package utils;

public class Math {

    public static double sqrt(double number) {
        double t;
        double squareRoot = number / 2;
        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);
        return squareRoot;
    }

    public static int min(int a, int b) {
        return (a > b) ? b : a;
    }

    public static int max(int a, int b) {
        return (a < b) ? b : a;
    }
}
