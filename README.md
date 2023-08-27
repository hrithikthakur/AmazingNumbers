## Amazing Numbers Java Program

Welcome to the "Amazing Numbers" Java program readme! This program allows you to explore various properties of natural numbers, helping you discover interesting patterns and characteristics.

### Overview

This Java program lets you interactively explore properties of natural numbers. You can perform the following actions using the program:

1. Enter a natural number to know its properties.
2. Enter two natural numbers to obtain the properties of a list of consecutive numbers:
   - The first parameter represents a starting number.
   - The second parameter indicates how many consecutive numbers should be processed.
3. Enter two natural numbers and specify properties to search for.
   - A property preceded by a minus sign ("-") must not be present in the numbers.
4. You can separate the parameters with a single space.
5. Enter 0 to exit the program.

### Getting Started

1. Compile the Java program:
   ```sh
   javac Main.java
   ```
2. Run the program:
   ```sh
   java Main
   ```

### Instructions

1. Upon running the program, you'll be presented with a list of supported requests and instructions.
2. Enter your request according to the guidelines provided.
3. The program will process your request and provide relevant information about the numbers.

### Example Usages

1. **Single Number Properties:**
   - Enter a single natural number, and the program will display various properties of that number.

2. **List of Consecutive Numbers:**
   - Enter two natural numbers separated by a space. The first number is the starting point, and the second number indicates how many consecutive numbers should be processed. The program will display properties for each of these numbers.

3. **Searching for Properties:**
   - Enter two natural numbers followed by specific properties you're interested in. The program will provide numbers that match those properties.

### Properties

The program provides information about the following properties of a number:

- `even`: Checks if the number is even.
- `odd`: Checks if the number is odd.
- `buzz`: Checks if the number is divisible by 7 or ends with 7.
- `duck`: Checks if the number contains the digit 0.
- `palindromic`: Checks if the number reads the same forwards and backwards.
- `gapful`: Checks if the number is "gapful," meaning it's divisible by a two-digit concatenation of its first and last digits.
- `spy`: Checks if the sum of the digits is equal to the product of the digits.
- `square`: Checks if the number is a perfect square.
- `sunny`: Checks if the number is followed by a perfect square.
- `jumping`: Checks if the digits alternate in an increasing or decreasing pattern.
- `happy`: Checks if the number is a "happy" number, meaning the sum of the squares of its digits eventually leads to 1.
- `sad`: The opposite of a happy number.

### Exiting the Program

To exit the program, simply enter 0. The program will display a goodbye message and terminate.

### Note

- The program uses regular expressions to validate input, so please follow the instructions precisely.
- The program employs recursion to check for "happy" and "sad" numbers.

Feel free to experiment and discover interesting properties of numbers using this "Amazing Numbers" Java program!
