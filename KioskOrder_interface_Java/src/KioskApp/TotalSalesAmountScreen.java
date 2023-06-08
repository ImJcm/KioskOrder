package KioskApp;

public class TotalSalesAmountScreen implements Screen {
    /*
        상품 주문완료 시, 주문한 금액을 kioskApp의 static 필드로 정의한 변수에 누적하여 더한 값을 출력한다.
        입력 버튼 창이 나오고 1번 클릭 시, 뒤로가기 진행
     */
    @Override
    public void run() throws Exception {
        try {
            //TotalSalesAmount notice
            ScreenShow();

            int TotalSalesclickNum = 0;
            TotalSalesclickNum = Integer.parseInt(KioskApp.sc.nextLine());

            switch (TotalSalesclickNum) {
                case 1:
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
        System.out.println("[ 총 판매금액 현황 ]");
        System.out.printf("현재까지 총 판매된 금액은 [ W " + "\u001B[31m%.1f\u001B[0m ] 입니다.\n\n",KioskApp.TotalSalesAmount);

        //뒤로가기 추가
        System.out.println("\u001B[31m1.\u001B[0m 뒤로가기\n");
    }
}
