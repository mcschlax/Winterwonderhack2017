package Base;

import java.util.*;

public class Fractal {
    private static int defaultXRES = 512;
    private static int defaultYRES = 512;
    private static long defaultITER =  1000000L;
    private static double defaultWeight = 0.0;

    private static Random random = new Random();

    public static Point[][] createFractal(Map<String, String> params) {
        EnumMap<FractalParam, Object> valid = validateParams(params);

        int YRES = (int) valid.get(FractalParam.YRES);
        int XRES = (int) valid.get(FractalParam.XRES);

        Point[][] points = new Point[YRES][XRES];
        for (int y = 0; y < YRES; y++)
            for (int x = 0; x < XRES; x++)
                points[y][x] = new Point((y - YRES/2.0)/(YRES/2.0),(x - XRES/2.0)/(XRES/2.0)); // scale to [-1,1)

        long ITER = (long) valid.get(FractalParam.ITER);
        Point p = points[random.nextInt(YRES)][random.nextInt(XRES)];
        Color c = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble());

        /* calculate total weight*/
        double totalWeight = 0;
        for (FractalParam fparam : FractalParam.values())
            if (fparam.tClass() == Double.class)
                totalWeight += (double) valid.get(fparam);

        for (long i = 0; i < ITER; i++) {
            /* pick a random variation based on weight */
            double ran = random.nextDouble() * totalWeight;
            double countUp = 0;
            int variation = 0;
            for (FractalParam fparam : FractalParam.values())
                if (fparam.tClass() == Double.class) {
                    /* count up through variations until weight is topped indicating found variation*/
                    countUp += (double) valid.get(fparam);
                    variation++;
                    if (countUp >= ran) {
                        variation--;
                        break;
                    }
                }
            switch (variation) {
                case 0:
                    p = points[map((p.Y()+1)/2.0, YRES)][map(p.X()/2.0, XRES)];
                    c = Color.mix(c, FractalParam.VAR0.color());
                    break;
                case 1:
                    p = points[map(p.Y()/2.0, YRES)][map((p.X()+1)/2.0, XRES)];
                    c = Color.mix(c, FractalParam.VAR1.color());
                    break;
                case 2:
                    p = points[map(p.Y()/2.0, YRES)][map(p.X()/2.0, XRES)];
                    c = Color.mix(c, FractalParam.VAR2.color());
                    break;
                default:
                    System.out.print(variation);
            }

            if (i > 20) {
                p.incV();
                p.C(Color.mix(p.C(), c));
            }
        }

        return points;
    }
    private static int map(double d, int max) {
        if (d < -1 || d > 1) throw new IllegalArgumentException("D " + d + " must be within range [-1,1]");
        return (int) (d*max/2 + max/2);
    }

    private static EnumMap<FractalParam, Object> validateParams(Map<String, String> params) {
        List<String> errorList = new LinkedList<>();

        EnumMap<FractalParam, Object> valid = new EnumMap<>(FractalParam.class);

        for (String option : params.keySet()) {
            boolean found = false;
            for (FractalParam p : FractalParam.values()) {
                for (String alias : p.alias())
                    if (option.equalsIgnoreCase(alias))
                        found = true;

                if (found) {
                    // make this less janky in future
                    try {
                        Object parse;
                        if (p.tClass() == Integer.class) {
                            parse = Integer.parseInt(params.get(option));
                        } else if (p.tClass() == Double.class) {
                            parse = Double.parseDouble(params.get(option));
                        } else if (p.tClass() == Long.class) {
                            parse = Long.parseLong(params.get(option));
                        } else {
                            throw new IllegalArgumentException("Fractal Option Type Must Be Defined Programmer Mistake!");
                        }

                        valid.put(p, parse);
                    } catch (NumberFormatException ex) {
                        errorList.add("Fractal Option " + option + " Value " +
                                params.get(option) + " Illegal Type Must Be " + p.tClass());
                    } catch (IllegalArgumentException ex) {
                        errorList.add(ex.getMessage());
                    }
                    break;
                }
            }
            if (!found) {
                errorList.add("Fractal Option " + option + " Does Not Exist");
            }
        }

        if (!errorList.isEmpty()) {
            String error = "";
            for (String s : errorList) error += s + '\n';

            throw new IllegalArgumentException(error);
        }

        /* default values if necessary */
        if (!valid.containsKey(FractalParam.XRES))
            valid.put(FractalParam.XRES, defaultXRES);
        if (!valid.containsKey(FractalParam.YRES))
            valid.put(FractalParam.YRES, defaultYRES);
        if (!valid.containsKey(FractalParam.ITER))
            valid.put(FractalParam.ITER, defaultITER);
        for (FractalParam p : FractalParam.values())
            if (!valid.containsKey(p))
                valid.put(p, defaultWeight);


        return valid;
    }


}