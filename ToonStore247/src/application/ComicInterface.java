package application;

import data.UserData;
import java.io.IOException;

interface ComicInterface {
    void totalComicView() throws IOException;

    void transactionComic(UserData uc) throws IOException;

    void recommendComic();

    void saveComicMapToFile(String fileName);

    void loadComicMapFromFile(String fileName);
}
