package Base;

import java.util.Arrays;
import java.util.List;

public enum FractalParam {
    XRES (Integer.class, Arrays.asList("XRES", "x")),
    YRES (Integer.class, Arrays.asList("YRES", "y")),
    ITER (Long.class, Arrays.asList("ITER", "i")),
    VAR0 (Double.class, Arrays.asList("VAR0", "v0", "w0", "linear"), new Color(1.0,0.0,0.0)),
    VAR1 (Double.class, Arrays.asList("VAR1", "v1", "w1", "sinusodial"), new Color(0.0,1.0,0.0)),
    VAR2 (Double.class, Arrays.asList("VAR2", "v1", "w2", "spherical"), new Color(0.0,0.0,1.0));

    private final List<String> alias;
    private final Class<?> tClass;
    private final Color color;

    FractalParam (Class<?> tClass, List<String> alias) {
        this.tClass = tClass;
        this.alias = alias;
        this.color = new Color(1.0,1.0,1.0);
    }

    FractalParam (Class<?> tClass, List<String> alias, Color color) {
        this.tClass = tClass;
        this.alias = alias;
        this.color = color;
    }

    public List<String> alias() { return alias; }
    public Class<?> tClass() { return tClass; }
    public Color color() { return color; }
}
