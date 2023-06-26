package logichandle;

import Eltity.Item;
import Eltity.Saler;

import java.util.Scanner;

public class SalerLogicManagement {
    private Saler[] salers = new Saler[1000];

    public Saler[] getSalers() {
        return salers;
    }

    public void inputNewSaler() {
        System.out.println("Bạn muốn thêm mới bao nhiêu nhân viên bán hàng: ");
        int salersNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < salersNumber; i++){
            System.out.println("Nhập thông tin nhân viên thứ " + (i + 1));
            Saler salers = new Saler();
            salers.inputInfo();
            saveSaler(salers);
        }
    }

    public void saveSaler(Saler saler) {
        for (int j = 0; j < salers.length; j++) {
            if (salers[j] == null){
                salers[j] = saler;
                break;
            }
        }
    }

    public void showSaler(){
        for (int i = 0; i < salers.length; i++) {
            if (salers[i] != null){
                System.out.println(salers[i]);
            }
        }
    }
}
