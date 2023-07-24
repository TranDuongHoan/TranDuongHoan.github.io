import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số phần tử của mảng: ");
        int n = scanner.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i+1) + ": ");
            int element = scanner.nextInt();
            a.add(element);
        }

        int[] countArray = new int[1000001];
        for (int i = 0; i < n; i++) {
            int key = a.get(i);
            countArray[key]++;
        }
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] > 0) {
                System.out.println(i + " xuất hiện " + countArray[i] + " lần");
            }
        }
    }
}


