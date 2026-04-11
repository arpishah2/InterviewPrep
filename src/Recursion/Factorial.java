package Recursion;

public class Factorial {

    public static void main(String[] args) {
        Factorial fact = new Factorial();
        System.out.println(fact.calcFact(-1));
        fact.calcFacIter(5);
    }

    public int calcFact(int no) {
        if (no <= 1)
            return 1;
        else
            return no * calcFact(no - 1);
    }

    public void calcFacIter(int no) {
        int fact = 1;
        for (int i = 1; i <= no; i++) {
            fact = fact * i;
        }
        System.out.println(fact);
    }
}
