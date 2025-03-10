package application;

import data.ComicData;
import data.ManagerData;
import data.UserData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class ManagerApplication {
    // static으로 선언하여 하나의 인스턴스만 공유하게 함
    static ManagerData managerData;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final ComicApplication cc = new ComicApplication();
    private static final UserApplication uc = new UserApplication();

    public static ComicApplication getCc() {
        return cc;
    }

    public static UserApplication getUc() {
        return uc;
    }

    // static 생성자
    static {
        managerData = new ManagerData();
        String fileName = "managerData.ser";

        // 파일이 있을때 로딩
        if (Files.exists(Paths.get(fileName))) {
            loadManagerDataFromFile(fileName);
        }
    }

    public static int HomeUI() throws IOException {
        int num;

        System.out.println("================================ ");
        System.out.println("     Welcome to ToonStore247     ");
        System.out.println("================================\n");
        System.out.println("      ████████████████████");
        System.out.println("           툰스토어247    ");
        System.out.println("      ████████████████████\n");
        System.out.println("============= HOME =============");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 종료");
        System.out.println("=================================");

        do {
            System.out.print(">> 원하시는 메뉴를 선택해주세요 → ");
            num = Integer.parseInt(br.readLine());
        } while (num < 1 || num > 3);

        return num;
    }

    public static int CustomerUI() throws IOException {
        int num;

        System.out.println("============== MAIN =============");
        System.out.println("1. 전체 목록");
        System.out.println("2. 대여 및 구매");
        System.out.println("3. 요금 충전");
        System.out.println("4. 내 정보");
        System.out.println("5. 로그아웃");
        System.out.println("6. 종료");
        System.out.println("=================================");

        do {
            System.out.print(">> 원하시는 메뉴를 선택해주세요 → ");
            num = Integer.parseInt(br.readLine());
        } while (num < 1 || num > 6);

        return num;
    }

    public static int ManagerUI() throws IOException {
        int num;

        System.out.println("=========== ADMIN =============");
        System.out.println("1.도서정보관리");
        System.out.println("2.도서재고관리");
        System.out.println("3.매출확인");
        System.out.println("4.로그아웃");
        System.out.println("5.종료");
        System.out.println("=================================");

        do {
            System.out.print(">> 원하시는 메뉴를 선택해주세요 → ");
            num = Integer.parseInt(br.readLine());
        } while (num < 1 || num > 5);

        return num;

    }

    public static void manageComicInfo() throws IOException {

        while (true) {
            System.out.println("\n========= 도서 정보 관리 =========");
            System.out.println("1) 신간 추가");
            System.out.println("2) 만화 정보 변경");
            System.out.println("3) 뒤로가기");
            System.out.println("================================");
            System.out.print(">> 원하시는 업무를 선택해주세요 → ");
            int n = Integer.parseInt(br.readLine());
            if (n == 1) {
                comicAdd();
            } else if (n == 2) {
                System.out.print("\n정보 변경을 원하시는 만화를 입력해주세요 → ");
                String title = br.readLine();
                if(cc.comicMap.containsKey(title)) {
                    ComicData cd = cc.comicMap.get(title);
                    changeComicInfo(cd);
                }
                else System.out.println("\n툰스토어247에 존재하지 않는 만화입니다!");
            } else {
                System.out.println();
                break;
            }
        }

    }

    public static void comicAdd() throws IOException {

        System.out.println("\n※ 신간 정보를 입력해주세요!");
        System.out.print(">> 제목 → ");
        String title = br.readLine();
        if(cc.comicMap.containsKey(title)) {
            System.out.println("\n툰스토어247에 존재하는 만화입니다!");
            return;
        }
        System.out.print(">> 저자 → ");
        String author = br.readLine();
        System.out.print(">> 장르 → ");
        String genre = br.readLine();
        System.out.print(">> 명대사 → ");
        String summary = br.readLine();
        System.out.print(">> 총 화 → ");
        int quantity = Integer.parseInt(br.readLine());

        cc.comicMap.put(title, new ComicData(title, author, genre, summary, quantity));

        //System.out.println(comicMap.containsKey(title));
        cc.saveComicMapToFile("comicData.ser");
        cc.loadComicMapFromFile("comicData.ser");
        System.out.printf("\n[%s] 가 정상적으로 입고 되었습니다!\n", title);

    }
    public static void manageComicInventory() throws IOException {

        System.out.println("\n========= 도서재고관리 ==========");
        System.out.println("1) 대여관리");
        System.out.println("2) 판매관리");
        System.out.println("3) 뒤로가기");
        System.out.println("================================");
        System.out.print(">> 원하시는 업무를 선택해주세요 → ");
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            totalUserView();
            System.out.println("1) 도서반납");
            System.out.println("2) 뒤로가기");
            System.out.println("================================");
            System.out.print(">> 원하시는 업무를 선택해주세요 → ");

            if (Integer.parseInt(br.readLine()) == 1) {

                System.out.print(">> 반납할 사용자의 ID 를 입력하시오 → ");
                String id = br.readLine();
                Map<String, List<String>> map = uc.userMap.get(id).getRentMap();
                if(map.isEmpty()) {
                    System.out.println("\n사용자가 대여중인 만화가 존재하지 않습니다!\n");
                }
                else {
                    System.out.print(">> 반납할 만화를 입력해주세요 → ");
                    String title = br.readLine();
                    System.out.print(">> 반납할 화를 입력해 주세요 → ");
                    int index = Integer.parseInt(br.readLine());
                    String[] tmp = cc.comicMap.get(title).getRentedBookStatus();

                    UserData ud = uc.userMap.get(id);
                    Map<String, List<String>> um = ud.getRentMap();
                    List<String> rentStatus;

                    if (ud.getRentIndex(title) != null) {
                        rentStatus = ud.getRentIndex(title);
                    } else rentStatus = new ArrayList<>();

                    tmp[index - 1] = "○";

                    rentStatus.remove(Integer.toString(index));

                    if (rentStatus.isEmpty()) um.remove(title);
                    else uc.userMap.get(id).setRentIndex(title, rentStatus);

                    System.out.println("\n반납이 완료 되었습니다!\n");
                }
            }

        } else if (n == 2) {
            System.out.print("\n추가 입고하실 만화를 입력해주세요 → ");
            String title = br.readLine();
            if(cc.comicMap.containsKey(title)){
                ComicData comic = cc.comicMap.get(title);
                ManagerApplication.fillBook(comic);}
            else System.out.println("\n툰스토어247에 존재하지 않는 만화입니다!\n");
        }
        else
        {
            System.out.println();
        }

    }

    public static void totalUserView() {

        System.out.println();
        for (String id : uc.userMap.keySet()) {
            if(id.equals("admin"))
                continue;

            UserData ud = uc.userMap.get(id);

            System.out.println("-------- 고객정보 --------");
            System.out.printf("이름 : %s\n", ud.getName());
            System.out.printf("아이디 : %s\n", ud.getId());
            System.out.printf("전화번호 : %s\n", ud.getHp());
            System.out.print("대여도서 : \n");
            int n = 1;
            Map<String, List<String>> um = ud.getRentMap();
            for (String key : um.keySet()) {
                System.out.printf("%d. [제목] %s, [대여 화] ", n++, key);
                for (String value : um.get(key)) {
                    System.out.printf("%s화 ", value);
                }
                System.out.println();
            }

            System.out.println("=========================");

        }

    }
    public static void fillBook(ComicData comic) throws IOException {
        int n = 1;

        System.out.print("현재 재고 : ");
        for (int num : comic.getSellBookStatus())
            System.out.printf("%d화[%s] ", n++, num);
        System.out.print("\n추가 입고 원하시는 화를 입력해주세요 → ");
        int num1 = Integer.parseInt(br.readLine());
        System.out.print("추가할 수량을 입력하세요 → ");
        int num2 = Integer.parseInt(br.readLine());
        comic.getSellBookStatus()[num1-1]+=num2;
        System.out.printf("\n%s의 %d화가 %d권 만큼 입고되었습니다 !!\n\n", comic.getTitle(), num1, num2);
        cc.saveComicMapToFile("comicData.ser");
        cc.loadComicMapFromFile("comicData.ser");
    }

    private static void changeComicInfo(ComicData comic) throws IOException {

        System.out.println("\n=================================");
        System.out.println("1) 제목 변경");
        System.out.println("2) 저자 변경");
        System.out.println("3) 장르 변경");
        System.out.println("4) 명대사 변경");
        System.out.println("=================================");
        System.out.print(">> 원하시는 업무를 선택하세요 → ");
        int num = Integer.parseInt(br.readLine());
        switch (num) {
            case 1:
                System.out.printf("\n현재 제목은 %s 입니다. 변경하실 내용을 입력해주세요 → ", comic.getTitle());
                comic.setTitle(br.readLine());
                break;
            case 2:
                System.out.printf("\n현재 저자는 %s 입니다. 변경하실 내용을 입력해주세요 → ", comic.getAuthor());
                comic.setAuthor(br.readLine());
                break;
            case 3:
                System.out.printf("\n현재 장르는 %s 입니다. 변경하실 내용을 입력해주세요 → ", comic.getGenre());
                comic.setGenre(br.readLine());
                break;
            case 4:
                System.out.printf("\n현재 대사는 %s 입니다. 변경하실 내용을 입력해주세요 → ", comic.getSummary());
                comic.setSummary(br.readLine());
                break;
        }
        System.out.println("\n내용 변경이 완료 되었습니다!!");

    }

    public static void logView() throws IOException {
        while (true) {
            System.out.println("\n========== 매출확인 ============");
            System.out.println("1) 대여로그");
            System.out.println("2) 판매로그");
            System.out.println("3) 매출확인");
            System.out.println("4) 뒤로가기");
            System.out.println("================================");
            System.out.print(">> 원하시는 업무를 선택해주세요 → ");
            int n2 = Integer.parseInt(br.readLine());
            if (n2 == 1) {
                ManagerApplication.rentLog();
            } else if (n2 == 2) {
                ManagerApplication.saleLog();
            } else if (n2 == 3) {
                ManagerApplication.totalLog();
            } else
            {
                System.out.println();
                break;
            }
        }
    }

    static Scanner sc = new Scanner(System.in);
    // 대여 로그 보기
    private static void rentLog() {
        int n = 0;
        System.out.println("\n========== 대여 로그 ============");
        for (String str : managerData.getRentInfo()) {
            System.out.print(str + " ");
            n++;
            if (n % 7 == 0) System.out.println();
        }
        System.out.println("---------------------------------");
        System.out.println("대여 매출 : " + managerData.getRent());
        System.out.println("=================================");
        System.out.print(">> 뒤로가기는 아무키 입력 → ");
        sc.next();
    }

    // 판매 로그 보기
    private static void saleLog() {
        int n = 0;
        System.out.println("\n=========== 판매 로그 ============");
        for (String str : managerData.getSaleInfo()) {
            System.out.print(str + " ");
            n++;
            if (n % 7 == 0) System.out.println();
        }
        System.out.println("---------------------------------");
        System.out.println("판매 매출 : " + managerData.getSale());
        System.out.println("=================================");
        System.out.print(">> 뒤로가기는 아무키 입력 → ");
        sc.next();
    }

    // 전체 로그 보기
    private static void totalLog() {
        System.out.println("\n========== 매출 확인 ===========");
        System.out.println("대여 매출 : " + managerData.getRent());
        System.out.println("판매 매출 : " + managerData.getSale());
        System.out.println("총 매출 : " + (managerData.getRent() + managerData.getSale()));
        System.out.println("=================================");
        System.out.print(">> 뒤로가기는 아무키 입력 → ");
        sc.next();
    }

    // 파일에 ManagerData 객체 저장
    public static void saveManagerDataToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(managerData);
            //System.out.println("Manager 데이터 파일 저장 : " + fileName);
        } catch (IOException e) {
            //System.err.println("Manager 데이터 저장 중 오류 : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 파일에서 ManagerData 객체 불러오기
    public static void loadManagerDataFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            managerData = (ManagerData) ois.readObject();
            //System.out.println("Manager 데이터 파일 로드 : " + fileName);
        } catch (NoSuchFileException | FileNotFoundException e) {
            //System.err.println("Manager 데이터 파일이 없습니다. 최초 실행 시 없을 수 있습니다.");
        } catch (IOException | ClassNotFoundException e) {
            //System.err.println("Manager 데이터 로딩 중 오류" + e.getMessage());
            e.printStackTrace();
        }
    }
}