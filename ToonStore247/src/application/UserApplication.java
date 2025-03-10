package application;

import data.UserData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class UserApplication implements UserInterface  // 상속 개념
{
    public Map<String, UserData> userMap = new HashMap<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    UserData ud;

    public UserApplication() {
        String fileName = "userData.ser";

        if (!Files.exists(Paths.get(fileName))) {
            userMap.put("admin", new UserData("admin", "관리자", "", "", ""));
        }

        loadUserMapFromFile(fileName);
    }

    @Override            // 오버라이딩 개념
    public String login() throws IOException {
        System.out.println("\n============= 로그인 =============");
        System.out.print("아이디를 입력해주세요 → ");
        String id = br.readLine();
        System.out.println();
        return id;

    }

    @Override
    public void signUp() throws IOException {

        String signid;        //-- 반복문 do while 종료를 위한 변수 선언
        System.out.println("\n============ 회원가입 ============");
        while (true) {
            System.out.print("사용하실 아이디를 입력 해주세요 → ");
            signid = br.readLine();

            if (userMap.containsKey(signid))                // 입력한 값이 발견 된다면 아랫값 실행
                System.out.println("※ 입력하신 아이디는 중복된 아이디 입니다!");
            else break;
        }

        System.out.print("이름을 입력 해주세요 → ");
        String name = br.readLine();

        System.out.print("전화번호를 입력 해주세요 → ");
        String hp = br.readLine();

        System.out.print("카드사 입력 → ");
        String cardCompany = br.readLine();

        System. out.print("카드번호 입력 → ");
        String cardNum = br.readLine();


        System.out.printf("\n%s님 정상적으로 가입 되었습니다!\n\n", signid);

        userMap.put(signid, new UserData(name, signid, hp, cardCompany, cardNum));
    }

    @Override
    public void chargePoint(String id) throws IOException {
        ud = userMap.get(id);

        System.out.println("\n=================================");
        System.out.printf("현재 포인트 : %d\n", ud.getBalance());
        System.out.println("=================================");
        System.out.println("1) 1000");
        System.out.println("2) 5000");
        System.out.println("3) 10000");
        System.out.println("4) 50000");
        System.out.println("5) 직접입력");
        System.out.println("6) 뒤로가기");
        System.out.println("=================================");
        System.out.print(">> 원하시는 금액을 선택해주세요 → ");
        int n = Integer.parseInt(br.readLine());
        switch (n) {
            case 1:
                ud.setBalance(1000);
                break;
            case 2:
                ud.setBalance(5000);
                break;
            case 3:
                ud.setBalance(10000);
                break;
            case 4:
                ud.setBalance(50000);
                break;
            case 5:
                System.out.print(">> 직접입력 → ");
                int point =Integer.parseInt(br.readLine());
                ud.setBalance(point);
                break;
            default:
                System.out.println();
                break;
        }
        if (n >= 1 && n <= 5) {
            System.out.println("\n정상적으로 충전되었습니다!");
            System.out.printf("충전 후 포인트 : %d\n\n", ud.getBalance());
        }
    }

    public void userView(String id) throws IOException {
        ud = userMap.get(id);

        System.out.println("\n========== 사용자 정보 ============");
        System.out.println("아이디      : " + ud.getId());
        System.out.println("이름       : " + ud.getName());
        System.out.println("전화번호    : " + ud.getHp());
        System.out.println("카드사      : " + ud.getCardCompany());
        System.out.println("카드번호    : " + ud.getCardNum());
        System.out.println("현재 포인트 : " + ud.getBalance());
        System.out.print("내가 대여한 만화 : \n");
        rentedBookView();
        System.out.println("=================================");
        System.out.print(">> 원하시는 업무를 선택해주세요. ([1] 카드 정보 변경, [2] 뒤로가기) → ");
        int n = Integer.parseInt(br.readLine());
        if (n == 1) changeCardInfo(ud);
        else System.out.println();
    }

    private void changeCardInfo(UserData ud) {
        System.out.println("\n========= 현재 카드 정보===========");
        System.out.printf("카드사 :  %s\n카드번호 : %s\n", ud.getCardCompany(), ud.getCardNum());
        System.out.println("=================================");
        System.out.print("변경할 정보 입력 (카드사, 카드번호 ',' 로 구분) → ");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] cardInfo = str.split(",");
        ud.setCardCompany(cardInfo[0].trim());
        ud.setCardNum(cardInfo[1].trim());

        System.out.println("\n========= 변경된 카드 정보=========");
        System.out.printf("카드사 : %s\n카드번호 : %s\n", ud.getCardCompany(), ud.getCardNum());
        System.out.println("=================================");
        System.out.println("위 카드 정보로 변경 완료 되었습니다!\n");

    }

    @Override
    public void rentedBookView() {
        int n = 1;
        Map<String, List<String>> um = ud.getRentMap();
        for (String key : um.keySet()) {
            System.out.printf("%d. [제목] %s, [대여 화] ", n++, key);
            for (String value : um.get(key)) {
                System.out.printf("%s화 ", value);
            }
            System.out.println();
        }
    }

    // UserMap 프로그램 종료 시 객체 직렬화 저장
    @Override
    public void saveUserMapToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(userMap);
            //System.out.println("User 데이터 파일 저장 : " + fileName);
        } catch (IOException e) {
            //System.err.println("User 데이터 저장 중 오류 : " + e.getMessage());
        }
    }


    // UserMap 데이터 시작 시 객채 역직렬화 로딩
    @Override
    public void loadUserMapFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            userMap = (Map<String, UserData>) ois.readObject();
            //System.out.println("User 데이터 파일 로드 : " + fileName);
        } catch (NoSuchFileException | FileNotFoundException e) {
            //System.err.println("User 데이터 파일이 없습니다. 최초 실행 시 없을 수 있습니다.");
        } catch (IOException | ClassNotFoundException e) {
            //System.err.println("User 데이터 로딩 중 오류" + e.getMessage());
            e.printStackTrace();
        }
    }

}