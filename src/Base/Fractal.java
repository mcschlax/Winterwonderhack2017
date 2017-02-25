package Base;

import java.util.*;

public class Fractal {
    private static Random random = new Random();

    public static Point[][] createFractal(Map<String, String> params) {
        EnumMap<FractalParam, Object> valid = validateParams(params);

        int XRES = (int) valid.get(FractalParam.XRES);
        int YRES = (int) valid.get(FractalParam.YRES);

        Point[][] points = new Point[XRES][YRES];
        for (int x = 0; x < XRES; x++)
            for (int y = 0; y < YRES; y++)
                points[x][y] = new Point( (x - XRES/2)/(XRES/2),y); // scale to [-1,1)

        int ITER = (int) valid.get(FractalParam.ITER);

        int ranX = random.nextInt(XRES);
        int ranY = random.nextInt(YRES);

        Point p = points[ranX][ranY];
        for (int i = 0; i < ITER; i++) {
            int f = random.nextInt(FractalParam.values().length - 3);
            double weight;
            switch (f) {
                case 0:
                    weight = (double) valid.get(FractalParam.VAR0);
                    p = points[map( weight*(p.X()/2),XRES)][map(weight*(p.X()/2),YRES)];
                    break;
                case 1:
                    weight = (double) valid.get(FractalParam.VAR1);
                    p = points[map(weight*((p.X()+1)/2), XRES)][map(weight*(p.Y()/2),YRES)];
                    break;
                case 2:
                    weight = (double) valid.get(FractalParam.VAR2);
                    p = points[map(weight*(p.X()/2),XRES)][map(weight*((p.Y() + 1)/2), YRES)];
                    break;
            }

            if (i > 20)
                p.incV();
        }

        return points;
    }
    private static int map(double d, int max) { return (int) (d*max/2 + max/2); }

    private static EnumMap<FractalParam, Object> validateParams(Map<String, String> params) {
        List<String> errorList = new LinkedList<>();

        EnumMap<FractalParam, Object> validated = new EnumMap<>(FractalParam.class);

        for (String option : params.keySet()) {
            for (FractalParam p : FractalParam.values()) {
                boolean found = false;
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
                        } else {
                            throw new IllegalArgumentException("Fractal Option Type Must Be Defined Programmer Mistake!");
                        }

                        validated.put(p, parse);

                    } catch (NumberFormatException ex) {
                        errorList.add("Fractal Option " + option + " Value " +
                                params.get(option) + " Illegal Type Must Be " + p.tClass());
                    } catch (IllegalArgumentException ex) {
                        errorList.add(ex.getMessage());
                    }
                    break;
                }
                // TODO if not found create error message
            }
        }

        //errorList.add("Fractal must have x and y resolution");
        //errorList.add("Fractal must have number of iterations");

        if (!errorList.isEmpty()) {
            String error = "";
            for (String s : errorList) error += s + '\n';

            throw new IllegalArgumentException(error);
        }

        return validated;
    }


}