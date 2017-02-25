package Base;

import java.util.*;

public class Main {

    public static void main(String[] args) {
       try {
           Fractal.createFractal(parseArgs(args));
       } catch (IllegalArgumentException ex) { System.out.print(ex.getMessage()); return; }
    }

    private static Map<String, String> parseArgs(String[] args) {
        List<String> errorList = new LinkedList<>();

        Map<String, String> parsedArgs = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {
                final String option = args[i].substring(1, args.length);
                boolean validOption = true;

                if (option.equals("")) {
                    errorList.add("Invalid Option Arg " + i + ": No option declared after -");
                    validOption = false;
                }

                if (i == args.length - 1 || args[i+1].charAt(0) == '-') {
                    errorList.add("Invalid Option Arg " + i + ": No option defined after -");
                    validOption = false;
                }

                if (validOption)
                    parsedArgs.put(option, args[i++]);
            }
        }

        if (!errorList.isEmpty()) {
            String error = "";
            for (String s : errorList) error += s + '\n';

            throw new IllegalArgumentException(error);
        }

        return parsedArgs;
    }
}
