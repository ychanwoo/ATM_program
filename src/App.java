import components.ATMService;
import components.BankData;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BankData bankData = new BankData();
            ATMService atmService = new ATMService(bankData);
            
            selectLanguage(scanner);
            
            if (!processLogin(scanner, atmService)) {
                System.out.println("로그인 3회 실패로 프로그램을 종료합니다.");
                scanner.close();
                return;
            }
            
            boolean run = true;
            
            while (run) {
                showMainMenu();
                int menu = scanner.nextInt();
                scanner.nextLine();
                
                switch (menu) {
                    case 1 -> withdrawMenu(scanner, atmService);
                    case 2 -> depositTransferMenu(scanner, atmService);
                    case 3 -> accountTransferMenu(scanner, atmService);
                    case 4 -> bankbookMenu();
                    case 5 -> inquiryMenu(atmService);
                    case 6 -> cashbeeMenu();
                    case 7 -> otherMenu(scanner);
                    case 0 -> {
                        run = false;
                        System.out.println("ATM 프로그램을 종료합니다.");
                    }
                    default -> System.out.println("잘못된 메뉴 번호입니다.");
                }
            }
        }
    }

    public static void selectLanguage(Scanner scanner) {
        System.out.println("===== 언어 선택 =====");
        System.out.println("1. 한국어");
        System.out.println("2. English");
        System.out.println("3. 日本語");
        System.out.println("4. 中文");
        System.out.print("선택: ");

        int language = scanner.nextInt();
        scanner.nextLine();

        switch (language) {
            case 1 -> System.out.println("한국어가 선택되었습니다.");
            case 2 -> System.out.println("English selected.");
            case 3 -> System.out.println("日本語が選択されました。");
            case 4 -> System.out.println("中文已选择。");
            default -> System.out.println("기본 언어는 한국어로 설정됩니다.");
        }
    }

    public static boolean processLogin(Scanner scanner, ATMService atmService) {
        while (!atmService.isLoginLocked()) {
            System.out.println();
            System.out.println("===== 로그인 =====");
            System.out.print("계좌번호 입력: ");
            String accountNumber = scanner.nextLine();

            System.out.print("비밀번호 입력: ");
            String password = scanner.nextLine();

            if (atmService.login(accountNumber, password)) {
                System.out.println("로그인 성공");
                return true;
            } else {
                System.out.println("로그인 실패");
            }
        }

        return false;
    }

    public static void showMainMenu() {
        System.out.println();
        System.out.println("===== ATM 메인 메뉴 =====");
        System.out.println("1. 예금출금");
        System.out.println("2. 입금/무통장송금");
        System.out.println("3. 계좌이체");
        System.out.println("4. 통장정리");
        System.out.println("5. 예금조회");
        System.out.println("6. 캐시비(하이패스)");
        System.out.println("7. 다른업무(언어설정)");
        System.out.println("0. 종료");
        System.out.print("메뉴 선택: ");
    }

    public static void withdrawMenu(Scanner scanner, ATMService atmService) {
        System.out.println("===== 예금출금 =====");
        System.out.print("출금 금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.withdraw(money)) {
            System.out.println("출금이 완료되었습니다.");
        } else {
            System.out.println("출금에 실패했습니다.");
        }
    }

    public static void depositTransferMenu(Scanner scanner, ATMService atmService) {
        System.out.println("===== 입금/무통장송금 =====");
        System.out.println("1. 입금");
        System.out.println("2. 무통장송금");
        System.out.print("선택: ");

        int menu = scanner.nextInt();
        scanner.nextLine();

        switch (menu) {
            case 1 -> depositMenu(scanner, atmService);
            case 2 -> cashTransferMenu(scanner);
            default -> System.out.println("잘못된 메뉴 번호입니다.");
        }
    }

    public static void depositMenu(Scanner scanner, ATMService atmService) {
        System.out.print("입금 금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.deposit(money)) {
            System.out.println("입금이 완료되었습니다.");
        } else {
            System.out.println("입금에 실패했습니다.");
        }
    }

    public static void cashTransferMenu(Scanner scanner) {
        System.out.println("무통장송금 기능은 다음 단계에서 구현할 예정입니다.");
    }

    public static void accountTransferMenu(Scanner scanner, ATMService atmService) {
        System.out.println("===== 계좌이체 =====");
        System.out.print("이체할 계좌번호 입력: ");
        String targetAccountNumber = scanner.nextLine();

        System.out.print("이체 금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.transfer(targetAccountNumber, money)) {
            System.out.println("계좌이체가 완료되었습니다.");
        } else {
            System.out.println("계좌이체에 실패했습니다.");
        }
    }

    public static void bankbookMenu() {
        System.out.println("통장정리 기능은 다음 단계에서 구현할 예정입니다.");
    }

    public static void inquiryMenu(ATMService atmService) {
        System.out.println("===== 예금조회 =====");
        atmService.showCurrentAccountInfo();
    }

    public static void cashbeeMenu() {
        System.out.println("캐시비(하이패스) 기능은 다음 단계에서 구현할 예정입니다.");
    }

    public static void otherMenu(Scanner scanner) {
        System.out.println("===== 다른업무 =====");
        selectLanguage(scanner);
    }
}
