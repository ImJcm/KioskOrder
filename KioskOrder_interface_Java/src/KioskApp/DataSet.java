package KioskApp;

import java.util.*;

public class DataSet {
    public static List<Menu> hiddenmenulist = new ArrayList<Menu>() {{
        add(new Menu(1,0,"TotalSalesAmount","총 판매금액을 출력합니다."));
        add(new Menu(2,0,"TotalSalesList","총 판매상품 목록 조회합니다."));
    }};

    //메뉴 리스트 객체 정보 저장
    public static List<Menu> menulist = new ArrayList<Menu>() {{
        add(new Menu(1,1, "Burgers", "앵거스 비프 통살을 다져만든 버거"));
        add(new Menu(2,1, "Forzen Custard", "매장에서 신선하게 만드는 아이스크림"));
        add(new Menu(3,1, "Drinks", "매장에서 직접 만드는 음료"));
        add(new Menu(4,1, "Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주"));
        add(new Menu(5,1,"Pizza","각종 치즈를 사용한 오븐에 구운 피자"));
    }};

    //오더 메뉴 리스트 객체 저장
    public static List<Menu> ordermenulist = new ArrayList<Menu>() {{
        add(new Menu(1,2, "Order", "장바구니를 확인 후 주문합니다."));
        add(new Menu(2,2,"OrderStatus","주문현황을 출력합니다."));
        add(new Menu(3,2, "Cancel", "진행중인 주문을 취소합니다."));
    }};

    //상품 리스트 객체 저장
    public static List<Product> productslist = new ArrayList<Product>() {{
        add(new Product(1, 1, "ShackBurger", 6.9, "토마토, 양상추, 쉣소스가 토핑된 치즈버거"));
        add(new Product(2, 1, "SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        add(new Product(3, 1, "Shroom Burger", 9.4, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"));
        add(new Product(4, 1, "Cheeseburger", 7.0, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        add(new Product(5, 1, "Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
        add(new Product(1, 2, "chocoIceCream", 1.0, "우유베이스 아이스크림에 초코무스를 듬뿍 얻은 초코아이스크림"));
        add(new Product(1, 3, "Ice Americano", 1.5, "아이스 아메리카노"));
        add(new Product(1, 4, "Draft Beer", 5.0, "효모가 살아있는 생맥주"));
    }};

    //상품 옵션 리스트 객체 저장
    public static List<Option> productOptionlist = new ArrayList<Option>() {{
        add(new Option(1, 1, "ShackBurger", 6.9, "토마토, 양상추, 쉣소스가 토핑된 치즈버거", "Single",6.9));
        add(new Option(2, 1, "ShackBurger", 6.9, "토마토, 양상추, 쉣소스가 토핑된 치즈버거", "Double",10.3));
        add(new Option(1, 1, "SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거","Single",8.9));
        add(new Option(2, 1, "SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거","Double",13.3));
        add(new Option(1, 1, "Shroom Burger", 9.4, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거","Single",9.4));
        add(new Option(2, 1, "Shroom Burger", 9.4, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거","Double",14.0));
        add(new Option(1, 1, "Cheeseburger", 7.0, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거","Single",7.0));
        add(new Option(2, 1, "Cheeseburger", 7.0, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거","Double",10.5));
        add(new Option(1, 1, "Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거","Single",5.4));
        add(new Option(2, 1, "Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거","Double",8.1));
        add(new Option(1, 2, "chocoIceCream", 1.0, "우유베이스 아이스크림에 초코무스를 듬뿍 얻은 초코아이스크림","콘",1.0));
        add(new Option(2, 2, "chocoIceCream", 1.0, "우유베이스 아이스크림에 초코무스를 듬뿍 얻은 초코아이스크림","컵",1.5));
        add(new Option(1, 3, "Ice Americano", 1.5, "아이스 아메리카노","M",1.5));
        add(new Option(2, 3, "Ice Americano", 1.5, "아이스 아메리카노","L",2.0));
        add(new Option(1, 4, "Draft Beer", 5.0, "효모가 살아있는 생맥주","500cc",5.0));
        add(new Option(2, 4, "Draft Beer", 5.0, "효모가 살아있는 생맥주","1000cc",8.0));
    }};
}
