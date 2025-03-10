package data;

import java.io.Serializable;

public class ComicData implements Serializable {

    private String title;
    private String author;
    private String genre;
    private String summary;
    private int quantity;
    private String[] rentedBookStatus; // 각 권의 대여 상태
    private int[] sellBookStatus;

    public ComicData(String title, String author, String genre, String summary, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.summary = summary;
        this.quantity = quantity;
        this.rentedBookStatus = new String[quantity];
        for (int i = 0; i < rentedBookStatus.length; i++)
            rentedBookStatus[i] = "○";
        this.sellBookStatus = new int[quantity];
        for (int i = 0; i < sellBookStatus.length; i++)
            sellBookStatus[i] = 5;
    }


    // Getter와 Setter 메소드
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getRentedBookStatus() {
        return rentedBookStatus;
    }

    public void setRentedBookStatus(String[] rentedBookStatus) {
        this.rentedBookStatus = rentedBookStatus;
    }

    public int[] getSellBookStatus() {
        return sellBookStatus;
    }

    public void setSellBookStatus(int[] sellBookStatus) {
        this.sellBookStatus = sellBookStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}