package Base;

public class Color {
    private final double r;
    private final double g;
    private final double b;

    public Color(double r, double g, double b) {
        assert (r < 0 || r > 1);
        assert (g < 0 || g > 1);
        assert (b < 0 || b > 1);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(Color c) {
        this.r = c.r;
        this.g = c.g;
        this.b = c.b;
    }

    public double getR() {
        return r;
    }

    public double getB() {
        return b;
    }

    public double getG() {
        return g;
    }

    public int encodeRGBGamma() {
        int encoded;

        /*
        if rgb <= .00304
            encode << 0 12.92 x r
            encode << 8 12.92 x g
            encode << 16 12.92 x b
         else
            encode << 0

         */

        return encoded;
    }
}