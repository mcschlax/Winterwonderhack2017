package Base;

public class Color {
    private static final byte WDC = (byte) 255; // white digital count
    private static final byte KDC = (byte) 0;   // black digital count

    private final double r;
    private final double g;
    private final double b;

    /**
     * Color
     * @param r Red [0,1]
     * @param g Green[0,1]
     * @param b Blue [0,1]
     */
    public Color(double r, double g, double b) {
        if (r < 0 || r > 1) throw new IllegalArgumentException("R value " + r + " must be within range [0,1]");
        if (g < 0 || g > 1) throw new IllegalArgumentException("G value " + g + " must be within range [0,1]");
        if (b < 0 || b > 1) throw new IllegalArgumentException("B value " + b + " must be within range [0,1]");


        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Please don't use unless testing as this eliminates the purpose of proper coloring
     * @param r [KDC,WDC]
     * @param g [KDC,WDC]
     * @param b [KDC,WDC]
     */
    @Deprecated
    public Color(byte r, byte g, byte b) {
        double rP = ((r - KDC) & 0xFF)/((double) ((WDC - KDC) & 0xFF));
        double gP = ((g - KDC) & 0xFF)/((double) ((WDC - KDC) & 0xFF));
        double bP = ((b - KDC) & 0xFF)/((double) ((WDC - KDC) & 0xFF));
        this.r = (rP <= 0.03928) ? rP/12.92 : Math.pow((rP + 0.055)/1.055, 2.4);
        this.g = (gP <= 0.03928) ? gP/12.92 : Math.pow((gP + 0.055)/1.055, 2.4);
        this.b = (bP <= 0.03928) ? bP/12.92 : Math.pow((bP + 0.055)/1.055, 2.4);
    }

    public double R() {
        return r;
    }
    public double G() {
        return g;
    }
    public double B() {
        return b;
    }


    /**
     * Compacts color into int, encoding with gamma correction
     * @return encoded int, each byte in int is a color channel in order r,g,b,a - a unused
     */
    public int encodeRGBGamma() {
        /*
        sRGB  tristimulus to nonlinar sRGB
        if rgb <= .00304
            rgb' = 12.92 x rgb
        else
            rgb' = 1.055 x rgb^(1.0/2.4) - 0.055
        */

        double rP = (r <= .003040) ? 12.92*r : 1.055*Math.pow(r, 1.0/2.4) - 0.055;
        double gP = (g <= .003040) ? 12.92*g : 1.055*Math.pow(g, 1.0/2.4) - 0.055;
        double bP = (b <= .003040) ? 12.92*b : 1.055*Math.pow(b, 1.0/2.4) - 0.055;

        /*
        Digital Code Values
        rgbB = (WDC - KDC) x rgb' + KDC
        */

        byte rB = (byte)(((WDC - KDC) & 0xFF)*rP + KDC);
        byte gB = (byte)(((WDC - KDC) & 0xFF)*gP + KDC);
        byte bB = (byte)(((WDC - KDC) & 0xFF)*bP + KDC);

        int encoded = 0;
        encoded += rB << 0;
        encoded += gB << 8;
        encoded += bB << 16;

        return encoded;
    }

    /**
     * Uncompacts int into byte[]
     * @param encoded int to be decoded
     * @return byte[] r,g,b,a - a unused and note bytes are signed in java!
     */
    public static byte[] decodeRGBGamma(int encoded) {
        byte[] split = new byte[4];
        for (int i = 0; i < 4; i++)
            split[i] = (byte) (encoded >>> i*8 & 0xFF);
        return split;
    }

    public static Color mix(Color c1, Color c2) {
        return new Color((c1.R()+c2.R())/2.0, (c1.G()+c2.G())/2.0, (c1.B()+c2.B())/2.0);
    }

    public static Color mix(Color c1, Color c2, double f) {
        Color mixed = mix(c1, c2);
        return new Color(mixed.R() * Math.log(f)/f, mixed.G() * Math.log(f)/f, mixed.B() * Math.log(f)/f);
    }
}