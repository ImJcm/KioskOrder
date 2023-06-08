package KioskApp;

import java.util.Collections;
import java.util.Comparator;

public class MenuOrderScreen implements Screen {
    OrderScreen orderScreen = new OrderScreen();
    OrderStatusScreen orderStatusScreen = new OrderStatusScreen();

    @Override
    public void run(int OrderclickNum) throws Exception {
        Collections.sort(DataSet.ordermenulist, Comparator.comparing(Menu::getMid));
        switch (DataSet.ordermenulist.get(OrderclickNum).getMenuName()) {
            case "Order":
                orderScreen.run();
                break;
            case "OrderStatus":
                orderStatusScreen.run();
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