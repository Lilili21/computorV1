import elements.Polynomial;
import utils.Solution;
import elements.Variable;
import exeption.ParseProblemException;
import exeption.SolutionProblemException;
import utils.Utils;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        try {
            Map<Integer, Variable> elements = Utils.parsePolynomialElements(args);
            Polynomial polynomial = new Polynomial(elements);

            polynomial.printReduceForm();
            polynomial.printDegree();
            Solution.solve(polynomial);
        } catch (ParseProblemException | SolutionProblemException e) {
            System.out.println(e.getMessage());
        }
    }
}
