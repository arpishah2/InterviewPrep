package Recursion;/* Write a program that prints the numbers from 1 to 100.
 * But for multiples of three print “Fizz” instead of the number and
 * for the multiples of five print “Buzz”.
 * For numbers which are multiples of both three and five print “Recursion.FizzBuzz”."
 */

public class FizzBuzz {

    public static void main(String args[]) {
        FizzBuzz fb = new FizzBuzz();
		/*for(int i=1; i<100; i++){
			fb.fizzBuzz(i);
		}*/

        fb.fizzBuzzRecu(15);
    }

    public void fizzBuzz(int number) {
        if (number % 15 == 0)
            System.out.println("Recursion.FizzBuzz");
        else if (number % 3 == 0)
            System.out.println("Fizz");
        else if (number % 5 == 0)
            System.out.println("Buzz");
        else
            System.out.println(number);
    }

    public void fizzBuzzRecu(int number) {

        if (number == 0)
            return;

        fizzBuzzRecu(number - 1);

        if (number % 15 == 0)
            System.out.println("Recursion.FizzBuzz");
        else if (number % 3 == 0)
            System.out.println("Fizz");
        else if (number % 5 == 0)
            System.out.println("Buzz");
        else
            System.out.println(number);
    }

}
