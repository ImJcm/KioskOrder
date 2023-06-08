package KioskApp;

import KioskManageProgram.KioskManageApp;

import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class MainMenuScreen {
    public static void run() throws Exception {
        //메뉴에 출력된 번호 외 입력 시 예외_1
        //숫자가 아닌 입력을 할 때 예외_2, NumberFormatException 발생 -> Exception으로 처리
        try {
            //Main KioskApp.Menu Screen
            MainMenuScreenShow();

            int clickNum = 0;
            clickNum = Integer.parseInt(KioskApp.sc.nextLine());
            /*
                0번 히든 메뉴 : 총 판매 금액 조회 및 출력 + 총 판매된 상품 목록 출력
                1 ~ N :
                    Shack KioskApp.Menu Numbers : 1~Shack KioskApp.Menu Size -1
                    KioskApp.Order KioskApp.Menu Numbers : Shack KioskApp.Menu Size ~ KioskApp.Order KioskApp.Menu Numbers Size
            */

            //메뉴의 상품출력하는 과정은 출력하는 내용이 리스트에 값에 따라만 달라지고 양식은 같아서 하나의 함수에 매개변수만 달리보내
            //동작시킬 수 있지만, KioskApp.Order 메뉴의 경우, 각 과정이 다른 화면을 출력하기 때문에 분기 or switch의 추가되는 것이 필수라고 생각한다.
            if(clickNum == -2) {
                //Kiosk Manage App 실행
                KioskManageApp.main();
            } else if(clickNum == -1) {
                //kioskApp 종료
                KioskApp.kioskStop = true;
            } else if(clickNum == 0) {
                //Hidden KioskApp.Menu
                HiddenMenuScreen.run();
            } else if (clickNum > 0 && clickNum <= DataSet.menulist.size()) {
                //KioskApp.Menu - shack menu
                MenuDetailScreen.run(DataSet.menulist.get(clickNum-1).getMid());
            } else if (clickNum > DataSet.menulist.size() && clickNum <= DataSet.menulist.size() + DataSet.ordermenulist.size()) {
                //OrderMenu
                MenuOrderScreen.run(clickNum-DataSet.menulist.size()-1);
            } else {
                //선택지 외 입력 값
                throw new Exception();
            }

            //switch로는 menu가 추가될 때 마다 case를 추가해야하는 작업이 필수이기 때문에, if-else isBetween 함수를 사용해서
            //코드 수정없이 동작하도록 구현하는게 좋은 것 같다.
            //switch - version (~5/30)
            /*if(clickNum == 0) {
                KioskApp.HiddenMenuScreen.run();
            } else {
                switch (KioskApp.DataSet.menulist.get(clickNum-1).getCategory()) {
                    case 1:
                        //ShackShackMenu
                        KioskApp.MenuDetailScreen.run(KioskApp.DataSet.menulist.get(clickNum-1).getMenuName());
                        break;
                    case 2:
                        //OrderMenu
                        if (KioskApp.DataSet.menulist.get(clickNum-1).getMenuName().equals("KioskApp.Order")) {
                            KioskApp.OrderScreen.run();
                        } else if (KioskApp.DataSet.menulist.get(clickNum-1).getMenuName().equals("Cancel")) {
                            KioskApp.KioskApp.basket.resetBasket();
                            System.out.println("\u001B[33m" + "\"진행중인 주문을 종료합니다.\"" + "\u001B[0m");
                        }
                        break;
                    case 3:
                        //KioskApp.KioskApp Power Off
                        KioskApp.KioskApp.orderStop = true;
                        break;
                    default:
                        throw new Exception();
                }
            }*/
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "\"옳바른 메뉴 입력이 아닙니다.\"" + "\u001B[0m");
            MainMenuScreen.run();
        }
    }


    private static void MainMenuScreenShow() {
        AtomicInteger index = new AtomicInteger(1);
        System.out.println("\u001B[32m" + "\"SHAKESHACK BURGER에 오신걸 환영합니다.\"" + "\u001B[0m");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");

        System.out.printf("[ \u001B[32mSHACKSHACK\u001B[0m MENU ]\n");
        Collections.sort(DataSet.menulist, Comparator.comparing(Menu::getMid));
        DataSet.menulist.stream().filter((Menu m) -> m.getCategory() == 1).forEach((Menu m) -> {
            System.out.printf("\u001B[31m%d\u001B[0m. ", index.getAndIncrement());
            //System.out.printf("\u001B[31m%d\u001B[0m. ", m.getMid());
            m.getMenulist();
        });

        System.out.println("\n[ \u001B[32mORDER\u001B[0m MENU ]");
        Collections.sort(DataSet.ordermenulist, Comparator.comparing(Menu::getMid));
        DataSet.ordermenulist.stream().filter((Menu m) -> m.getCategory() == 2).forEach((Menu m) -> {
            System.out.printf("\u001B[31m%d\u001B[0m. ", index.getAndIncrement());
            //System.out.printf("\u001B[31m%d\u001B[0m. ", m.getMid());
            m.getMenulist();
        });
    }
}
