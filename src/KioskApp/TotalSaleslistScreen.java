package KioskApp;

import java.util.HashMap;
import java.util.List;

public class TotalSaleslistScreen {
    private static HashMap<Product, Integer> totalsaleslist = new HashMap<>();
    public static void run() {
        try {
            //TotalSaleslist Notice
            TotalSaleslistScreenShow();

            int TotalSaleslistclickNum = 0;
            TotalSaleslistclickNum = Integer.parseInt(KioskApp.sc.nextLine());

            switch (TotalSaleslistclickNum) {
                case 1:
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "\"옳바른 메뉴 입력이 아닙니다.\"" + "\u001B[0m");
            TotalSaleslistScreen.run();
        }
    }

    private static void TotalSaleslistScreenShow() {
        //기존에 있는 KioskApp.KioskApp.order.getOrderlist();를 사용하여 판매 번호 별로 판매 상품들을 출력하기 때문에 사용하면 되지만
        //양식이 다르기 때문에 재구성
        System.out.println("[ 총 판매상품 목록 현황 ]");
        System.out.printf("현재까지 총 판매된 상품 목록은 아래와 같습니다.\n\n");

        //Make Sales list
        makeOrderlist();

        //뒤로가기 추가
        System.out.println("\u001B[31m1.\u001B[0m 뒤로가기\n");
    }

    private static void makeOrderlist() {
        //판매 목록 리스트 만들기
        KioskApp.order.getOrder().forEach((HashMap<Basket, List<String>> map) -> {
            map.forEach((Basket b, List<String> info) -> {
                b.getBasket().forEach((Product p, Integer i) -> {
                    boolean flag = true;
                    for(Product p2 : totalsaleslist.keySet()) {
                        if(p2.getMenuName().equals(p.getMenuName())) {
                            totalsaleslist.put(p2,totalsaleslist.get(p2)+i);
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        totalsaleslist.put(p,totalsaleslist.getOrDefault(p,0)+i);
                    }
                });
            });
        });

        //만든 판매 목록 리스트 출력
        totalsaleslist.forEach((p, i) -> {
            System.out.println(String.format("- %-20s \u001B[33m|\u001B[0m \u001B[33m%d개\u001B[0m \u001B[33m|\u001B[0m W \u001B[31m%.1f\u001B[0m",p.getMenuName(),i,p.getPrice()));
        });
        System.out.println("\n");
    }
}
