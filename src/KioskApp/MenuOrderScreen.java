package KioskApp;

import java.util.Collections;
import java.util.Comparator;

public class MenuOrderScreen {
    public static void run(int OrderclickNum) throws Exception {
        //KioskApp.Order KioskApp.Menu list Screen
        //Collections.sort(DataSet.ordermenulist,(Menu m1, Menu m2) -> m1.getMid() - m2.getMid());
        Collections.sort(DataSet.ordermenulist, Comparator.comparing(Menu::getMid));
        switch (DataSet.ordermenulist.get(OrderclickNum).getMenuName()) {
            case "Order":
                OrderScreen.run();
                break;
            case "OrderStatus":
                OrderStatusScreen.run();
                break;
            case "Cancel":
                KioskApp.basket.resetBasket();
                System.out.println("\u001B[33m" + "\"진행중인 주문을 종료합니다.\"" + "\u001B[0m");
                break;
            default:
                throw new Exception();
        }
    }
}