import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    private double rootullon;
    private double rootullat;
    private double rootlrlon;
    private double rootlrlat;
    private double queryullon;
    private double queryullat;
    private double querylrlon;
    private double querylrlat;
    private double querywidth;
    private double queryheight;
    private int depth;
    private double rasterLonDPP;
    private double rasterullon;
    private double rasterlrlon;
    private double rasterullat;
    private double rasterlrlat;
    private double lonPI;
    private int minx;
    private int maxx;
    private double latPI;
    private int miny;
    private int maxy;
    private String[][] grid;
    private Boolean querysuccess;

    public Rasterer() {
        rootullon = MapServer.ROOT_ULLON;
        rootullat = MapServer.ROOT_ULLAT;
        rootlrlon = MapServer.ROOT_LRLON;
        rootlrlat = MapServer.ROOT_LRLAT;
    }

    private void update(Map<String, Double> params) {
        querylrlon = params.get("lrlon");
        querylrlat = params.get("lrlat");
        queryullon = params.get("ullon");
        queryullat = params.get("ullat");
        querywidth = params.get("w");
        queryheight = params.get("h");
    }

    private void fill(Map<String, Object> results) {
        updateresults();
        results.put("depth", depth);
        results.put("raster_ul_lon", rasterullon);
        results.put("raster_ul_lat", rasterullat);
        results.put("raster_lr_lon", rasterlrlon);
        results.put("raster_lr_lat", rasterlrlat);
        results.put("render_grid", grid);
        results.put("query_success", querysuccess);
    }

    private void updateresults() {
        updatedepth();
        updateraster();
        updaterendergrid();
        updatequerysuccess();
    }

    private void updatequerysuccess() {
        if (queryullon > rootlrlon || queryullat < rootlrlat || querylrlon < rootullon || querylrlat > rootullat) {
            querysuccess = false;
        } else {
            querysuccess = true;
        }
    }

    private void updaterendergrid() {
        if (maxx > Math.pow(2, depth) - 1) {
            maxx = (int) Math.pow(2, depth) - 1;
        }
        if (maxy > Math.pow(2, depth) - 1) {
            maxy = (int) Math.pow(2, depth) - 1;
        }
        grid = new String[maxy - miny + 1][maxx - minx + 1];
        for (int i = miny; i < miny + grid.length; i++) {
            for (int j = minx; j < minx + grid[0].length; j++) {
                grid[i - miny][j-minx] = "d" + depth + "_x" + j + "_y" + i + ".png";
            }
        }
    }

    private void updateraster() {
        lonperimage();
        imageminx();
        imagemaxx();
        latperimage();
        imageminy();
        imagemaxy();
    }

    private void imageminy() {
        int k = 0;
        while ((-(k * latPI)) + rootullat > queryullat) {
            k++;
        }
        if (k == 0) {
            miny = 0;
        } else {
            miny = k - 1;
        }
        rasterullat = -(miny*latPI) + rootullat;
    }

    private void imagemaxy() {
        int k = 0;
        while ((-k * latPI) + rootullat > querylrlat) {
            k++;
        }
        if (k == 0) {
            maxy = 0;
        } else {
            maxy = k - 1;
        }
        rasterlrlat = -(k * latPI) + rootullat;
    }

    private void imageminx() {
        int k = 0;
        while (((k*lonPI) + rootullon) < queryullon) {
            k++;
        }
        if (k == 0) {
            minx = 0;
        } else {
            minx = k - 1;
        }
        rasterullon = (minx * lonPI) + rootullon;
    }

    private void imagemaxx() {
        int k = 0;
        while (((k*lonPI) + rootullon) < querylrlon) {
            k++;
        }
        if (k == 0) {
            maxx = 0;
        } else {
            maxx = k - 1;
        }
        rasterlrlon = (k * lonPI) + rootullon;
    }

    private void latperimage() {
        latPI = Math.abs(rootullat - rootlrlat)/Math.pow(2, depth);
    }

    private void lonperimage() {
        lonPI = Math.abs(rootullon - rootlrlon)/Math.pow(2, depth);
    }

    private void updatedepth() {
        double QueryLonDPP = Math.abs(queryullon - querylrlon)/(querywidth);
        int k = 0;
        while (DepthkLonDPP(k) > QueryLonDPP) {
            k++;
        }
        depth = k;
        if (depth > 7) {
            depth = 7;
        }
        rasterLonDPP = DepthkLonDPP(depth);
    }

    private double DepthkLonDPP(int depth) {
        return Math.abs(rootullon - rootlrlon)/(Math.pow(2, depth) * 256);
    }





    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        update(params);
        fill(results);
        return results;
    }

}
