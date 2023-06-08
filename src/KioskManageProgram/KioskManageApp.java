package KioskManageProgram;

import KioskApp.Basket;
import KioskApp.Product;
import KioskApp.DataSet;
import KioskApp.Menu;
import KioskApp.Option;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class KioskManageApp {
    static Scanner sc = new Scanner(System.in);
    static boolean kioskManageStop = false;
    static ManageMenuContext manageMenuContext = new ManageMenuContext();

    public static void main() {
        kioskManageStop = false;

        while(!kioskManageStop) {
            displayManageMainMenu();
        }
        System.out.println(String.format("\u001B[32m\"키오스크 관리 프로그램을 종료합니다.\"\u001B[0m\n"));
    }

    private static void displayManageMainMenu() {
        System.out.println("[ \u001B[32mKIOSK Manage App\u001B[0m ]");

        List<Menu> mainMenus = manageMenuContext.getManagemenulist("ManageMain");
        int nextNum = printManageMainMenu(mainMenus,1);
        /*AtomicInteger index = new AtomicInteger(1);
        manageMenuContext.getManagemenulist("ManageMain").forEach((Menu m) -> {
            System.out.println(index + ". " + m.name);
        });*/

        try {
            int ManageMenuClickNum = 0;
            ManageMenuClickNum = Integer.parseInt(sc.nextLine());

            switch (ManageMenuClickNum) {
                case -1:
                    KioskManageApp.kioskManageStop = true;
                    break;
                case 1:
                    displayWaitingOrder();
                    break;
                case 2:
                    displayCompleteOrder();
                    break;
                case 3:
                    displayCreateProduct();
                    break;
                case 4:
                    displayDeleteProduct();
                    break;
                case 5:
                    handleModifyOption();
                    break;
                case 6:
                    handleDeleteOption();
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m\"옳바르지 않은 입력입니다.\"\u001B[0m");
            displayManageMainMenu();
        }
    }

    //display
    private static void displayWaitingOrder() throws Exception {
        List<HashMap<Basket, List<String>>> order = manageMenuContext.getOrder();

        System.out.println("[ \u001B[32mWaiting Order list\u001B[0m ]");
        printorderlist(order);
    }

    private static void displayCompleteOrder() {
        List<HashMap<Basket, List<String>>> corder = manageMenuContext.getCompleteOrder();

        System.out.println("[ \u001B[32mComplete Order list\u001B[0m ]");
        printCompleteorderlist(corder);
    }

    private static void displayCreateProduct() {
        handleCreateProduct();
    }

    private static void displayDeleteProduct() {
        handleDeleteProduct();
    }

    //print
    private static int printManageMainMenu(List<Menu> menus, int num) {
        AtomicInteger index = new AtomicInteger(num);
        menus.forEach((Menu m) -> {
            System.out.println(index.get() + ". " + menus.get(index.getAndIncrement()-1).getMenuName());
        });
        return index.get();
    }
    private static void printorderlist(List<HashMap<Basket, List<String>>> order) throws Exception {
        //주문 데이터 출력
        AtomicInteger index = new AtomicInteger(1);
        order.forEach((HashMap<Basket,List<String>> map) -> {
            System.out.println("\u001B[34m대기번호\u001B[0m : " + index.getAndIncrement());
            System.out.println("\u001B[32m주문상품목록\u001B[0m");
            map.forEach((Basket basket, List<String> info) -> {
                basket.getBasket().forEach(Product::getProductlist);
                System.out.println("\u001B[32m주문 총 가격\u001B[0m : " + basket.getTotalPrice());
                System.out.println("\u001B[32m주문ID\u001B[0m : " + info.get(0));
                System.out.println("\u001B[32m요청사항\u001B[0m : " + info.get(1));
                System.out.println("\u001B[32m주문일시\u001B[0m : " + info.get(2));
                System.out.print("\n");
            });
        });

        //완료처리 로직
        /*
            사용자로부터 대기번호를 입력받고 입력한 번호에 해당하는 주문 데이터를
            1. 완료 주문 리스트에 추가
            2. 대기 중인 주문 리스트에서 삭제
         */
        handleCompleteOrder(index.get());
    }

    private static void printCompleteorderlist(List<HashMap<Basket, List<String>>> corder) {
        //주문완료 데이터 출력
        AtomicInteger index = new AtomicInteger(1);
        corder.forEach((HashMap<Basket,List<String>> map) -> {
            System.out.println("\u001B[34m대기번호\u001B[0m : " + index.getAndIncrement());
            System.out.println("\u001B[32m주문상품목록\u001B[0m");
            map.forEach((Basket basket, List<String> info) -> {
                basket.getBasket().forEach(Product::getProductlist);
                System.out.println("\u001B[32m주문 총 가격\u001B[0m : " + basket.getTotalPrice());
                System.out.println("\u001B[32m주문ID\u001B[0m : " + info.get(0));
                System.out.println("\u001B[32m요청사항\u001B[0m : " + info.get(1));
                System.out.println("\u001B[32m주문일시\u001B[0m : " + info.get(2));
                System.out.println("\u001B[34m완료주문일시\u001B[0m : " + info.get(3));
                System.out.print("\n");
            });
        });
    }


    //handle
    private static void handleCompleteOrder(int index) throws Exception {
        try {
            System.out.print("\u001B[34m주문완료할 번호를 입력하세요(\u001B[31m뒤로가기=\'0\'\u001B[0m)\u001B[0m : ");
            int OrderClickNum = 0;
            OrderClickNum = Integer.parseInt(sc.nextLine());
            if(OrderClickNum == 0) {
                return;
            } else if(OrderClickNum > 0 && OrderClickNum < index) {
                HashMap<Basket, List<String>> completeOrder = manageMenuContext.getOrder().get(OrderClickNum-1);
                completeOrder.forEach((Basket basket, List<String> list) -> {
                    List<String> newlist = new ArrayList<>(list);
                    newlist.add(manageMenuContext.getOrderTime());
                    completeOrder.put(basket,newlist);
                });
                manageMenuContext.addCompleteorders(completeOrder);

                manageMenuContext.deleteorders(OrderClickNum);

                System.out.println("\u001B[32m주문번호 [ " + OrderClickNum + " ]을 완료처리 했습니다.\u001B[0m\n");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "\"옳바른 메뉴 입력이 아닙니다.\"" + "\u001B[0m");
            handleCompleteOrder(index);
        }
    }

    private static void handleCreateProduct() {
        System.out.println("\u001B[32m[ CreateProduct ]\u001B[0m");
        System.out.println("\u001B[32m[ Menu List ]\u001B[0m");
        List<Integer> menuIds = new ArrayList<>();
        DataSet.menulist.forEach((m)-> {
            menuIds.add(m.getMid());
            System.out.print("Menu ID : " + m.getMid());
            System.out.println(" | Menu Name : " + m.getMenuName());

        });
        System.out.println("\u001B[33m-\u001B[0m".repeat(20));
        System.out.print("메뉴ID : ");
        int menuID = Integer.parseInt(sc.nextLine());

        System.out.print("상품이름 : ");
        String productName = sc.nextLine();

        System.out.print("상품설명 : ");
        String productDescription = sc.nextLine();

        System.out.print("가격 : ");
        Double productPrice = Double.parseDouble(sc.nextLine());

        if(!menuIds.contains(menuID)) {
            System.out.println("\u001B[31m기존의 존재하는 메뉴가 아닙니다. 마지막 MenuId Number로 대체합니다.\u001B[0m\n \u001B[34m메뉴를 추가를 위해 메뉴명과 설명을 입력하세요 \u001B[0m");
            System.out.print("메뉴명 : ");
            String menuName = sc.nextLine();

            System.out.print("메뉴 설명 : ");
            String menuDescription = sc.nextLine();

            DataSet.menulist.add(new Menu(menuIds.get(menuIds.size() - 1)+1,1,menuName,menuDescription));
            DataSet.productslist.add(new Product(1,menuIds.get(menuIds.size() - 1)+1,productName,productPrice,productDescription));

        } else {
            int nextPid = DataSet.productslist.stream().filter((Product p) -> p.getProductcategory() == menuID).mapToInt(Product::getPid).max().getAsInt();
            DataSet.productslist.add(new Product(nextPid+1,menuID,productName,productPrice,productDescription));
        }
        handleAddOption(menuID,productName,productPrice,productDescription);
    }

    private static void handleAddOption(int menuid, String pName,double pPrice, String pDesc) {
        //Add Options
        //새로운 상품에 대한 옵션 추가
        int numpid = 1;
        while(true) {
            System.out.println("[ \u001B[34m옵션을 추가합니다.\u001B[0m\u001B[31m(\"quit\"입력과 최소 1개 이상의 옵션 입력 시 종료)\u001B[0m ]");

            System.out.print("옵션명 : ");
            String optionName = sc.nextLine();
            if(optionName.equals("quit") && numpid > 1) {
                break;
            } else {
                System.out.print("옵션가격 : ");
                Double optionPrice = Double.parseDouble(sc.nextLine());

                DataSet.productOptionlist.add(new Option(numpid++, menuid, pName, pPrice, pDesc, optionName,optionPrice));
                System.out.println("\u001B[34m옵션이 추가되었습니다.\u001B[0m");
            }
        }
    }

    private static void handleDeleteProduct() {
        DataSet.productslist.forEach((Product p) -> {
            System.out.printf("%2d | PID : %2d | MenuID : %2d | ProductName : %-20s | Price : %.1f | ProductDescription : %s\n",DataSet.productslist.indexOf(p)+1,p.getPid(),p.getProductcategory(),p.getMenuName(),p.getPrice(), p.getMenuDescription());
        });

        System.out.print("삭제할 상품의 번호를 입력하세요 : ");
        int cn = Integer.parseInt(sc.nextLine());
        String pName = DataSet.productslist.get(cn-1).getMenuName();
        DataSet.productslist.remove(cn-1);


        DataSet.productOptionlist.removeAll(DataSet.productOptionlist.stream().filter((Option o) -> o.getMenuName().equals(pName)).toList());

        System.out.println("\u001B[31m상품(+상품옵션)이 삭제되었습니다.\u001B[0m");
    }

    private static void handleModifyOption() {
        DataSet.productOptionlist.forEach((Option o) -> {
            System.out.printf("%2d | PID : %2d | MenuID : %2d | ProductName : %-20s | ",DataSet.productOptionlist.indexOf(o)+1,o.getPid(),o.getProductcategory(),o.getMenuName());
            o.getOptions().forEach((key, value) -> {
                System.out.printf("option : %-10s | modifyPrice : %.1f\n", key,value);
            });
        });

        System.out.print("수정할 옵션의 번호를 입력하세요 : ");
        int cn = Integer.parseInt(sc.nextLine());

        System.out.print("변경할 옵션명 : ");
        String optionName = sc.nextLine();

        System.out.print("변경할 옵션가격 : ");
        Double optionPrice = Double.parseDouble(sc.nextLine());

        DataSet.productOptionlist.get(cn-1).setOption(optionName,optionPrice);
        System.out.println("\u001B[34m옵션이 변경되었습니다.\u001B[0m");
    }

    private static void handleDeleteOption() {
        DataSet.productOptionlist.forEach((Option o) -> {
            System.out.printf("%2d | PID : %2d | MenuID : %2d | ProductName : %-20s | ",DataSet.productOptionlist.indexOf(o)+1,o.getPid(),o.getProductcategory(),o.getMenuName());
            o.getOptions().forEach((key, value) -> {
                System.out.printf("option : %-10s | modifyPrice : %.1f\n", key,value);
            });
        });

        System.out.print("삭제할 옵션의 번호를 입력하세요 : ");
        int cn = Integer.parseInt(sc.nextLine());

        DataSet.productOptionlist.remove(cn-1);
        System.out.println("\u001B[31m옵션이 삭제되었습니다.\u001B[0m");
    }
}
