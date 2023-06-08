package KioskApp;

import java.util.*;

public class KioskApp {
    static Scanner sc = new Scanner(System.in); //사용자 입력
    static Basket basket = new Basket();   //장바구니 생성
    static Order order = new Order();      //주문내역
    static boolean kioskStop = false;       //키오스크 종료 플래그
    static double TotalSalesAmount = 0;     //총 판매금액
    static int OrderId = 1;                //주문 시, 주문 id
    public static void main(String[] args) throws Exception {
        
        while (!kioskStop) {
            //first KioskApp.Menu Screen
            MainMenuScreen.run();
        }
        System.out.println("\u001B[33m" + "\"키오스크를 종료합니다.\"" + "\u001B[0m");
    }
    
    //Kiosk Manage App에서 주문리스트 받는 함수
    public static List<HashMap<Basket,List<String>>> getOrder() {
        return order.getOrder();
    }
}

