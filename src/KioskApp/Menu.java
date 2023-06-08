package KioskApp;

public class Menu {
    private int Mid;    //KioskApp.Menu 식별 id로, -1 : KioskApp.Product, 0 ~ N : KioskApp.Menu
    private int category;
    private String menuName;
    private String menuDescription;

    public Menu(String mName, String mDesc) {
        this.Mid = -1;
        this.category = 1;
        this.menuName = mName;
        this.menuDescription = mDesc;
    }

    public Menu(int Mid, int category, String mName, String mDesc) {
        this.Mid = Mid;
        this.category = category;
        this.menuName = mName;
        this.menuDescription = mDesc;
    }

    public int getMid() {
        return this.Mid;
    }
    public void getMenulist() {
        System.out.printf("%-20s \u001B[33m|\u001B[0m %s\n", this.menuName, this.menuDescription);
    }

    public int getCategory() {
        return this.category;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public String getMenuDescription() {
        return this.menuDescription;
    }

    public void setMenuName(String str) {
        this.menuName = str;
    }
}
