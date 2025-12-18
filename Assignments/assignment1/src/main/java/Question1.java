

/* * Question 1 :
  *  A class sixth student required to solve basic mathematics problems. For this he/ she needs to
perform operations such as addition, subtraction, multiplication, division, remainder, square,
cube, and absolute. Write a program using methods to perform these basic operations.
* */

class Question1 {

    // addition
    public int add(int a, int b) {
        return a + b;
    }

    // subtraction
    public int subtract(int a, int b) {
        return a - b;
    }

    // multiplication
    public int multiply(int a, int b) {
        return a * b;
    }

    // division
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }

    //remainder
    public int remainder(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a % b;
    }

    // square
    public int square(int a) {
        return a * a;
    }

    // cube
    public int cube(int a) {
        return a * a * a;
    }

    // absolute
    public int absolute(int a) {
        return a < 0 ? -a : a;
    }

    public static void main(String[] args) {
        Question1 calculator = new Question1();

        System.out.println("Add: " + calculator.add(5, 3));
        System.out.println("Subtract: " + calculator.subtract(5, 3));
        System.out.println("Multi: " + calculator.multiply(5, 3));
        System.out.println("Div: " + calculator.divide(5, 3));
        System.out.println("Rem: " + calculator.remainder(5, 3));
        System.out.println("Sqr: " + calculator.square(5));
        System.out.println("Cube: " + calculator.cube(5));
        System.out.println("Abs: " + calculator.absolute(-5));
    }
}