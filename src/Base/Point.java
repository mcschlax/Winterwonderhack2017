package Base;

public class Point {
    private final double x;
    private final double y;
    private Color c;
    private int f;

    public Point(double x, double y, int f, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.f = f;
    }

    public Point(int x, int y) {this(x, y, 0, Color.WHT); }

    public double X() { return x; }

    public double Y() { return y; }

    public Color C() { return c; }
    public void C(Color c) { this.c = c; }

    public int V() { return f; }
    public void incV() { this.f++; }

}
