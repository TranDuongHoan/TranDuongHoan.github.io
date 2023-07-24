import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số nguyên dương: ");
        int n = scanner.nextInt();
        ArrayList<Integer> factors = new ArrayList<>();
        int i = 2;
        while (n >= 2) {
            if (n % i == 0) {
                factors.add(i);
                n /= i;
            } else {
                i++;
            }
        }
        System.out.print("Các thừa số nguyên tố là: ");
        for (int factor : factors) {
            System.out.print(factor + " ");
        }
    }
}

