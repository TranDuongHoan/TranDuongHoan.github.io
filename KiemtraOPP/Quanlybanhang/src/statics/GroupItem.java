package statics;

public enum GroupItem {
    DIEN_TU("Điện tử"),
    DIEN_LANH("Điện lạnh"),
    MAY_TINH("Máy tính");

    public String name;

    GroupItem(String name) {
        this.name = name;
    }
}
