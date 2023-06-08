package KioskApp;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderStatusScreen implements Screen{
    @Override
    public void run() {
        //OrderStatus Screen
        ScreenShow();
    }

    @Override
    public void ScreenShow() {
        //현재까지 주문된 Order들의 list를 모두 출력
        AtomicInteger index = new AtomicInteger(1);
        System.out.println("[ \u001B[32mOrderStatus list\u001B[0m ]");
        KioskApp.order.getOrder().forEach((HashMap<Basket, List<String>> map) -> {
            System.out.printf("[\u001B[33m Orders %2d ]\u001B[0m\n",index.getAndIncrement());
            map.forEach((Basket basket, List<String> info) -> {
                System.out.println("\u001B[32m주문ID\u001B[0m : " + info.get(0));
                basket.getBasket().forEach((Product p, Integer i) -> {
                    p.getProductlist(i);
                });
                System.out.println("\u001B[32m요청사항\u001B[0m : " + info.get(1));
                System.out.println("\u001B[32m주문일시\u001B[0m : " + info.get(2));
                System.out.print("\n");
            });
        });
    }
}
