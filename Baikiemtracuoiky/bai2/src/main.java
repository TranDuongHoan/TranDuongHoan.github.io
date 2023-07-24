import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("nhập c =");
        double c = scanner.nextDouble();
        double p = 0;
        int n = 0;
        double term = 1;
        while (Math.abs(term) > c) {
            p += term;
            n++;
            term = Math.pow(-1, n) * 1 / (2*n + 1);
        }
        p *= 4;
        System.out.println("p = " + p);
    }
}
