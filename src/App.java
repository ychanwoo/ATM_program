import components.ATMService;
import components.BankData;
import components.LanguageManager;
import java.util.Scanner;

public class App {
    public static final String LINE = "==================================================";
    public static final String SUB_LINE = "--------------------------------------------------";
    public static final String ATM_LINE = "================================================================================";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankData bankData = new BankData();
        ATMService atmService = new ATMService(bankData);
        LanguageManager languageManager = new LanguageManager();

        boolean run = true;

        while (run) {
            showMainMenu(languageManager);
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    if (!ensureLogin(scanner, atmService, languageManager, "출금")) {
                        if (atmService.isLoginLocked()) {
                            run = false;
                        }
                        break;
                    }
                    withdrawMenu(scanner, atmService);
                    break;
                case 2:
                    depositTransferMenu(scanner, atmService, languageManager);
                    break;
                case 3:
                    if (!ensureLogin(scanner, atmService, languageManager, "계좌이체")) {
                        if (atmService.isLoginLocked()) {
                            run = false;
                        }
                        break;
                    }
                    accountTransferMenu(scanner, atmService);
                    break;
                case 4:
                    if (!ensureLogin(scanner, atmService, languageManager, "통장정리")) {
                        if (atmService.isLoginLocked()) {
                            run = false;
                        }
                        break;
                    }
                    bankbookMenu(atmService);
                    break;
                case 5:
                    if (!ensureLogin(scanner, atmService, languageManager, "예금조회")) {
                        if (atmService.isLoginLocked()) {
                            run = false;
                        }
                        break;
                    }
                    inquiryMenu(scanner, atmService);
                    break;
                case 6:
                    if (!processLogin(scanner, atmService, languageManager, "장기카드대출")) {
                        if (atmService.isLoginLocked()) {
                            run = false;
                        }
                        break;
                    }

                    if (!atmService.isCurrentLoanAccount()) {
                        showFail("현재 입력하신 계좌는 대출 계좌가 아닙니다.");
                        break;
                    }

                    loanMenu(scanner, atmService);
                    break;
                case 7:
                    cashbeeMenu(scanner, atmService);
                    break;
                case 8:
                    otherMenu(scanner, languageManager);
                    break;
                case 0:
                    run = false;
                    System.out.println(languageManager.getText("exit"));
                    break;
                default:
                    System.out.println(languageManager.getText("invalid_menu"));
            }
        }

        scanner.close();
    }

    public static boolean ensureLogin(Scanner scanner, ATMService atmService, LanguageManager languageManager, String sectionTitle) {
        if (atmService.getCurrentAccount() != null) {
            return true;
        }

        if (!processLogin(scanner, atmService, languageManager, sectionTitle)) {
            System.out.println(languageManager.getText("login_locked"));
            return false;
        }

        return true;
    }

    public static void selectLanguage(Scanner scanner, LanguageManager languageManager) {
        System.out.println(languageManager.getText("language_title"));
        System.out.println(languageManager.getText("language_1"));
        System.out.println(languageManager.getText("language_2"));
        System.out.println(languageManager.getText("language_3"));
        System.out.println(languageManager.getText("language_4"));
        System.out.print(languageManager.getText("select"));

        int language = scanner.nextInt();
        scanner.nextLine();

        languageManager.setLanguage(language);

        switch (languageManager.getLanguage()) {
            case 1:
                System.out.println(languageManager.getText("selected_korean"));
                break;
            case 2:
                System.out.println(languageManager.getText("selected_english"));
                break;
            case 3:
                System.out.println(languageManager.getText("selected_japanese"));
                break;
            case 4:
                System.out.println(languageManager.getText("selected_chinese"));
                break;
            default:
                System.out.println(languageManager.getText("default_language"));
        }
    }

    public static boolean processLogin(Scanner scanner, ATMService atmService, LanguageManager languageManager, String sectionTitle) {
        while (!atmService.isLoginLocked()) {
            showSectionTitle(sectionTitle);
            System.out.print("  " + languageManager.getText("input_account"));
            String accountNumber = scanner.nextLine();

            System.out.print("  " + languageManager.getText("input_password"));
            String password = scanner.nextLine();

            if (atmService.login(accountNumber, password)) {
                showSuccess(languageManager.getText("login_success"));
                return true;
            } else {
                showFail(languageManager.getText("login_fail"));
            }
        }

        return false;
    }

    public static void showMainMenu(LanguageManager languageManager) {
        System.out.println();
        System.out.println(ATM_LINE);
        System.out.println("  신하나은행 ATM".concat(padRight("", 40)).concat("www.shin.com"));
        System.out.println("  점번호 : 0101".concat(padRight("", 33)).concat("기번 : 1234"));
        System.out.println(ATM_LINE);
        System.out.println("  ┌────────────────┐   ┌────────────────────────────┐   ┌────────────────┐");
        System.out.println("  │ [1] 예금출금   │   │ 만원/오만원권 출금 가능    │   │ [5] 예금조회   │");
        System.out.println("  └────────────────┘   │ 오만원권 입금 가능         │   └────────────────┘");
        System.out.println("  ┌────────────────┐   └────────────────────────────┘   ┌────────────────┐");
        System.out.println("  │ [2] 입금/무통장│   ┌────────────────────────────┐   │ [6] 장기카드대출│");
        System.out.println("  │     입금       │   │ ATM에서도 다양한 서비스    │   └────────────────┘");
        System.out.println("  └────────────────┘   │        신하나은행 ATM      │   ┌────────────────┐");
        System.out.println("  ┌────────────────┐   └────────────────────────────┘   │ [7] 캐시비/    │");
        System.out.println("  │ [3] 계좌이체   │                                    │     하이패스   │");
        System.out.println("  └────────────────┘                                    └────────────────┘");
        System.out.println("  ┌────────────────┐                                    ┌────────────────┐");
        System.out.println("  │ [4] 통장정리   │                                    │ [8] 다른업무   │");
        System.out.println("  └────────────────┘                                    │   언어설정     │");
        System.out.println("                                                        └────────────────┘");
        System.out.println(ATM_LINE);
        System.out.println("  종료를 원하면 0번을 입력하세요.");
        System.out.print("  " + languageManager.getText("menu_input"));
    }

    public static void withdrawMenu(Scanner scanner, ATMService atmService) {
        showSectionTitle("출금 서비스");
        System.out.print("  금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (!isValidCashUnit(money)) {
            showFail("만원 단위로 입출금이 가능합니다.");
            return;
        }

        if (atmService.withdraw(money)) {
            showSuccess("출금이 완료되었습니다.");
        } else {
            showFail("출금에 실패했습니다.");
        }
    }

    public static void depositTransferMenu(Scanner scanner, ATMService atmService, LanguageManager languageManager) {
        showSectionTitle("입금 / 무통장입금");
        System.out.println("  1. 입금");
        System.out.println("  2. 무통장입금");
        System.out.println(SUB_LINE);
        System.out.print("  " + languageManager.getText("select"));

        int menu = scanner.nextInt();
        scanner.nextLine();

        switch (menu) {
            case 1:
                if (!ensureLogin(scanner, atmService, languageManager, "입금")) {
                    return;
                }
                depositMenu(scanner, atmService);
                break;
            case 2:
                cashTransferMenu(scanner, atmService);
                break;
            default:
                System.out.println(languageManager.getText("invalid_menu"));
        }
    }

    public static void depositMenu(Scanner scanner, ATMService atmService) {
        System.out.println("  입금하실 카드나 통장을 넣어주세요.");
        System.out.print("  금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (!isValidCashUnit(money)) {
            showFail("만원 단위로 입출금이 가능합니다.");
            return;
        }

        if (atmService.deposit(money)) {
            showSuccess("입금이 완료되었습니다.");
        } else {
            showFail("입금에 실패했습니다.");
        }
    }

    public static void cashTransferMenu(Scanner scanner, ATMService atmService) {
        System.out.print("  보내는 사람 이름 입력: ");
        String senderName = scanner.nextLine();

        System.out.print("  받는 계좌번호 입력: ");
        String targetAccountNumber = scanner.nextLine();

        System.out.print("  금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.cashTransfer(targetAccountNumber, money)) {
            showSuccess(senderName + "님의 무통장입금이 완료되었습니다.");
        } else {
            showFail("무통장입금에 실패했습니다.");
        }
    }

    public static void accountTransferMenu(Scanner scanner, ATMService atmService) {
        showSectionTitle("계좌이체 서비스");
        System.out.print("  이체할 계좌번호 입력: ");
        String targetAccountNumber = scanner.nextLine();

        System.out.print("  금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.transfer(targetAccountNumber, money)) {
            showSuccess("계좌이체가 완료되었습니다.");
        } else {
            showFail("계좌이체에 실패했습니다.");
        }
    }

    public static void bankbookMenu(ATMService atmService) {
        showSectionTitle("통장정리");
        System.out.println("  통장을 넣어주세요.");
        atmService.showTransactionHistory();
    }

    public static void inquiryMenu(Scanner scanner, ATMService atmService) {
        while (true) {
            showSectionTitle("예금조회");
            atmService.showCurrentAccountInfo();
            System.out.println(SUB_LINE);
            System.out.println("  1. 메인화면으로 돌아가기");
            System.out.print("  선택: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 1) {
                return;
            }

            showFail("1번을 입력하면 메인화면으로 돌아갑니다.");
        }
    }

    public static void loanMenu(Scanner scanner, ATMService atmService) {
        while (true) {
            showSectionTitle("장기카드대출");
            atmService.showLoanAccountInfo();
            System.out.println(SUB_LINE);
            System.out.println("  1. 메인화면으로 돌아가기");
            System.out.print("  선택: ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 1) {
                return;
            }

            showFail("1번을 입력하면 메인화면으로 돌아갑니다.");
        }
    }

    public static void cashbeeMenu(Scanner scanner, ATMService atmService) {
        showSectionTitle("캐시비 / 하이패스");
        System.out.println("  1. 캐시비 충전");
        System.out.println("  2. 하이패스 충전");
        System.out.println("  3. 잔액 조회");
        System.out.println(SUB_LINE);
        System.out.print("  선택: ");

        int menu = scanner.nextInt();
        scanner.nextLine();

        switch (menu) {
            case 1:
                System.out.println("  교통카드를 넣어주세요.");
                System.out.print("  금액 입력: ");
                int cashbeeMoney = scanner.nextInt();
                scanner.nextLine();

                if (atmService.chargeCashbee(cashbeeMoney)) {
                    showSuccess("캐시비 충전이 완료되었습니다.");
                } else {
                    showFail("캐시비 충전에 실패했습니다.");
                }
                break;
            case 2:
                System.out.println("  하이패스를 넣어주세요.");
                System.out.print("  금액 입력: ");
                int hiPassMoney = scanner.nextInt();
                scanner.nextLine();

                if (atmService.chargeHiPass(hiPassMoney)) {
                    showSuccess("하이패스 충전이 완료되었습니다.");
                } else {
                    showFail("하이패스 충전에 실패했습니다.");
                }
                break;
            case 3:
                atmService.showCashbeeInfo();
                break;
            default:
                showFail("잘못된 메뉴 번호입니다.");
        }
    }

    public static void otherMenu(Scanner scanner, LanguageManager languageManager) {
        showSectionTitle(languageManager.getText("other_title"));
        selectLanguage(scanner, languageManager);
    }

    public static void showSectionTitle(String title) {
        System.out.println();
        System.out.println(LINE);
        System.out.println("  " + title);
        System.out.println(LINE);
    }

    public static void showSuccess(String message) {
        System.out.println();
        System.out.println("[완료] " + message);
    }

    public static void showFail(String message) {
        System.out.println();
        System.out.println("[안내] " + message);
    }

    public static boolean isValidCashUnit(int money) {
        return money > 0 && money % 10000 == 0;
    }

    public static String padRight(String text, int count) {
        StringBuilder builder = new StringBuilder(text);

        for (int i = 0; i < count; i++) {
            builder.append(" ");
        }

        return builder.toString();
    }
}
