package KioskManageProgram;

import KioskApp.KioskApp;
import KioskApp.Basket;
import KioskApp.Menu;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageMenuContext {

    public ManageMenuContext() {
        initializeManageMenu();
    }

    private void initializeManageMenu() {
        ManageDataSet.menus = new HashMap<>();
        ManageDataSet.menus.put("ManageMain", ManageDataSet.mainManageMenus);

        //KioskApp에서 Order(주문내역) 받기
        ManageDataSet.order = new ArrayList<>();
        ManageDataSet.order = KioskApp.getOrder();

        //Kiosk Manage App에서 사용할 완료 주문 목록
        ManageDataSet.completeorder = new ArrayList<>();
    }

    //Manage Menu 목록 반환
    public List<Menu> getManagemenulist(String key) {
        return ManageDataSet.menus.get(key);
    }

    //Kiosk Order list -> Manage order list 반환
    public List<HashMap<Basket, List<String>>> getOrder() {
        return ManageDataSet.order;
    }

    //Kiosk Manage Order list -> Kiosk Manage Complete Order list
    public List<HashMap<Basket, List<String>>> getCompleteOrder() {
        return ManageDataSet.completeorder;
    }

    //Manage Menus 반환
    public Map<String, List<Menu>> getMenus() {
        return ManageDataSet.menus;
    }

    public void addCompleteorders(HashMap<Basket,List<String>> completeorder) {
        ManageDataSet.completeorder.add(completeorder);
    }

    public void deleteorders(int index) {
        ManageDataSet.order.remove(index-1);
    }

    public String getOrderTime() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString();
    }

    /*public List<Menu> getMenulist() {
    }

    public List<Product> getProductlist() {

    }*/
}
