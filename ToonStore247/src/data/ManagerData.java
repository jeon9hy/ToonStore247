package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagerData implements Serializable {
    private final List<String> rentInfo = new ArrayList<>();
    private final List<String> saleInfo = new ArrayList<>();
    private int rent = 0;
    private int sale = 0;

    public List<String> getRentInfo() {
        return rentInfo;
    }

    public void addRentInfo(String cid, String title, String money, String company, String id, String date, String time) {
        rentInfo.add(cid);
        rentInfo.add(title);
        rentInfo.add(company);
        rentInfo.add(id);
        rentInfo.add(money);
        rentInfo.add(date);
        rentInfo.add(time);
    }

    public List<String> getSaleInfo() {
        return saleInfo;
    }

    public void addSaleInfo(String cid, String title, String money, String company, String id, String date, String time) {
        saleInfo.add(cid);
        saleInfo.add(title);
        saleInfo.add(company);
        saleInfo.add(id);
        saleInfo.add(money);
        saleInfo.add(date);
        saleInfo.add(time);
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rental) {
        this.rent += rental;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale += sale;
    }
}
