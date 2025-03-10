package application;

import java.io.IOException;

interface UserInterface                    // 인터페이스 개념
{
    String login() throws IOException;

    void signUp() throws IOException;

    void chargePoint(String id) throws IOException;

    void userView(String id) throws IOException;

    void rentedBookView();

    void saveUserMapToFile(String fileName);

    void loadUserMapFromFile(String fileName);
}
