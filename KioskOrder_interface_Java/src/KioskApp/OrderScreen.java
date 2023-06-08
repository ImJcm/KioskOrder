package KioskApp;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderScreen implements Screen {
    @Override
    public void run() throws Exception {
        try {
            //KioskApp.OrderScreen notice
            ScreenShow();

            int orderclickNum = 0;
            orderclickNum = Integer.parseInt(KioskApp.sc.nextLine());

            switch (orderclickNum) {
                case 1:
                    //요청사항 입력 = OrderRequest();
                    //주문일시(ISO 8601 UTC 날짜 및 시간) = OrderTime();
                    List<String> otherinfo = Arrays.asList(
                            new String(OrderId()),
                            new String(OrderRequest()),
                            new String(OrderTime())
                    );
                    KioskApp.order.addOrder(KioskApp.basket,otherinfo);
                    System.out.println("주문이 완료되었습니다.!\n");
                    //총 판매금액 액수 업데이트
                    KioskApp.TotalSalesAmount += KioskApp.basket.getTotalPrice();
                    
                    //장바구니 초기화
                    KioskApp.basket = new Basket();

                    System.out.printf("대기번호는 [\u001B[31m %d \u001B[0m] 번 입니다.\n", KioskApp.order.getOrder().size());
                    System.out.println("(\u001B[31m3\u001B[0m초후 메뉴판으로 돌아갑니다.)\n");
                    Thread.sleep(3000);
                    break;
                case 2:
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "\"옳바른 메뉴 입력이 아닙니다.\"" + "\u001B[0m");
            run();
        }
    }

    @Override
    public void ScreenShow() {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ \u001B[32mOrders\u001B[0m ]");
        KioskApp.basket.getBasketlist();

        System.out.println("[ \u001B[32mTotal\u001B[0m ]");
        System.out.printf("W \u001B[31m%.1f\u001B[0m\n\n", KioskApp.basket.getTotalPrice());

        System.out.println("\u001B[31m1.\u001B[0m 주문\t\t\u001B[31m2.\u001B[0m 메뉴판");
    }

    private static String OrderRequest() throws Exception {
        try {
            System.out.print("\n\u001B[32m요청사항을 입력해주세요. (20자 제한)\u001B[0m => ");
            String request = KioskApp.sc.nextLine();

            if(request.length() > 20) {
                throw new Exception();
            } else {
                return request;
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "\"요청사항이 20자가 넘어갑니다. 다시입력해주세요.\"" + "\u001B[0m");
            OrderRequest();
        }
        return null;
    }

    private static String OrderTime() {
        //return ZonedDateTime.now(ZoneId.of("UTC")).toString();
        //return ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString();
    }

    private static String OrderId() {
        return Integer.toString(KioskApp.OrderId++);
    }
}
