package logichandle;

import Eltity.Item;

import java.util.Scanner;

public class ItemLogicManagement {
    private Item[] items = new Item[1000];

    public void inputNewItem() {
        System.out.println("Bạn muốn thêm mới bao nhiêu mặt hàng: ");
        int itemsNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < itemsNumber; i++){
            System.out.println("Nhập thông tin mặt hàng thứ " + (i + 1));
            Item item = new Item();
            item.inputInfo();
            saveItem(item);
        }
    }

    public void saveItem(Item item) {
        for (int j = 0; j < items.length; j++) {
            if (items[j] == null){
                items[j] = item;
                break;
            }
        }
    }

    public void showItem(){
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null){
                System.out.println(items[i]);
            }
        }
    }

}
