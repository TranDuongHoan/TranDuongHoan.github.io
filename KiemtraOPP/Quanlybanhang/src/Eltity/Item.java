package Eltity;

import java.util.Scanner;

public class Item implements InputInfo{
    private static int AUTO_id = 1000;
    public int length;
    private int id;

    private String name;
    private String GroupItem;
    private double price;
    private double quantity;

    public Item() {
        this.id = AUTO_id;
        AUTO_id++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupItem() {
        return GroupItem;
    }

    public void setGroupItem(String groupItems) {
        GroupItem = groupItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", GroupItem='" + GroupItem + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
    @Override
    public void inputInfo() {
        System.out.println("Mã mặt hàng: ");
        this.setId(new Scanner(System.in).nextInt());
        System.out.println("Tên mặt hàng: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.println("Nhóm hàng: ");
        this.setGroupItem(new Scanner(System.in).nextLine());
        System.out.println("Giá bán: ");
        this.setPrice(new Scanner(System.in).nextDouble());
        System.out.println("Khối lượng: ");
        this.setQuantity(new Scanner(System.in).nextDouble());
    }
}
