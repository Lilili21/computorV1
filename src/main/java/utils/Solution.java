package utils;

import elements.Polynomial;
import exeption.SolutionProblemException;

import static utils.Math.*;

public class Solution {

    public static void solve(Polynomial polynomial){
        switch (polynomial.getMaxDegree()) {
            case -1:
                System.out.println("All real numbers are solution.");
                break;
            case 0:
                throw new SolutionProblemException("The polynomial couldn't be solved, the equation is wrong.");
            case 1:
                solveFirstDegree(polynomial);
                break;
            case 2:
                solveSecondDegree(polynomial);
                break;
            default:
                throw new SolutionProblemException("The polynomial degree is stricly greater than 2, I can't solve.");
        }
    }

    private static void solveSecondDegree(Polynomial polynomial) {
        if (polynomial.getElements().size() == 1) {
            System.out.println("The solutions is:\n0");
        } else if (polynomial.getElements().size() == 2) {
            solveEasy(polynomial);
        } else {
            solveHarder(polynomial);
        }
    }

    private static void solveHarder(Polynomial polynomial) {
        double a = polynomial.getElements().get(2).getNumerator() * polynomial.getElements().get(2).getSign();
        double b = polynomial.getElements().get(1).getNumerator() * polynomial.getElements().get(1).getSign();
        double c = polynomial.getElements().get(0).getNumerator() * polynomial.getElements().get(0).getSign();
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0)
            throw new SolutionProblemException("Discriminant is less than zero");
        else if (discriminant == 0) {
            System.out.println("Discriminant is zero, solutions is:");
            System.out.println(-b / (2 * a));
        } else {
            System.out.println("Discriminant is strictly positive, the two solutions are:");
            System.out.println((-b + sqrt(discriminant)) / (2 * a));
            System.out.println((-b - sqrt(discriminant)) / (2 * a));
        }
    }

    private static void solveEasy(Polynomial polynomial) {
        if (polynomial.getElements().containsKey(1)){
            System.out.println("The two solutions are:");
            System.out.println(polynomial.getElements().get(1).divideVariable(polynomial.getElements().get(2)));
            System.out.println("0");
        } else {
            double discriminant = polynomial.getElements().get(0).divideVariable(polynomial.getElements().get(2));
            if (discriminant < 0)
                throw new SolutionProblemException("Discriminant is less than zero");
            System.out.println("The two solutions are:");
            double result = java.lang.Math.sqrt(discriminant);
            System.out.println(-1 * result);
            System.out.println(result);
        }
    }

    private static void solveFirstDegree(Polynomial polynomial) {
        System.out.println("The solution is:");
        if (polynomial.getElements().containsKey(0) && polynomial.getElements().containsKey(1)) {
            System.out.println(polynomial.getElements().get(0).divideVariable(polynomial.getElements().get(1)));
        } else {
            System.out.println("0");
        }
    }
}
