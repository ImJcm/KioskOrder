package KioskApp;

import java.util.HashMap;

public class Basket {
    //주문개수 기능을 추가하기 위해 장바구니 자료구조를 변경할 필요가 있다.
    //private List<KioskApp.Product> shoppingBasket;

    //같은 상품의 경우, count 개수를 늘리기 위해 HashMap 자료구조를 사용하는 것이 좋다고 생각한다.
    private HashMap<Product,Integer> shoppingBasket;

    Basket() {
        //this.shoppingBasket = new ArrayList<>();
        this.shoppingBasket = new HashMap<>();
    }

    public HashMap<Product,Integer> getBasket() {
        return this.shoppingBasket;
    }

    public void addBasket(Product p) {
        //shoppingBasket.add(p);
        //상품 p에 대해서 갯수를 가져오는데 없을 경우 디폴트값으로 0을 주고 +1 한다.
        //this.shoppingBasket.put(p,this.shoppingBasket.getOrDefault(p,0)+1);
        //Product의 객체 주소를 기준으로 Key를 잡기때문에 이름과 가격이 변경되는 옵션을 적용하면 중복상품으로 인식하지 못한다.
        //따라서, KioskApp.Product MenuName을 비교해서 카운팅해주는 방법을 이용한다.
        for(Product tmp_p : this.shoppingBasket.keySet()) {
            if(tmp_p.getMenuName().equals(p.getMenuName())) {
                this.shoppingBasket.put(tmp_p,this.shoppingBasket.get(tmp_p)+1);
                //중복되는 Key는 없기때문에 바로 tmp_p에 해당하는 Key의 value를 +1하고 return;
                return;
            }
        }
        //반복문 나온 경우, shoppingBasket에 없다는 의미이므로, put
        this.shoppingBasket.put(p,this.shoppingBasket.getOrDefault(p,0)+1);
    }

    public void resetBasket() {
        this.shoppingBasket.clear();
    }

    public void getBasketlist() {
        //shoppingBasket.forEach((KioskApp.Product p) -> p.getProductlist());
        this.shoppingBasket.forEach(((product, i) -> product.getProductlist(i)));
    }

    public double getTotalPrice() {
        //return shoppingBasket.stream().mapToDouble(KioskApp.Product::getPrice).sum();
        return this.shoppingBasket.entrySet().stream().mapToDouble((p) -> p.getKey().getPrice() * p.getValue()).sum();
    }
}
