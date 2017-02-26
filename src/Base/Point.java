package Base;

public class Point {
    private final double y;
    private final double x;

    private Color c;
    private int f;

    public Point(double y, double x, int f, Color c) {
        this.y = y;
        this.x = x;
        this.c = c;
        this.f = f;
    }

    public Point(double y, double x) {this(y, x, 0, new Color(1, 1, 1)); }

    public double Y() { return y; }

    public double X() { return x; }

    public Color C() { return c; }
    public void C(Color c) { this.c = c; }

    public int V() { return f; }
    public void incV() { this.f++; }

}
