import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a shortestPath method for finding routes between two points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for your
 * satisfaction (or the autograder), upgrade your implementation by switching it to A*.
 * Your code will probably not be fast enough to pass the autograder unless you use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code, and boils
 * down to the priority you use to order your vertices.
 */
public class Router {
    /**
     * Return a List of longs representing the shortest path from the node
     * closest to a start location and the node closest to the destination
     * location.
     * @param g The graph to use.
     * @param stlon The longitude of the start location.
     * @param stlat The latitude of the start location.
     * @param destlon The longitude of the destination location.
     * @param destlat The latitude of the destination location.
     * @return A list of node id's in the order visited on the shortest path.
     */

    public static List<Long> shortestPath(GraphDB g, double stlon, double stlat,
                                          double destlon, double destlat) {
        long startnode = g.closest(stlon, stlat);
        long endnode = g.closest(destlon, destlat);
        System.out.println(endnode);
        PriorityQueue<Vertice> nextup = new PriorityQueue<>();
        Vertice start = new Vertice(startnode, endnode, g);
        start.previousvertice = null;
        start.distancesofar = 0;
        for (Long i : g.adjacent(startnode)) {
            Vertice next = new Vertice(i, endnode, g);
            next.previousvertice(start);
            nextup.add(next);
        }
        Vertice last = shortestPathHelper(g, endnode, nextup);
        List<Long> path = new LinkedList<Long>();
        return listify(last, path);
    }

    private static List<Long> listify(Vertice last, List<Long> current) {
        if (last.previousvertice == null) {
            current.add(last.thisnode);
            return current;
        } else {
            List<Long> returnlist = listify(last.previousvertice, current);
            current.add(last.thisnode);
            return returnlist;
        }
    }

    private static class Vertice implements Comparable<Vertice> {
        Vertice previousvertice;
        Long thisnode;
        Long endnode;
        double distancesofar;
        double distancetogo;
        GraphDB g;

        Vertice(Long nodeid, Long ENDNODE, GraphDB G) {
            thisnode = nodeid;
            endnode = ENDNODE;
            g = G;
        }

        double distancetogo() {
            return g.distance(g.lon(thisnode), g.lat(thisnode), g.lon(endnode), g.lat(endnode));
        }

        public int compareTo(Vertice other) {
            if (((this.distancesofar + this.distancetogo()) - (other.distancesofar + other.distancetogo())) < 0) {
                return -1;
            } else if ((this.distancesofar + this.distancetogo()) - (other.distancesofar + other.distancetogo()) > 0) {
                return 1;
            } else {
                return 0;
            }
        }

        void previousvertice(Vertice i) {
            previousvertice = i;
            distancesofar = previousvertice.distancesofar + g.distance(g.lon(thisnode), g.lat(thisnode), g.lon(previousvertice.thisnode), g.lat(previousvertice.thisnode));
        }



    }

    private static Vertice shortestPathHelper(GraphDB g, Long endnode, Queue<Vertice> nodestocheck) {
        Vertice whatsup = nodestocheck.remove();
        Set<Long> checked = new HashSet<>();
        while (!whatsup.thisnode.equals(endnode)) {
            checked.add(whatsup.thisnode);
            for (Long i : g.adjacent(whatsup.thisnode)) {
                if (whatsup.previousvertice.thisnode != i && !checked.contains(i)) {
                    Vertice next = new Vertice(i, endnode, g);
                    next.previousvertice(whatsup);
                    nodestocheck.add(next);
                }
            }
            if (nodestocheck.isEmpty() && !whatsup.thisnode.equals(endnode)) {
                whatsup.previousvertice = null;
                return whatsup;
            }
            whatsup = nodestocheck.remove();
        }
        return whatsup;
    }

    /**
     * Create the list of directions corresponding to a route on the graph.
     * @param g The graph to use.
     * @param route The route to translate into directions. Each element
     *              corresponds to a node from the graph in the route.
     * @return A list of NavigatiionDirection objects corresponding to the input
     * route.
     */
    public static List<NavigationDirection> routeDirections(GraphDB g, List<Long> route) {
        List<NavigationDirection> directions = new LinkedList<NavigationDirection>();
        Iterator<Long> path = route.iterator();
        direction(g, path, directions);
        for (NavigationDirection i : directions) {
            System.out.println(i);
        }
        return directions;
    }

    private static void direction(GraphDB g, Iterator<Long> route, List<NavigationDirection> directions) {
        Long currentnode = route.next();
        GraphDB.Way currentway = null;
        Long nextnode = route.next();
        double distancesofar = 0;
        for (GraphDB.Way i : g.Nodes.get(Long.toString(currentnode)).Highways) {
            if (g.Nodes.get(Long.toString(nextnode)).Highways.contains(i)) {
                currentway = i;
            }
        }
        int direction = 0;
        Long previousnode = currentnode;
        while (route.hasNext()) {
            while (containsway(g, nextnode, currentnode, currentway) && route.hasNext()) {
                distancesofar += g.distance(currentnode, nextnode);
                previousnode = currentnode;
                currentnode = nextnode;
                nextnode = route.next();
            }

            double initialbearing = g.bearing(previousnode, currentnode);
            double nextbearing = g.bearing(currentnode, nextnode);
            NavigationDirection nextd = new NavigationDirection();
            nextd.direction = direction;
            nextd.distance = distancesofar;
            if (route.hasNext()) {
                nextd.distance = distancesofar;
            } else if (!route.hasNext() && containsway(g, nextnode, currentnode, currentway)) {
                distancesofar += g.distance(currentnode, nextnode);
                nextd.distance = distancesofar;
                if (currentway.name == null) {
                    nextd.way = "";
                } else {
                    nextd.way = currentway.name;
                }
                directions.add(nextd);
               return;
            }
            if (currentway.name == null) {
                nextd.way = "";
            } else {
                nextd.way = currentway.name;
            }
            directions.add(nextd);
            direction = bearingint(initialbearing, nextbearing);
            Boolean toggle = true;
            if (route.hasNext()) {
                for (GraphDB.Way i : g.Nodes.get(Long.toString(nextnode)).Highways) {
                    if (i.name != null && currentway.name != null) {
                        if (i.name.equals(currentway.name) && !i.id.equals(currentway.id)) {
                            for (GraphDB.Way j : g.Nodes.get(Long.toString(currentnode)).Highways) {
                                for (GraphDB.Way h : g.Nodes.get(Long.toString(nextnode)).Highways) {
                                    if (j.id.equals(h.id) && j.name.equals(h.name) && !j.name.equals(currentway.name)) {
                                        currentway = j;
                                        toggle = false;
                                    }
                                }
                            }
                        }
                    }
                }
                previousnode = currentnode;
                currentnode = nextnode;
                nextnode = route.next();
                for (GraphDB.Way i : g.Nodes.get(Long.toString(currentnode)).Highways) {
                    if (g.Nodes.get(Long.toString(nextnode)).Highways.contains(i)) {
                        if (toggle == true) {
                            currentway = i;
                        }
                    }
                }
            }
            distancesofar = g.distance(previousnode, currentnode);
            if (!route.hasNext()) {
                initialbearing = g.bearing(previousnode, currentnode);
                nextbearing = g.bearing(currentnode, nextnode);
                nextd = new NavigationDirection();
                nextd.direction = direction;
                for (GraphDB.Way i : g.Nodes.get(Long.toString(currentnode)).Highways) {
                    if (g.Nodes.get(Long.toString(nextnode)).Highways.contains(i)) {
                        currentway = i;
                    }
                }
                if (containsway(g, previousnode, currentnode, currentway)) {
                    distancesofar += g.distance(currentnode, nextnode);
                } else {
                    distancesofar = g.distance(currentnode, nextnode);
                }
                nextd.distance = distancesofar;
                if (currentway.name == null) {
                    nextd.way = "";
                } else {
                    nextd.way = currentway.name;
                }
                directions.add(nextd);
                return;
            }
        }
        NavigationDirection nextd = new NavigationDirection();
        distancesofar += g.distance(currentnode, nextnode);
        nextd.distance = distancesofar;
        nextd.direction = 0;
        if (currentway.name == null) {
            nextd.way = "";
        } else {
            nextd.way = currentway.name;
        }
        directions.add(nextd);
    }

    private static Boolean containsway(GraphDB g, Long nextnode, Long currentnode, GraphDB.Way currentway) {

        for (GraphDB.Way i : g.Nodes.get(Long.toString(nextnode)).Highways) {
            if (i.id.equals(currentway.id)) {
                return true;
            } else if (i.name != null && currentway.name != null) {
                if (i.name.equals(currentway.name) && !i.id.equals(currentway.id)) {
                    for (GraphDB.Way j : g.Nodes.get(Long.toString(currentnode)).Highways) {
                        for (GraphDB.Way h : g.Nodes.get(Long.toString(nextnode)).Highways) {
                            if (j.id.equals(h.id) && j.name.equals(h.name) && !j.name.equals(currentway.name)) {
                                return false;
                            }
                        }
                    }
                }
                if (i.name.equals(currentway.name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int bearingint(double initial, double next) {
        if ((next > initial && next < initial + 15) || (next < initial && next > initial - 15)) {
            return 1;
        } else if ((next < -165 && initial > 360 - 15 + next) || (next > 165 && initial < -360 + 15 + next)) {
            return 1;
        } else if (next > initial && next < initial + 30) {
            return 3;
        } else if (next < initial && next > initial - 30) {
            return 2;
        } else if (next < -150 && initial > 360 - 30 + next) {
            return 3;
        } else if (next > 150 && initial < -360 + 30 + next) {
            return 2;
        } else if (next > initial && next < initial + 100) {
            return 4;
        } else if (next < initial && next > initial - 100) {
            return 5;
        } else if (next < -80 && initial > 360 - 100 + next) {
            return 4;
        } else if (next > 80 && initial < -360 + 100 + next) {
            return 5;
        } else if (next > initial && next < initial + 180) {
            return 7;
        } else if (next < initial && next > initial - 180) {
            return 6;
        } else if (next < 0 && initial > 360 - 180 + next) {
            return 7;
        } else if (next > 0 && initial < -360 + 180 + next) {
            return 6;
        }
        return 10;
    }


    /**
     * Class to represent a navigation direction, which consists of 3 attributes:
     * a direction to go, a way, and the distance to travel for.
     */
    public static class NavigationDirection {

        /** Integer constants representing directions. */
        public static final int START = 0;
        public static final int STRAIGHT = 1;
        public static final int SLIGHT_LEFT = 2;
        public static final int SLIGHT_RIGHT = 3;
        public static final int RIGHT = 4;
        public static final int LEFT = 5;
        public static final int SHARP_LEFT = 6;
        public static final int SHARP_RIGHT = 7;

        /** Number of directions supported. */
        public static final int NUM_DIRECTIONS = 8;

        /** A mapping of integer values to directions.*/
        public static final String[] DIRECTIONS = new String[NUM_DIRECTIONS];

        /** Default name for an unknown way. */
        public static final String UNKNOWN_ROAD = "unknown road";
        
        /** Static initializer. */
        static {
            DIRECTIONS[START] = "Start";
            DIRECTIONS[STRAIGHT] = "Go straight";
            DIRECTIONS[SLIGHT_LEFT] = "Slight left";
            DIRECTIONS[SLIGHT_RIGHT] = "Slight right";
            DIRECTIONS[LEFT] = "Turn left";
            DIRECTIONS[RIGHT] = "Turn right";
            DIRECTIONS[SHARP_LEFT] = "Sharp left";
            DIRECTIONS[SHARP_RIGHT] = "Sharp right";
        }

        /** The direction a given NavigationDirection represents.*/
        int direction;
        /** The name of the way I represent. */
        String way;
        /** The distance along this way I represent. */
        double distance;

        /**
         * Create a default, anonymous NavigationDirection.
         */
        public NavigationDirection() {
            this.direction = STRAIGHT;
            this.way = UNKNOWN_ROAD;
            this.distance = 0.0;
        }

        public String toString() {
            return String.format("%s on %s and continue for %.3f miles.",
                    DIRECTIONS[direction], way, distance);
        }

        /**
         * Takes the string representation of a navigation direction and converts it into
         * a Navigation Direction object.
         * @param dirAsString The string representation of the NavigationDirection.
         * @return A NavigationDirection object representing the input string.
         */
        public static NavigationDirection fromString(String dirAsString) {
            String regex = "([a-zA-Z\\s]+) on ([\\w\\s]*) and continue for ([0-9\\.]+) miles\\.";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(dirAsString);
            NavigationDirection nd = new NavigationDirection();
            if (m.matches()) {
                String direction = m.group(1);
                if (direction.equals("Start")) {
                    nd.direction = NavigationDirection.START;
                } else if (direction.equals("Go straight")) {
                    nd.direction = NavigationDirection.STRAIGHT;
                } else if (direction.equals("Slight left")) {
                    nd.direction = NavigationDirection.SLIGHT_LEFT;
                } else if (direction.equals("Slight right")) {
                    nd.direction = NavigationDirection.SLIGHT_RIGHT;
                } else if (direction.equals("Turn right")) {
                    nd.direction = NavigationDirection.RIGHT;
                } else if (direction.equals("Turn left")) {
                    nd.direction = NavigationDirection.LEFT;
                } else if (direction.equals("Sharp left")) {
                    nd.direction = NavigationDirection.SHARP_LEFT;
                } else if (direction.equals("Sharp right")) {
                    nd.direction = NavigationDirection.SHARP_RIGHT;
                } else {
                    return null;
                }

                nd.way = m.group(2);
                try {
                    nd.distance = Double.parseDouble(m.group(3));
                } catch (NumberFormatException e) {
                    return null;
                }
                return nd;
            } else {
                // not a valid nd
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof NavigationDirection) {
                return direction == ((NavigationDirection) o).direction
                    && way.equals(((NavigationDirection) o).way)
                    && distance == ((NavigationDirection) o).distance;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction, way, distance);
        }
    }
}
