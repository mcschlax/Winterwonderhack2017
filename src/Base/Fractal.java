package Base;

import java.util.*;

public class Fractal {
    public static Point[][] createFractal(Map<String, String> params) {
        EnumMap<FractalParam, Object> validated = validateParams(params);
        return  null;
    }

    private static EnumMap<FractalParam, Object> validateParams(Map<String, String> params) {
        List<String> errorList = new LinkedList<>();
        errorList.add("Fractal must have x and y resolution");
        errorList.add("Fractal must have number of iterations");

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

        if (!errorList.isEmpty()) {
            String error = "";
            for (String s : errorList) error += s + '\n';

            throw new IllegalArgumentException(error);
        }

        return validated;
    }
}