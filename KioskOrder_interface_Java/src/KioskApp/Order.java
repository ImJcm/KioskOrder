package KioskApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private List<HashMap<Basket, List<String>>> orders;

    Order() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Basket basket,List<String> info) {
        this.orders.add(new HashMap<>() {{
            put(basket,info);
        }});
    }

    public void deleteOrder(int index) {
        this.orders.remove(index);
    }

    public List<HashMap<Basket,List<String>>> getOrder() {
        return this.orders;
    }
}
