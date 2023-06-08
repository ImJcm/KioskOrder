package KioskApp;

import java.util.Collections;
import java.util.Comparator;

public class HiddenMenuScreen implements Screen {
    TotalSalesAmountScreen totalSalesAmountScreen = new TotalSalesAmountScreen();
    TotalSaleslistScreen totalSaleslistScreen = new TotalSaleslistScreen();
    @Override
    public void run() throws Exception {
        try {
            //Hidden KioskApp.Menu Screen
            ScreenShow();

            int hiddenMenuclickNum = 0;
            hiddenMenuclickNum = Integer.parseInt(KioskApp.sc.nextLine());

            switch (hiddenMenuclickNum) {
                case 1:
                    totalSalesAmountScreen.run();
                    break;
                case 2:
                    totalSaleslistScreen.run();
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
        System.out.println("\n[ \u001B[32mHidden KioskApp.Menu\u001B[0m ]");
        Collections.sort(DataSet.hiddenmenulist, Comparator.comparing(Menu::getMid));
        DataSet.hiddenmenulist.stream().filter((Menu m) -> m.getCategory() == 0).forEach((Menu m) -> {
            System.out.printf("\u001B[31m%d\u001B[0m. ", m.getMid());
            m.getMenulist();
        });
    }
}
