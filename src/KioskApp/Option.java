package KioskApp;

import java.util.HashMap;
public class Option extends Product{
    //<options 내용, 변동 가격>
    private HashMap<String,Double> options = new HashMap<>();

    public Option(int pid, int productcategory, String pname, double p, String pdesc, String options, double changep) {
        super(pid,productcategory,pname,p,pdesc);

        addOption(options, changep);
    }

    public void addOption(String k, double p) {
        this.options.put(k,p);
    }

    public void setOption(String k, double p) {
        this.options.clear();
        addOption(k,p);
    }

    public HashMap<String, Double> getOptions() {
        return this.options;
    }
}
