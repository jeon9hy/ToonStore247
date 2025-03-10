package data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData implements Serializable {

    Map<String, List<String>> rentMap = new HashMap<>();
    private String name = "";
    private String id = "";
    private String hp = "";
    private String cardCompany;
    private String cardNum;
    private int balance = 0;

    public UserData(String id, String name, String hp, String cardCompany, String cardNum)   // 생성자 개념
    {
        this.name = name;
        this.id = id;
        this.hp = hp;
        this.cardCompany = cardCompany;
        this.cardNum = cardNum;
    }

    // getter setter 설정
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance += balance;
    }

    public String getCardCompany() {
        return cardCompany;
    }

    public void setCardCompany(String cardCompany) {
        this.cardCompany = cardCompany;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Map<String, List<String>> getRentMap() {
        return rentMap;
    }

    public void setRentIndex(String title, List<String> index) {
        rentMap.put(title, index);
    }

    public List<String> getRentIndex(String title) {
        return rentMap.get(title);
    }

}