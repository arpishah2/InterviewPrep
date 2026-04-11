package Recursion;/*
 * Fibonacci sequence: 1 , 1 , 2 , 3 , 5 , 8 , 13 , 21 , 34 , 55 , 89
 */

public class Fib {

    public static void main(String args[]) {
        Fib f = new Fib();
        int n = 5;
        for (int i = 0; i <= 5; i++) {
            System.out.print(f.calcFib(i) + "\t");
        }
        System.out.println();
        f.calcFibIter(5);
    }

    public int calcFib(int no) {
        int fibVal = 0;
        if (no <= 1)
            fibVal = no;
        else
            fibVal = calcFib(no - 1) + calcFib(no - 2);
        return fibVal;
    }

    public void calcFibIter(int no) {

        int val1 = 0, val2 = 0;
        for (int i = 0; i <= no; i++) {
            if (i <= 0) val1 = val2 = 0;
            else if (i <= 2) {
                val1 = 0;
                val2 = 1;
            } else {
                int temp = val2;
                val2 = val1 + val2;
                val1 = temp;
            }
            System.out.print("\t" + (val1 + val2));
        }
    }

}
