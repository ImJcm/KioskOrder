package KioskApp;

public class BuyScreen implements Screen {
    private static Product optProduct;
    OptionScreen optionScreen = new OptionScreen();

    @Override
    public void run(Product product) throws Exception {
        //상품선택에 출력된 번호 외 입력 시 예외_1
        //숫자가 아닌 입력을 할 때 예외_2, NumberFormatException 발생 -> Exception으로 처리
        try {
            //product buy Screen
            //객체의 주소가 새로 생성되는 것이기 때문에, basket에 addBasket에서 HashMap에 객체의 참조 주소를 기준으로 Key를 잡기 때문에
            //중복 상품으로 인식하지 못한다. 따라서, addBasket의 코드를 변경해야한다. address -> MenuName으로 변경하는 것이 좋아보인다.
            ScreenShow(product);

            int buyclickNum = Integer.parseInt(KioskApp.sc.nextLine());
            switch (buyclickNum) {
                case 1:
                    KioskApp.basket.addBasket(optProduct);
                    System.out.println(optProduct.getMenuName() + " 가 장바구니에 추가되었습니다.\n");
                    break;
                case 2:
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "\"옳바른 메뉴 입력이 아닙니다.\"" + "\u001B[0m");
            run(product);
        }
    }

    @Override
    public void ScreenShow(Product product) {
        optProduct = new Product(product);
        //product option Screen
        optionScreen.run(optProduct);

        //optioned product buy screen
        System.out.printf("\n\u001B[33m" + "\"" + "%-20s | W %-3.1f | %s" + "\"" + "\u001B[0m", optProduct.getMenuName(), optProduct.getPrice(), optProduct.getMenuDescription());
        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인\t\t 2. 취소");

    }
}
