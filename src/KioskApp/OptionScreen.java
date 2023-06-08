package KioskApp;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class OptionScreen {
    public static void run(Product product) {
        try {
            //product option list notice + print list
            OptionScreenShow(product);

            int optionclickNum = 0;
            optionclickNum = Integer.parseInt(KioskApp.sc.nextLine());

            //index는 옵션의 갯수를 표시
            if (optionclickNum > 0 && optionclickNum <= DataSet.productOptionlist.stream().filter((Option op) -> op.getMenuName().equals(product.getMenuName())).toList().size()) {
                Map.Entry<String, Double> entry = DataSet.productOptionlist.stream().filter((Option op) -> op.getMenuName().equals(product.getMenuName()))
                        .sorted(Comparator.comparing(Product::getPid))
                        .toList()
                        .get(optionclickNum-1)
                        .getOptions()
                        .entrySet()
                        .iterator()
                        .next();

                //상품이름 변경
                product.setMenuName(product.getMenuName() + "(" + entry.getKey() +")");

                //상품가격 변경
                product.setPrice(entry.getValue());
            } else {
                //상품의 저장된 옵션 이외의 번호를 선택했을 때, 예외 발생
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "\"옳바른 메뉴 입력이 아닙니다.\"" + "\u001B[0m");
            OptionScreen.run(product);
        }
    }

    private static void OptionScreenShow(Product product) {
        System.out.printf("\n\u001B[33m" + "\"" + "%-20s | W %-3.1f | %s" + "\"" + "\u001B[0m", product.getMenuName(), product.getPrice(), product.getMenuDescription());
        System.out.println("\n위 메뉴의 어떤 옵션으로 추가하시겠습니까?");

        AtomicInteger lastIndex = new AtomicInteger(1);
        //product option list
        DataSet.productOptionlist.stream()
                .filter((Option op) -> op.getMenuName().equals(product.getMenuName()))
                .sorted(Comparator.comparing(Product::getProductcategory))
                .forEach((Option op) -> {
                    Iterator iter = op.getOptions().entrySet().iterator();
                    Map.Entry<String,Double> entry = (Map.Entry<String, Double>)iter.next();
                    System.out.printf("\u001B[31m%d.\u001B[0m %s(W %.1f)\t", op.getPid(), entry.getKey(), entry.getValue());
                    lastIndex.getAndIncrement();
                });
    }
}
