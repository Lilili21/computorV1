package utils;

import elements.Flags;
import elements.Variable;
import exeption.ParseProblemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static String parsePolynomialString(String[] args){
        if (args.length == 1)
            return args[0].replace(" ","");
        try {
            System.out.println("please enter polynomial on the keyboard");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String result = reader.readLine();
            if (result.length() < 1)
                throw new ParseProblemException("ParseException there is no polynomial entered");
            return result.replace(" ","").toUpperCase();
        } catch (IOException e) {
            throw new ParseProblemException("ParseException there is no polynomial entered");
        }
    }

    private static Variable chooseElement(String polynomial, Flags flags) throws NumberFormatException{
        Variable newVariable = new Variable();
        switch (polynomial.charAt(flags.getPos())) {
            case '+':
                newVariable.parseVariable(polynomial.substring(flags.getStart(), flags.getPos()),
                        flags.signResult());
                flags.movePos(1);
                break;
            case '-':
                if (flags.getPos() == 0 || polynomial.charAt(flags.getPos() - 1) == '=') {
                    flags.movePos(-1);
                    break;
                }
                newVariable.parseVariable(polynomial.substring(flags.getStart(), flags.getPos()),
                                flags.signResult());
                flags.movePos(-1);
                break;
            case '=':
                newVariable.parseVariable(polynomial.substring(flags.getStart(), flags.getPos() ),
                                flags.signResult());
                flags.setSignAfterEqual(-1);
                flags.movePos(1);
                break;
            default:
                if (flags.getPos()  == polynomial.length() - 1) {
                    newVariable.parseVariable(polynomial.substring(flags.getStart(), flags.getPos() + 1),
                            flags.signResult());
                }
        }
        return newVariable;
    }

    private static void addVariableToElements(Variable addVariable, Map<Integer, Variable> elements) {
        if (elements.containsKey(addVariable.getDegree())) {
            elements.get(addVariable.getDegree()).addVariable(addVariable);
        } else {
            elements.put(addVariable.getDegree(), addVariable);
        }
    }

    public static Map<Integer, Variable> parsePolynomialElements(String[] args) {
        String polynomialString = Utils.parsePolynomialString(args);
        System.out.println("Polynomial: " + polynomialString);

        Flags flags = new Flags(0, 0, 1, 1);
        Variable newVariable;
        Map<Integer, Variable> elements = new HashMap<>();

        checkPolynomialString(polynomialString);

        while (flags.getPos() < polynomialString.length()) {
            newVariable = chooseElement(polynomialString, flags);
            if (newVariable.getNumerator() != 0)
                addVariableToElements(newVariable, elements);
            flags.enlargePos();
        }

        return elements;
    }

    private static void checkPolynomialString(String polynomialString) {
        if (polynomialString.charAt(0) == '+')
            throw new ParseProblemException("ParseException \"+\" in a wrong place");
        if (!polynomialString.contains("="))
            throw new ParseProblemException("ParseException there is no \"=\" in polynomial");
        if (polynomialString.split("=").length != 2)
            throw new ParseProblemException("ParseException more than one \"=\"");
    }
}
