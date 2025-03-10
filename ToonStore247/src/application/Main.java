package application;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        UiApplication ob = new UiApplication();
        // UserData 프로그램 시작 시 로딩
        while (true) ob.homeMenu();
    }
}