package Base;

public class Point {
    private final int x;
    private final int y;
    private Color c;
    private int v;

    public Point(int x, int y, int v, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.v = v;
    }

    public Point(int x, int y) {this(x, y, 0, Color.WHT); }

    public int X() { return x; }

    public int Y() { return y; }

    public Color C() { return c; }
    public void C(Color c) { this.c = c; }

    public int V() { return v; }
    public void V(int v) { this.v = v; }

}
