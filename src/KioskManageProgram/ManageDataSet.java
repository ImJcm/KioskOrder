package KioskManageProgram;

import KioskApp.Basket;
import KioskApp.Menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageDataSet {
    static List<Menu> mainManageMenus = Arrays.asList(
            new Menu(1,2,"대기주문 목록", "대기주문 목록을 출력한다."),
            new Menu(2,2,"완료주문 목록", "완료주문 목록을 출력한다."),
            new Menu(3,2,"상품 생성", "상품을 생성한다."),
            new Menu(4,2,"상품 삭제", "상품을 삭제한다."),
            new Menu(5,2,"상품 옵션 수정", "상품옵션을 수정한다.")
    );

    static Map<String, List<Menu>> menus;   //메뉴와 메뉴세부정보를 저장
    static List<HashMap<Basket, List<String>>> order;    //대기주문
    static List<HashMap<Basket, List<String>>> completeorder;  //대기주문 중 완료 처리된 주문
}
