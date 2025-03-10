package application;

import data.ComicData;
import data.UserData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

class ComicApplication implements ComicInterface {

    public Map<String, ComicData> comicMap = new HashMap<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    List<String> action = new ArrayList<>();
    List<String> romance = new ArrayList<>();
    List<String> comedy = new ArrayList<>();
    List<String> thriller = new ArrayList<>();
    List<String> fantasy = new ArrayList<>();

    public ComicApplication() {
        String fileName = "comicData.ser";

        if (!Files.exists(Paths.get(fileName))) {
            comicMap.put("나의 히어로 아카데미아", new ComicData("나의 히어로 아카데미아", "호리코시 코헤이", "액션", "저도 히어로가 될 수 있을까요..?", 7));
            comicMap.put("호리미야", new ComicData("호리미야", "하기와라 다이스케", "로맨스", "마음이 따끈따끈", 8));
            comicMap.put("귀멸의 칼날", new ComicData("귀멸의 칼날", "고토게 코요하루", "액션", "엔바시라 쿄주로 엔무!!", 8));
            comicMap.put("바람의 검심", new ComicData("바람의 검심", "와츠키 노부히로", "액션", "칼잡이 발도재 !", 4));
            comicMap.put("이누야샤", new ComicData("이누야샤", "타카하시 루미코", "액션", "안녕히 계세요 여러분! 전 이 세상의 모든 굴레와 속박을 벗어 던지고 제 행복을 찾아 떠납니다! 여러분도 행복하세요~~! !", 7));
            comicMap.put("당신은 저승님", new ComicData("당신은 저승님", "쇼탄", "로맨스", "고용해 주시겠습니까?", 7));
            comicMap.put("결혼한다는 게, 정말인가요", new ComicData("결혼한다는 게, 정말인가요", "와카키 타미키", "로맨스", "'우리 결혼하지 않을래요?'", 10));
            comicMap.put("내세에는 남남이 좋겠어", new ComicData("내세에는 남남이 좋겠어", "코니시 아스카", "로맨스", " '지금 내 눈앞에 있다는 소리야.' ", 8));
            comicMap.put("아기와 나", new ComicData("아기와 나", "라가와 마리모", "로맨스", ".운명이라..있을지도 모르지. 하지만 내가 믿는 운명은 인간이 만드는 거야.", 3));
            comicMap.put("괴짜가족", new ComicData("괴짜가족", "하마오카 켄지", "코미디", "꺄울~!, 으갸!", 8));
            comicMap.put("사카모토입니다만?", new ComicData("사카모토입니다만?", "사노 나미", "코미디", "절은 절에서나 하시죠?", 7));
            comicMap.put("사이키 쿠스오의 재난", new ComicData("사이키 쿠스오의 재난", "쇼탄", "아소 슈이치", "내 이름은 사이키 쿠스오. 초능력자다.", 9));
            comicMap.put("마루코는 아홉살", new ComicData("마루코는 아홉살", "사쿠라 모모코", "코미디", "마루코 : 할아버지가 내가 죽을때 까지 심부름 해주셨음 좋겠어.. 할아버지 : 차라리 일찍 죽는게 낫겠다.", 5));
            comicMap.put("보노보노", new ComicData("보노보노", "이가라시 미키오", "코미디", "헛소리하지마 인마", 7));
            comicMap.put("슈타인즈 게이트", new ComicData("슈타인즈 게이트", "하야시 나오타카", "스릴러", "엘 프사이 콩그루.", 8));
            comicMap.put("간츠", new ComicData("간츠", "오쿠 히로야", "스릴러", "생에 죽음의 마지막 막을 내릴때 동시에 새로운 생명 창조의 시작을 알리지", 8));
            comicMap.put("피안도", new ComicData("피안도", "마츠모토 코지", "스릴러", "괜찮아 뼈는 부러지지 않았어....", 4));
            comicMap.put("미래일기", new ComicData("미래일기", "에스노 사카에", "스릴러", "유키를 죽이려는 것들은 모조리 죽어버리면 돼!!", 6));
            comicMap.put("일하는 세포", new ComicData("일하는 세포", "하야시 나오타카", "스릴러", "어째서 환경이 나아지질 않는 거야?! 그렇게 죽도록 고생했는데, 대체 왜?", 6));
            comicMap.put("나 혼자만 레벨업", new ComicData("나 혼자만 레벨업", "추공", "판타지", "[플레이어가 되실 자격을 획득하셨습니다.]", 4));
            comicMap.put("나루토", new ComicData("나루토", "키시모토 마사시", "판타지", "호카게는 나의 꿈이니까!", 4));
            comicMap.put("블리치", new ComicData("블리치", "쿠보 타이토", "판타지", "만해.", 7));
            comicMap.put("주술회전", new ComicData("주술회전", "아쿠타미 게게", "판타지", "영역 전개.", 8));
            comicMap.put("장송의 프리렌", new ComicData("장송의 프리렌", "야마다 카네히토", "판타지", "그 백 분의 일이 너를 바꾸었다.", 3));
            comicMap.put("란마 1/2", new ComicData("란마 1/2", "타카하시 루미코", "코미디", "아.. 안경... 이거지!", 7));
            comicMap.put("원피스", new ComicData("원피스", "오다 에이치로", "액션", "나는 해적왕이 될꺼라고요!", 6));
        }

        loadComicMapFromFile(fileName);
    }

    // 전체 만화 목록 출력
    @Override
    public void totalComicView() throws IOException {

        System.out.println("\n=================================");
        System.out.println("◎ 툰스토어 247 보유 만화 ◎");

        if (action.isEmpty()) {
            for (String title : comicMap.keySet()) {
                ComicData comic = comicMap.get(title);
                switch (comic.getGenre()) {
                    case "액션":
                        action.add(comic.getTitle());
                        break;
                    case "로맨스":
                        romance.add(comic.getTitle());
                        break;
                    case "코미디":
                        comedy.add(comic.getTitle());
                        break;
                    case "스릴러":
                        thriller.add(comic.getTitle());
                        break;
                    case "판타지":
                        fantasy.add(comic.getTitle());
                        break;
                }
            }
            Collections.sort(action);
            Collections.sort(romance);
            Collections.sort(comedy);
            Collections.sort(thriller);
            Collections.sort(fantasy);

        }
        print();
    }

    private void print() throws IOException {

        int n = 1;
        System.out.println("=================================");
        System.out.println("[액션]");
        for (String str : action)
            System.out.printf("%d.%s\n", n++, str);
        n = 1;
        System.out.println("=================================");
        System.out.println("[로맨스]");
        for (String str : romance)
            System.out.printf("%d.%s\n", n++, str);
        n = 1;
        System.out.println("=================================");
        System.out.println("[스릴러]");
        for (String str : thriller)
            System.out.printf("%d.%s\n", n++, str);
        n = 1;
        System.out.println("=================================");
        System.out.println("[판타지]");
        for (String str : fantasy)
            System.out.printf("%d.%s\n", n++, str);
        n = 1;
        System.out.println("=================================");
        System.out.println("[코미디]");
        for (String str : comedy)
            System.out.printf("%d.%s\n", n++, str);
        System.out.println("=================================");

        System.out.print(">> 뒤로 가기는 아무키 입력 → ");
        br.readLine();
        System.out.println();
    }

    // 선택한 만화의 상세 정보 출력
    @Override
    public void transactionComic(UserData uc) throws IOException {
        System.out.println("\n========== 대여 및 구매 ===========");
        System.out.print("작품 제목을 입력해주세요 → ");

        String title = br.readLine();

        if (comicMap.containsKey(title)) {
            System.out.println();
            int i = 1, j = 1;
            ComicData comic = comicMap.get(title);
            System.out.println("=================================");
            System.out.printf("%s님의 현재 포인트 : %d\n", uc.getName(), uc.getBalance());
            System.out.println("=================================");
            System.out.printf("제목 : %s\n", comic.getTitle());
            System.out.printf("작가 : %s\n", comic.getAuthor());
            System.out.printf("장르 : %s\n", comic.getGenre());
            System.out.printf("명대사 : %s\n", comic.getSummary());
            System.out.print("대여 재고 : ");
            for (String str : comic.getRentedBookStatus())
                System.out.printf("%d권(%s) ", i++, str);
            System.out.println();
            System.out.print("판매 재고 : ");
            for (int num : comic.getSellBookStatus())
                System.out.printf("%d권(%s) ", j++, num);
            System.out.println("\n=================================");
            System.out.print(">> 원하시는 업무를 선택해주세요 ([1]대여 [2]구매 [3]뒤로가기) → ");

            int num = Integer.parseInt(br.readLine());
            if (num == 1) {
                if (uc.getBalance() >= 1000) {
                    rentBook(title, uc);
                } else {
                    System.out.println("\n※ 고객님의 포인트가 부족합니다!\n");
                }

            } else if (num == 2) {
                if (uc.getBalance() >= 5000) {
                    buyBook(title, uc);
                } else {
                    System.out.println("\n※ 고객님의 포인트가 부족합니다!\n");
                }
            }
        } else {
            System.out.println("\n※ 해당 만화는 존재하지 않습니다!\n");
        }
    }

    // 특정 만화의 대여 로직 구현
    private void rentBook(String title, UserData uc) throws IOException {


        ComicData comic = comicMap.get(title);
        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();

        String[] tmp = comic.getRentedBookStatus();
        List<String> rentStatus;
        if (uc.getRentIndex(title) != null) {
            rentStatus = uc.getRentIndex(title);
            for (String str : rentStatus) {
                tmp[Integer.parseInt(str) - 1] = "X";
            }
        } else rentStatus = new ArrayList<>();

        System.out.println("\n=================================");
        System.out.printf("%s님의 현재 포인트 : %d\n", uc.getName(), uc.getBalance());
        System.out.println("=================================");
        System.out.print("대여 가능 : ");
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].equals("○")) System.out.printf("[%d화]", i + 1);
        }
        System.out.println("\n=================================");
        System.out.print("대여 원하시는 화를 입력하세요 → ");
        int num = Integer.parseInt(br.readLine());
        if (tmp[num - 1].equals("○")) {
            tmp[num - 1] = "X";
            comic.setRentedBookStatus(tmp);

            ManagerApplication.managerData.setRent(1000);
            ManagerApplication.managerData.addRentInfo(uc.getId(), title, "1000원", uc.getCardCompany(), uc.getCardNum(), now.toString(), time.toString());
            uc.setBalance(-1000);
            System.out.printf("\n%d화의 대여가 완료 되었습니다!\n반납 일을 지켜주세요!\n\n", num);
            rentStatus.add(Integer.toString(num));
            Collections.sort(rentStatus);
            uc.setRentIndex(title, rentStatus);
        } else {
            System.out.println("\n대여 불가능한 화입니다. 대여 가능한 화를 확인해주세요!\n");
            rentBook(title, uc);
        }
    }


    // 특정 만화의 판매 로직 구현
    private void buyBook(String title, UserData uc) throws IOException {

        ComicData comic = comicMap.get(title);
        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.println("\n=================================");
        System.out.printf("%s님의 현재 포인트 : %d\n", uc.getName(), uc.getBalance());
        int[] tmp = comic.getSellBookStatus();
        System.out.println("=================================");
        System.out.print("구매 가능 : ");
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] > 0) System.out.printf("[%d화]", i + 1);
        }
        System.out.println("\n=================================");

        System.out.print("구매 원하시는 화를 입력하세요 → ");
        int num = Integer.parseInt(br.readLine());

        if (tmp[num - 1] < 0) {
            System.out.println("\n구매 불가능한 화입니다. 구매 가능한 화를 확인해주세요!\n");
            buyBook(title, uc);
        } else {
            tmp[num - 1]--;
            ManagerApplication.managerData.setSale(5000);
            ManagerApplication.managerData.addSaleInfo(uc.getId(), title, "5000원", uc.getCardCompany(), uc.getCardNum(), now.toString(), time.toString());
            uc.setBalance(-5000);
            System.out.printf("\n%d화의 구매가 완료 되었습니다!\n\n", num);
        }
        comic.setSellBookStatus(tmp);

    }


    // 오늘의 추천 만화 출력 로직 구현
    @Override
    public void recommendComic() {

        Calendar today = Calendar.getInstance();

        Vector<String> vt = new Vector<>(comicMap.keySet());
        String title = vt.get(today.get(Calendar.DATE) % comicMap.size());
        ComicData comic = comicMap.get(title);
        System.out.println("=================================");
        System.out.println("♣ 오늘의 추천 만화 ♣");
        System.out.printf("[제목] %s\n", title);
        System.out.printf("[명대사] %s\n", comic.getSummary());
    }

    // ComicMap 프로그램 종료 시 객체 직렬화 저장
    @Override
    public void saveComicMapToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(comicMap);
            //System.out.println("Comic 데이터 파일 저장 : " + fileName);
        } catch (IOException e) {
            //System.err.println("Comic 데이터 저장 중 오류 : " + e.getMessage());
            //System.out.println(e);
        }
    }

    // ComicMap 데이터 시작 시 객채 역직렬화 로딩
    @Override
    public void loadComicMapFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            comicMap = (Map<String, ComicData>) ois.readObject();
            //System.out.println("Comic 데이터 파일 로드 : " + fileName);
        } catch (NoSuchFileException | FileNotFoundException e) {
            //System.err.println("Comic 데이터 파일이 없습니다. 최초 실행 시 없을 수 있습니다.");
        } catch (IOException | ClassNotFoundException e) {
            // System.err.println("Comic 데이터 로딩 중 오류" + e.getMessage());
            e.printStackTrace();
        }
    }
}
