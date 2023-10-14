import java.util.Scanner;

public class MetricConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

            System.out.println("Please input your query. For example, 1 km = m. Enter 'exit' or '-1' to exit the program");
            System.out.println("Supported conversions: km = miles, mm = inches,  celsius = fahrenheit, kg = lb");
        while (running) {

            String input = scanner.nextLine();
            if (input.equals("exit") || input.equals("-1")){
                running = false;
                System.exit(-1);
            }
            // Split the input into parts
            String[] parts = input.split(" ");

            if (parts.length == 4 && parts[2].equals("=")) {
                try {
                    double value = Integer.parseInt(parts[0]);
                    String fromUnit = parts[1];
                    String toUnit = parts[3];

                    Conversion conversion = new Conversion(value, fromUnit, toUnit);
                    double convertedValue = conversion.convert();
                    if (value == 0 && convertedValue == 0) {
                        // Handle the case of converting 0 to 0
                        System.out.println("No conversion needed. The value is 0 " + toUnit);
                    } 
                    else if(value != 0 && convertedValue == 0){
                        continue;
                    }
                    else {
                        System.out.println("Your converted value is " + convertedValue + " from " + fromUnit + " to " + toUnit);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The first part is not a valid integer.");
                }
            } else {
                System.out.println("Invalid input. Please provide a value, 'Unit', '=', and 'NewUnit' separated by space.");
            }
        }
        scanner.close();
    }

    private static class Conversion {
        private double value;
        private String fromUnit;
        private String toUnit;
        private double convertedValue;

        private Conversion(double value, String fromUnit, String toUnit) {
            this.value = value;
            this.fromUnit = fromUnit;
            this.toUnit = toUnit;
        }

        private double convert() {
            switch (fromUnit + " = " + toUnit) {
                case "km = miles":
                    convertedValue = value * 0.6213712; 
                    break;
                case "mm = inches":
                    convertedValue = value / 25.4; 
                    break;
                case "celsius = fahrenheit":
                    convertedValue = (value * 1.8) + 32;
                    break;
                case "kg = lb":
                    convertedValue = value * 2.2046;
                    break;
                default:
                    System.out.println("Unsupported unit conversion.");
            }
            return convertedValue;
        }
    }
}




