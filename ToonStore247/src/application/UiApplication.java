package application;

import data.UserData;

import java.time.LocalDate;

class UiApplication {

    static String accessedID;
    // ManagerApplication mc = new ManagerApplication();
    // ManagerApplication 는 하나로 공유해야 하니 Static으로 선언
    ComicApplication cc = ManagerApplication.getCc();
    UserApplication uc = ManagerApplication.getUc();

    public void homeMenu() {

        try {
            int n = ManagerApplication.HomeUI();

            switch (n) {
                case 1:
                    accessedID = uc.login();

                    uc.loadUserMapFromFile("userData.ser");
                    cc.loadComicMapFromFile("comicData.ser");
                    ManagerApplication.loadManagerDataFromFile("managerData.ser");
                    if (accessedID.equals("admin")) {
                        managerMenu();
                    } else if (uc.userMap.containsKey(accessedID))
                        customerMenu();
                    else {
                        System.out.println("존재하지 않는 아이디입니다!\n");
                        accessedID = "";
                    }
                    break;
                case 2:
                    uc.signUp();
                    uc.saveUserMapToFile("userData.ser");
                    cc.saveComicMapToFile("comicData.ser");
                    ManagerApplication.saveManagerDataToFile("managerData.ser");
                    break;
                case 3:
                    uc.saveUserMapToFile("userData.ser");
                    cc.saveComicMapToFile("comicData.ser");
                    ManagerApplication.saveManagerDataToFile("managerData.ser");
                    System.exit(-1);
            }
        } catch (Exception e) {
            System.out.println(e);
            homeMenu();
        }
    }

    public void customerMenu() {

        UserData ud = uc.userMap.get(accessedID);

        System.out.printf("오늘은 %s | [%s님 환영합니다!]\n", LocalDate.now(), ud.getName());
        cc.recommendComic();

        try {
            int n = ManagerApplication.CustomerUI();

            switch (n) {
                case 1:
                    cc.totalComicView();
                    customerMenu();
                    break;
                case 2:
                    cc.transactionComic(ud);
                    customerMenu();
                    break;
                case 3:
                    uc.chargePoint(accessedID);
                    customerMenu();
                    break;
                case 4:
                    uc.userView(accessedID);
                    customerMenu();
                    break;
                case 5:
                    System.out.println();
                    uc.saveUserMapToFile("userData.ser");
                    cc.saveComicMapToFile("comicData.ser");
                    ManagerApplication.saveManagerDataToFile("managerData.ser");
                    accessedID = "";
                    homeMenu();
                    break;
                case 6:
                    uc.saveUserMapToFile("userData.ser");
                    cc.saveComicMapToFile("comicData.ser");
                    ManagerApplication.saveManagerDataToFile("managerData.ser");
                    System.out.print("프로그램을 종료합니다.");
                    System.exit(-1);  // 프로그램 종료
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        } catch (Exception e) {
            System.out.println(e);
            customerMenu();
        }
    }

    public void managerMenu() {

        try {
            int num = ManagerApplication.ManagerUI();

            switch (num) {
                case 1:
                    ManagerApplication.manageComicInfo();
                    managerMenu();
                    break;


                case 2:
                    ManagerApplication.manageComicInventory();
                    managerMenu();
                    break;

                case 3:
                    ManagerApplication.logView();
                    managerMenu();
                    break;
                case 4:
                    System.out.println();
                    uc.saveUserMapToFile("userData.ser");
                    cc.saveComicMapToFile("comicData.ser");
                    ManagerApplication.saveManagerDataToFile("managerData.ser");
                    homeMenu();
                    accessedID = "";
                    break;
                case 5:
                    uc.saveUserMapToFile("userData.ser");
                    cc.saveComicMapToFile("comicData.ser");
                    ManagerApplication.saveManagerDataToFile("managerData.ser");
                    System.exit(-1);
            }
        } catch (Exception e) {
            System.out.println(e);
            managerMenu();
        }
    }

}