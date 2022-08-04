package numbers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
//        write your code here
        System.out.println("Welcome to Amazing Numbers!");
        final String instructions = "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n\t" +
                "* the first parameter represents a starting number;\n\t" +
                "* the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.";
        System.out.println(instructions);
        final String errorMessage1 = "The first parameter should be a natural number or zero.";
        final String errorMessage2 = "The second parameter should be a natural number.";
        final List<String> properties = List.of("BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD");
        while (true) {
            System.out.print("Enter a request: ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if (str.matches("0")) {
                System.out.println("Goodbye!");
                return;
            }
            if (str.matches("\\D+")) {
                System.out.println(errorMessage1);
                continue;
            }
            if (str.matches("-?\\d+")) {
                long a = Long.parseLong(str);
                if ( a < 0) {
                    System.out.println(errorMessage1);
                    continue;
                }
                System.out.printf("Properties of %,d\n", a);
                System.out.println("even: " + isEven(a));
                System.out.println("odd: " + isOdd(a));
                System.out.println("buzz: " + isBuzz(a));
                System.out.println("duck: " + isDuck(a));
                System.out.println("palindromic: " + isPalindromic(a));
                System.out.println("gapful: " + isGapful(a));
                System.out.println("spy: " + isSpy(a));
                System.out.println("square: " + isSquare(a));
                System.out.println("sunny: " + isSunny(a));
                System.out.println("jumping: " + isJumping(a));
                System.out.println("happy: " + isHappy(a));
                System.out.println("sad: " + isSad(a));
                continue;
            }
            if (str.matches("-?\\d+\\s+-?\\d+\\s*([\\S]*\\s*)*")) {
                List<String> requestedProperties  = new ArrayList<>(Arrays.asList(str.split("\\s+")));

                for (int i = 2; i < requestedProperties.size(); i++) {
                    requestedProperties.set(i, requestedProperties.get(i).toUpperCase());
                }
                long initial = Long.parseLong(requestedProperties.get(0));
                int len = Integer.parseInt(requestedProperties.get(1));
                if (initial < 0) {
                    System.out.println(errorMessage1);
                    continue;
                }
                if (len <= 0) {
                    System.out.println(errorMessage2);
                    continue;
                }
                List<String> err = new ArrayList<>();
                for (int i = 2; i < requestedProperties.size(); i++){
                    if (requestedProperties.get(i).startsWith("-")){
                        if (!properties.contains(requestedProperties.get(i).substring(1)) && !requestedProperties.get(i).equals("")) err.add(requestedProperties.get(i));
                    }
                    else if (!properties.contains(requestedProperties.get(i)) && !requestedProperties.get(i).equals("")) err.add(requestedProperties.get(i));
                }
                if (err.size() > 0) {
                    System.out.printf("The %s %s %s wrong.\n" +
                                    "Available properties: %s\n",
                                    err.size() == 1 ? "property" :"properties", err, err.size() == 1 ? "is" : "are" , properties);
                    continue;
                }
                boolean mep = false;
                String mep1 = null, mep2 = null;

                if (requestedProperties.contains("EVEN") && requestedProperties.contains("ODD")) {
                    mep = true;
                    mep1 = "EVEN";
                    mep2 = "ODD";
                }
                else if (requestedProperties.contains("-EVEN") && requestedProperties.contains("-ODD")) {
                    mep = true;
                    mep1 = "-EVEN";
                    mep2 = "-ODD";
                }

                else if (requestedProperties.contains("SPY") && requestedProperties.contains("DUCK")) {
                    mep = true;
                    mep1 = "SPY";
                    mep2 = "DUCK";
                }
                else if (requestedProperties.contains("SUNNY") && requestedProperties.contains("SQUARE")) {
                    mep = true;
                    mep1 = "SUNNY";
                    mep2 = "SQUARE";
                }
                else if (requestedProperties.contains("HAPPY") && requestedProperties.contains("SAD")) {
                    mep = true;
                    mep1 = "HAPPY";
                    mep2 = "SAD";
                }
                else if (requestedProperties.contains("-HAPPY") && requestedProperties.contains("-SAD")) {
                    mep = true;
                    mep1 = "-HAPPY";
                    mep2 = "-SAD";
                }
                for (String property : requestedProperties) {
                    if (requestedProperties.contains("-" + property)) {
                        mep = true;
                        mep1 = property;
                        mep2 = "-" + property;
                        break;
                    }
                }
                if (mep) {
                    System.out.printf("The request contains mutually exclusive properties: \n[%s, %s]\nThere are no numbers with these properties.", mep1, mep2);
                    continue;
                }
                
                int count = 0;
                List<Long> nums;
                while (count < len) {
                    nums = LongStream.rangeClosed(initial, initial + 100000).boxed().collect(Collectors.toList());
                    for (int i = 2; i < requestedProperties.size(); i++) {
                        filterList(requestedProperties.subList(2, requestedProperties.size()), nums);
                    }
                    for (int i = 0; i < nums.size() && count < len; i++) {
                        printProperties(nums.get(i));
                        count++;
                    }
                    initial += 100000;
                }

            }


        }

/* */
    }
    static boolean isEven(long a){
        return a % 2 == 0;
    }

    static boolean isOdd(long a){
         return !isEven(a);
    }

    static boolean isBuzz(long a) {
        return a % 7 == 0 || a % 10 == 7;
    }


    static boolean isDuck(long a) {
        return String.valueOf(a).contains("0");
    }

    static boolean isPalindromic(long a){
        String str = String.valueOf(a);
        String rev = new StringBuilder(str).reverse().toString();
        return str.equals(rev);
    }


    static boolean isGapful(long a){
        if (a < 100) {
            return false;
        } else {
            String num = String.valueOf(a);
            char f = num.charAt(0);
            char l = num.charAt(num.length() - 1);
            int n = Integer.parseInt(f + "" + l);
            return a % n == 0;
        }
    }

    static boolean isSpy(long a){
        String[] digits = String.valueOf(a).split("");
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += Integer.parseInt(digits[i]);
        }
        int product = 1;
        for (int i = 0; i < digits.length; i++) {
            product *= Integer.parseInt(digits[i]);
        }
        return sum == product;
    }

    static boolean isSquare(long a) {
        long sqrt = (long) Math.sqrt(a);
        return sqrt * sqrt == a;
    }

    static boolean isSunny(long a) {
        return isSquare(a + 1);
    }

    static boolean isJumping(long a){
        if ( a < 10) {
            return true;
        }
        String[] digits = String.valueOf(a).split("");
        int prevDigit = Integer.parseInt(digits[0]);
        for (int i = 1; i < digits.length; i++) {
            int currentDigit = Integer.parseInt(digits[i]);
            if (Math.abs(prevDigit - currentDigit) != 1) {
                return false;
            }
            prevDigit = currentDigit;
        }
        return true;
    }

    /*static boolean isHappy(long a){
        List<Long> sums = new ArrayList<>();
        long currentNum = a;
        while (true) {
            String[] digits = String.valueOf(currentNum).split("");
            long sum = 0;
            for (int i = 0; i < digits.length; i++) {
                sum += Integer.parseInt(digits[i]) * Integer.parseInt(digits[i]);
            }
            if (sum == 1 || sum == 7) {
                return true;
            }
            if (sums.contains(sum) || sum <= 9) {
                return false;
            }
            sums.add(sum);
            currentNum = sum;
        }
    }*/

    static boolean isHappy(long a){
        if (a == 1 || a == 7) {
            return true;
        }
        long sum = 0;
        //get sum of squares of digits in sum
        while (a > 0) {
            long d = a % 10;
            sum += d * d;
            a /= 10;
        }
        if (sum <= 9 && sum!= 7 && sum != 1) {
            return false;
        }
        return isHappy(sum);
    }
    
    static boolean isSad(long a){
        return !isHappy(a);
    }

    static void printProperties(long i) {
        List<String> prop = new ArrayList<>();
        if (isEven(i)) prop.add("even");
        if (isOdd(i)) prop.add("odd");
        if (isBuzz(i)) prop.add("buzz");
        if (isDuck(i)) prop.add("duck");
        if (isPalindromic(i)) prop.add("palindromic");
        if (isGapful(i)) prop.add("gapful");
        if(isSpy(i)) prop.add("spy");
        if(isSquare(i)) prop.add("square");
        if(isSunny(i)) prop.add("sunny");
        if(isJumping(i)) prop.add("jumping");
        if(isHappy(i)) prop.add("happy");
        if(isSad(i)) prop.add("sad");


        System.out.println(i + " is " + String.join(", ", prop));
    }

    static void filterList(List<String> props, List<Long> nums) {

        for (int i = 0; i < props.size(); i++) {
            if (props.get(i).equalsIgnoreCase("even")) {
                nums.removeIf(x -> !isEven(x));
            } else if (props.get(i).equalsIgnoreCase("odd")) {
                nums.removeIf(x -> !isOdd(x));
            } else if (props.get(i).equalsIgnoreCase("buzz")) {
                nums.removeIf(x -> !isBuzz(x));
            } else if (props.get(i).equalsIgnoreCase("duck")) {
                nums.removeIf(x -> !isDuck(x));
            } else if (props.get(i).equalsIgnoreCase("palindromic")) {
                nums.removeIf(x -> !isPalindromic(x));
            } else if (props.get(i).equalsIgnoreCase("gapful")) {
                nums.removeIf(x -> !isGapful(x));
            } else if (props.get(i).equalsIgnoreCase("spy")) {
                nums.removeIf(x -> !isSpy(x));
            } else if (props.get(i).equalsIgnoreCase("square")) {
                nums.removeIf(x -> !isSquare(x));
            } else if (props.get(i).equalsIgnoreCase("sunny")) {
                nums.removeIf(x -> !isSunny(x));
            } else if (props.get(i).equalsIgnoreCase("jumping")) {
                nums.removeIf(x -> !isJumping(x));
            } else if (props.get(i).equalsIgnoreCase("happy")) {
                nums.removeIf(x -> !isHappy(x));
            } else if (props.get(i).equalsIgnoreCase("sad")) {
                nums.removeIf(x -> !isSad(x));
            }
            if (props.get(i).equalsIgnoreCase("-even")) {
                nums.removeIf(x -> isEven(x));
            } else if (props.get(i).equalsIgnoreCase("-odd")) {
                nums.removeIf(x -> isOdd(x));
            } else if (props.get(i).equalsIgnoreCase("-buzz")) {
                nums.removeIf(x -> isBuzz(x));
            } else if (props.get(i).equalsIgnoreCase("-duck")) {
                nums.removeIf(x -> isDuck(x));
            } else if (props.get(i).equalsIgnoreCase("-palindromic")) {
                nums.removeIf(x -> isPalindromic(x));
            } else if (props.get(i).equalsIgnoreCase("-gapful")) {
                nums.removeIf(x -> isGapful(x));
            } else if (props.get(i).equalsIgnoreCase("-spy")) {
                nums.removeIf(x -> isSpy(x));
            } else if (props.get(i).equalsIgnoreCase("-square")) {
                nums.removeIf(x -> isSquare(x));
            } else if (props.get(i).equalsIgnoreCase("-sunny")) {
                nums.removeIf(x -> isSunny(x));
            } else if (props.get(i).equalsIgnoreCase("-jumping")) {
                nums.removeIf(x -> isJumping(x));
            } else if (props.get(i).equalsIgnoreCase("-happy")) {
                nums.removeIf(x -> isHappy(x));
            } else if (props.get(i).equalsIgnoreCase("-sad")) {
                nums.removeIf(x -> isSad(x));
            }


        }
    }

}