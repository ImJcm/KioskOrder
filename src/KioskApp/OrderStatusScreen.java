package KioskApp;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderStatusScreen {
    public static void run() {
        //OrderStatus Screen

        //현재까지 주문된 Order들의 list를 모두 출력
        AtomicInteger index = new AtomicInteger(1);
        System.out.println("[ \u001B[32mOrderStatus list\u001B[0m ]");
        KioskApp.order.getOrder().forEach((HashMap<Basket, List<String>> map) -> {
            System.out.printf("[\u001B[33m Orders %2d ]\u001B[0m\n",index.getAndIncrement());
            /*
            //entrySet - Map.Entry 객체로 받는 방법
            for(Map.Entry<KioskApp.Basket,List<String>> e : map.entrySet()) {
                e.getKey().getBasket().forEach((KioskApp.Product p, Integer i) -> {
                    p.getProductlist(i);
                });
                System.out.println("\u001B[32m요청사항\u001B[0m : " + e.getValue().get(0).toString());
                System.out.println("\u001B[32m주문일시\u001B[0m : " + e.getValue().get(1).toString());
                System.out.print("\n\n");
            }
             */
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
