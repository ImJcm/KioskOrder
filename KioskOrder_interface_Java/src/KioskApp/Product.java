package KioskApp;

public class Product extends Menu {
    private int pid;
    private int productcategory;
    private double price;
    //상품마다 옵션이 다르기 때문에 Product클래스에 option 속성을 추가 - option - field Version
    //private List<String> option = null;

    //객체복사 생성자
    public Product(Product p) {
        super(p.getMenuName(),p.getMenuDescription());
        //this.option = p.getOption();
        this.pid = p.getPid();
        this.productcategory = p.getProductcategory();
        this.price = p.getPrice();
    }

    public Product(int pid, int productcategory, String pname, double p, String pdesc) {
        super(pname,pdesc);
        this.productcategory = productcategory;
        this.pid = pid;
        this.price = p;
    }

    public int getPid() {
        return this.pid;
    }

    public int getProductcategory() {
        return this.productcategory;
    }

    public double getPrice() {
        return this.price;
    }

    public void getProductlist() {
        //Print pid, MenuName, Price, KioskApp.Menu Description
        System.out.printf("%-20s \u001B[33m|\u001B[0m W \u001B[31m%-3.1f\u001B[0m \u001B[33m|\u001B[0m %s\n",this.getMenuName(), this.getPrice(), this.getMenuDescription());
    }

    public void getProductlist(int cnt) {
        //Print MenuName, Price, cnt, MenuDescription
        System.out.printf("%-20s \u001B[33m|\u001B[0m W \u001B[31m%-3.1f\u001B[0m \u001B[33m|\u001B[0m \u001B[31m%d\u001B[0m개 \u001B[33m|\u001B[0m %s\n",this.getMenuName(), this.getPrice(), cnt, this.getMenuDescription());
    }

    public void setPrice(double p) {
        this.price = p;
    }
}
