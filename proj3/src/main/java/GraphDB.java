import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /** Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc. */
    Map<String, Node> Nodes = new HashMap<>();
    Map<String, Way> Buildings = new HashMap<>();
    Map<String, Way> Highways = new HashMap<>();

    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     * @param dbPath Path to the XML file to be parsed.
     */
    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            FileInputStream inputStream = new FileInputStream(inputFile);
            // GZIPInputStream stream = new GZIPInputStream(inputStream);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputStream, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    public class Node {
        String id;
        String lon;
        String lat;
        Set<Node> Adj;
        String name;
        String type;
        Set<Way> Highways;
        Set<Way> Buildings = new HashSet<>();

        Node(String ID, String LON, String LAT) {
            id = ID;
            lon = LON;
            lat = LAT;
            Adj = new HashSet<>();
            Highways = new HashSet<>();
        }

        void addAdjacentNode(String ID) {
            Adj.add(Nodes.get(ID));
            Nodes.get(ID).addAdjacentNode(id);
        }

        void addAdjacentNode(Node node) {
            Adj.add(node);
        }

        void addName(String NAME) {
            name = NAME;
        }

        void addType(String TYPE) {
            type = TYPE;
        }

        void addBuilding(Way building) {
            Buildings.add(building);
        }

    }

    public class Way {
        String id;
        Queue<Node> ConnectedNodes;
        String buildingtype;
        String name;
        String maxspeed;
        String highwaytype;

        Way(String ID) {
            id = ID;
            ConnectedNodes = new LinkedList<>();
        }

        void addNode(String ID) {
            Node toput = Nodes.get(ID);
            toput.Highways.add(this);
            ConnectedNodes.add(toput);
        }

        void addBuildingType(String TYPE) {
            buildingtype = TYPE;
        }

        void addName(String NAME) {
            name = NAME;
        }

        void addMaxSpeed(String SPEED) {
            maxspeed = SPEED;
        }

        void addHighwayType(String HIGHWAY) {
            highwaytype = HIGHWAY;
        }

    }

    Way addWay(String ID) {
        return new Way(ID);
    }

    Node addNode(String ID, String LON, String LAT) {
        Node r = new Node(ID, LON, LAT);
        Nodes.put(ID, r);
        return r;
    }

    void addBuilding(Way building) {
        Buildings.put(building.id, building);
        if (building.name != null) {
            for (Node i : building.ConnectedNodes) {
                i.addBuilding(building);
            }
        }
    }

    void addHighways(Way highway) {
        Highways.put(highway.id, highway);
        Iterator<Node> Connections = highway.ConnectedNodes.iterator();
        Node pastnode = Connections.next();
        if (!Connections.hasNext()) {
            return;
        }
        Node presentnode = Connections.next();
        pastnode.addAdjacentNode(presentnode);
        while (Connections.hasNext()) {
            Node futurenode = Connections.next();
            presentnode.addAdjacentNode(pastnode);
            presentnode.addAdjacentNode(futurenode);
            pastnode = presentnode;
            presentnode = futurenode;
        }
        presentnode.addAdjacentNode(pastnode);
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        Set<String> toberemoved = new HashSet<>();
        for (Map.Entry<String, Node> i : Nodes.entrySet()) {
            if (i.getValue().Adj.isEmpty() && i.getValue().name == null) {
                toberemoved.add(i.getKey());
            }
        }
        for (String i : toberemoved) {
            Nodes.remove(i);
        }
    }

    /**
     * Returns an iterable of all vertex IDs in the graph.
     * @return An iterable of id's of all vertices in the graph.
     */
    Iterable<Long> vertices() {
        //YOUR CODE HERE, this currently returns only an empty list.
        HashSet<Long> vertices = new HashSet<>();
        for (Node i : Nodes.values()) {
            vertices.add(Long.parseLong(i.id));
        }
        return vertices;
    }

    /**
     * Returns ids of all vertices adjacent to v.
     * @param v The id of the vertex we are looking adjacent to.
     * @return An iterable of the ids of the neighbors of v.
     */
    Iterable<Long> adjacent(long v) {
        HashSet<Long> adjacent = new HashSet<>();
        for (Node i : Nodes.get(Long.toString(v)).Adj) {
            adjacent.add(Long.parseLong(i.id));
        }
        return adjacent;
    }

    /**
     * Returns the great-circle distance between vertices v and w in miles.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The great-circle distance between the two locations from the graph.
     */
    double distance(long v, long w) {
        return distance(lon(v), lat(v), lon(w), lat(w));
    }

    static double distance(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double dphi = Math.toRadians(latW - latV);
        double dlambda = Math.toRadians(lonW - lonV);

        double a = Math.sin(dphi / 2.0) * Math.sin(dphi / 2.0);
        a += Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda / 2.0) * Math.sin(dlambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 3963 * c;
    }

    /**
     * Returns the initial bearing (angle) between vertices v and w in degrees.
     * The initial bearing is the angle that, if followed in a straight line
     * along a great-circle arc from the starting point, would take you to the
     * end point.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The initial bearing between the vertices.
     */
    double bearing(long v, long w) {
        return bearing(lon(v), lat(v), lon(w), lat(w));
    }

    static double bearing(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double lambda1 = Math.toRadians(lonV);
        double lambda2 = Math.toRadians(lonW);

        double y = Math.sin(lambda2 - lambda1) * Math.cos(phi2);
        double x = Math.cos(phi1) * Math.sin(phi2);
        x -= Math.sin(phi1) * Math.cos(phi2) * Math.cos(lambda2 - lambda1);
        return Math.toDegrees(Math.atan2(y, x));
    }

    /**
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    long closest(double lon, double lat) {
        long id = 0;
        double closestdistance = Double.POSITIVE_INFINITY;
        for (Node i : Nodes.values()) {
            if (closestdistance > distance(lon, lat, Double.parseDouble(i.lon), Double.parseDouble(i.lat))) {
                id = Long.parseLong(i.id);
                closestdistance = distance(lon, lat, Double.parseDouble(i.lon), Double.parseDouble(i.lat));
            }
        }
        return id;
    }

    /**
     * Gets the longitude of a vertex.
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    double lon(long v) {
        return Double.parseDouble(Nodes.get(Long.toString(v)).lon);
    }

    /**
     * Gets the latitude of a vertex.
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    double lat(long v) {
        return Double.parseDouble(Nodes.get(Long.toString(v)).lat);
    }
}
