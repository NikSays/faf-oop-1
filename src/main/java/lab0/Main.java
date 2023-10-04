package lab0;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MathUtils mc = new MathUtils();
        Scanner sc = new Scanner(System.in);    //System.in is a standard input stream  

        System.out.print("Enter number to check: ");
        int num = sc.nextInt();
        if (mc.isNarcissistic(num)) {
            System.out.println("Narcissistic");
        } else {
            System.out.println("Not narcissistic");
        }
    }
}
