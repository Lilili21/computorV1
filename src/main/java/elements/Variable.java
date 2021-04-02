package elements;

import exeption.ParseProblemException;

public class Variable {
    private double numerator;
    private int sign; //1 - positive, -1 - negative

    public Variable() {
    }

    public double getNumerator() {
        return numerator;
    }

    public int getSign() {
        return sign;
    }

    public int getDegree() {
        return degree;
    }

    private int degree;

    public void parseVariable(String elem, int sign){

        try {
            if (elem.contains(".")) {
                this.numerator = Double.parseDouble(elem.substring(0, elem.indexOf("*")));
            } else {
                if (elem.contains("*")) {
                    this.numerator = Integer.parseInt(elem.substring(0, elem.indexOf("*")));
                } else {
                    this.numerator = (elem.contains("X")) ? 1 : Integer.parseInt(elem);
                }
            }
            if (!elem.contains("X")) {
                this.degree = 0;
            } else {
                if (elem.contains("^")) {
                    this.degree = Integer.parseInt(elem.substring(elem.indexOf("X") + 2));
                } else {
                    this.degree = 1;
                }
            }
            this.sign = sign;
        } catch (NumberFormatException e) {
            throw new ParseProblemException("ParseException not correct polynomial syntax");
        }
    }

    public void addVariable(Variable addVariable) throws ParseProblemException {
        int sign = this.sign * addVariable.getSign();

        this.numerator += sign * addVariable.getNumerator();

        if (this.numerator < 0) {
            this.sign *= -1;
            this.numerator *= -1;
        }
    }

    public double divideVariable(Variable dividerVariable){
        double result = this.numerator / dividerVariable.numerator;
        if (this.getSign() == dividerVariable.getSign())
            return -1 * result;
        return result;
    }

    public void printVariable(){
        System.out.print(numerator);
        System.out.printf(" * X ^ %d", degree);
    }

    public void printSign(){
        if (sign == 1) {
            System.out.print(" + ");
        } else {
            System.out.print(" - ");
        }
    }
}
