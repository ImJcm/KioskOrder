package KioskApp;

import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MenuDetailScreen {
    private static List<Product> filterProductlist;
    public static void run(int productcategory) throws Exception {
        //상세메뉴에 출력된 번호 외 입력 시 예외_1
        //숫자가 아닌 입력을 할 때 예외_2, NumberFormatException 발생 -> Exception으로 처리
        try {
            //KioskApp.MenuDetailScreen notice
            MenuDetailScreenShow(productcategory);

            int DetailclickNum = 0;
            DetailclickNum = Integer.parseInt(KioskApp.sc.nextLine());

            //뒤로가기
            if (DetailclickNum == filterProductlist.size() + 1) {
                return;
            }
            BuyScreen.run(filterProductlist.get(DetailclickNum - 1));
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "\"옳바른 메뉴 입력이 아닙니다.\"" + "\u001B[0m");
            MenuDetailScreen.run(productcategory);
        }
    }

    private static void MenuDetailScreenShow(int productcategory) {
        //메뉴 상품 리스트 생성
        System.out.println("\u001B[32m" + "\"SHAKESHACK BURGER에 오신걸 환영합니다.\"" + "\u001B[0m");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");

        System.out.printf("[ \u001B[32m%s\u001B[0m MENU ]\n", DataSet.menulist.stream().filter((menu -> menu.getMid() == productcategory)).toList().get(0).getMenuName());

        Collections.sort(DataSet.productslist, (p1, p2) -> {
            if(p1.getProductcategory() == p2.getProductcategory()) {
                return p1.getPid() - p2.getPid();
            }
            return p1.getProductcategory() - p2.getProductcategory();
        });
        /*filterProductlist = DataSet.productslist.stream().filter((Product p)
                -> p.getProductcategory() == productcategory).sorted(Comparator.comparing(Product::getPid)).toList();*/
        filterProductlist = DataSet.productslist.stream().filter((Product p)
                -> p.getProductcategory() == productcategory).toList();
        filterProductlist.forEach((Product p) -> {
            System.out.printf("\u001B[31m%2d\u001B[0m. ", DataSet.productslist.indexOf(p)+1);
            p.getProductlist();
        });

        //뒤로가기 추가
        System.out.printf("\u001B[31m%2d\u001B[0m. 뒤로가기\n", filterProductlist.size() + 1);
    }
}
