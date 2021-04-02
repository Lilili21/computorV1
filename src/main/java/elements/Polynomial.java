package elements;

import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    private Map<Integer, Variable> elements = new HashMap<>();
    private int maxDegree = -1;
    private int minDegree = -1;

    public Map<Integer, Variable> getElements() {
        return elements;
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    public Polynomial(Map<Integer, Variable> elements) {
        this.elements = elements;
        countDegree();
    }

    private void countDegree(){
        int degree;

        for(Map.Entry<Integer, Variable> pair: elements.entrySet()){
            if (pair.getValue().getNumerator() != 0) {
                degree = pair.getValue().getDegree();
                this.maxDegree = Math.max(degree, maxDegree);
                this.minDegree = (minDegree == -1) ? degree : minDegree;
                this.minDegree = Math.min(minDegree, degree);
            }
        }
    }

    public void printDegree(){
        if (maxDegree > 0)
            System.out.printf("Polynomial degree: %d\n", maxDegree);
    }

    public void printReduceForm(){
        System.out.print("Reduced form: ");
        if (maxDegree == -1 && minDegree == -1) {
            System.out.print("0");
        }
        elements.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(this::printElem);
        System.out.println(" = 0");
    }

    private void printElem(Map.Entry<Integer, Variable> elements) {
        if (elements.getValue().getNumerator() != 0) {
            if (!(elements.getValue().getDegree() == minDegree && elements.getValue().getSign() == 1)) {
                elements.getValue().printSign();
            }
            elements.getValue().printVariable();
        }
    }
}
