package Base;

import java.util.Arrays;
import java.util.List;

public enum FractalParam {
    XRES (Integer.class, Arrays.asList("XRES", "x")),
    YRES (Integer.class, Arrays.asList("YRES", "y")),
    ITER (Integer.class, Arrays.asList("ITER", "i")),
    VAR0 (Double.class, Arrays.asList("VAR0", "v0", "w0", "linear")),
    VAR1 (Double.class, Arrays.asList("VAR1", "v1", "w1", "sinusodial")),
    VAR2 (Double.class, Arrays.asList("VAR2", "v1", "w2", "spherical"));

    private final List<String> alias;
    private final Class<?> tClass;

    FractalParam (Class<?> tClass, List<String> alias) {
        this.tClass = tClass;
        this.alias = alias;
    }

    public Class<?> tClass() { return tClass; }

    public List<String> alias() { return alias; }
}
