package byog.Core;

import byog.TileEngine.TETile;

public interface MapObject {
    public int latitude();
    public int longitude();
    public TETile[][] World();
    public TETile Avatar();
    public void setlatitude(int latitude);
    public void setlongitude(int longitude);
}
