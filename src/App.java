import components.ATMService;
import components.BankData;
import components.LanguageManager;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankData bankData = new BankData();
        ATMService atmService = new ATMService(bankData);
        LanguageManager languageManager = new LanguageManager();

        selectLanguage(scanner, languageManager);

        if (!processLogin(scanner, atmService, languageManager)) {
            System.out.println(languageManager.getText("login_locked"));
            scanner.close();
            return;
        }

        boolean run = true;

        while (run) {
            showMainMenu(languageManager);
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    withdrawMenu(scanner, atmService);
                    break;
                case 2:
                    depositTransferMenu(scanner, atmService, languageManager);
                    break;
                case 3:
                    accountTransferMenu(scanner, atmService);
                    break;
                case 4:
                    bankbookMenu(atmService);
                    break;
                case 5:
                    inquiryMenu(atmService);
                    break;
                case 6:
                    cashbeeMenu(scanner, atmService);
                    break;
                case 7:
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

    public static boolean processLogin(Scanner scanner, ATMService atmService, LanguageManager languageManager) {
        while (!atmService.isLoginLocked()) {
            System.out.println();
            System.out.println(languageManager.getText("login_title"));
            System.out.print(languageManager.getText("input_account"));
            String accountNumber = scanner.nextLine();

            System.out.print(languageManager.getText("input_password"));
            String password = scanner.nextLine();

            if (atmService.login(accountNumber, password)) {
                System.out.println(languageManager.getText("login_success"));
                return true;
            } else {
                System.out.println(languageManager.getText("login_fail"));
            }
        }

        return false;
    }

    public static void showMainMenu(LanguageManager languageManager) {
        System.out.println();
        System.out.println(languageManager.getText("main_menu"));
        System.out.println(languageManager.getText("menu_1"));
        System.out.println(languageManager.getText("menu_2"));
        System.out.println(languageManager.getText("menu_3"));
        System.out.println(languageManager.getText("menu_4"));
        System.out.println(languageManager.getText("menu_5"));
        System.out.println(languageManager.getText("menu_6"));
        System.out.println(languageManager.getText("menu_7"));
        System.out.println(languageManager.getText("menu_0"));
        System.out.print(languageManager.getText("menu_input"));
    }

    public static void withdrawMenu(Scanner scanner, ATMService atmService) {
        System.out.println("===== 예금출금 =====");
        System.out.print("금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.withdraw(money)) {
            System.out.println("출금이 완료되었습니다.");
        } else {
            System.out.println("출금에 실패했습니다.");
        }
    }

    public static void depositTransferMenu(Scanner scanner, ATMService atmService, LanguageManager languageManager) {
        System.out.println("===== 입금/무통장입금 =====");
        System.out.println("1. 입금");
        System.out.println("2. 무통장입금");
        System.out.print(languageManager.getText("select"));

        int menu = scanner.nextInt();
        scanner.nextLine();

        switch (menu) {
            case 1 -> depositMenu(scanner, atmService);
            case 2 -> cashTransferMenu(scanner, atmService);
            default -> System.out.println(languageManager.getText("invalid_menu"));
        }
    }

    public static void depositMenu(Scanner scanner, ATMService atmService) {
        System.out.print("금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.deposit(money)) {
            System.out.println("입금이 완료되었습니다.");
        } else {
            System.out.println("입금에 실패했습니다.");
        }
    }

    public static void cashTransferMenu(Scanner scanner, ATMService atmService) {
        System.out.print("보내는 사람 이름 입력: ");
        String senderName = scanner.nextLine();

        System.out.print("받는 계좌번호 입력: ");
        String targetAccountNumber = scanner.nextLine();

        System.out.print("금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.cashTransfer(targetAccountNumber, money)) {
            System.out.println(senderName + "님의 무통장입금이 완료되었습니다.");
        } else {
            System.out.println("무통장입금에 실패했습니다.");
        }
    }

    public static void accountTransferMenu(Scanner scanner, ATMService atmService) {
        System.out.println("===== 계좌이체 =====");
        System.out.print("이체할 계좌번호 입력: ");
        String targetAccountNumber = scanner.nextLine();

        System.out.print("금액 입력: ");
        int money = scanner.nextInt();
        scanner.nextLine();

        if (atmService.transfer(targetAccountNumber, money)) {
            System.out.println("계좌이체가 완료되었습니다.");
        } else {
            System.out.println("계좌이체에 실패했습니다.");
        }
    }

    public static void bankbookMenu(ATMService atmService) {
        System.out.println("===== 통장정리 =====");
        atmService.showTransactionHistory();
    }

    public static void inquiryMenu(ATMService atmService) {
        System.out.println("===== 예금조회 =====");
        atmService.showCurrentAccountInfo();
    }

    public static void cashbeeMenu(Scanner scanner, ATMService atmService) {
        System.out.println("===== 캐시비(하이패스) =====");
        System.out.println("1. 캐시비 충전");
        System.out.println("2. 하이패스 충전");
        System.out.println("3. 잔액 조회");
        System.out.print("선택: ");

        int menu = scanner.nextInt();
        scanner.nextLine();

        switch (menu) {
            case 1:
                System.out.print("금액 입력: ");
                int cashbeeMoney = scanner.nextInt();
                scanner.nextLine();

                if (atmService.chargeCashbee(cashbeeMoney)) {
                    System.out.println("캐시비 충전이 완료되었습니다.");
                } else {
                    System.out.println("캐시비 충전에 실패했습니다.");
                }
                break;
            case 2:
                System.out.print("금액 입력: ");
                int hiPassMoney = scanner.nextInt();
                scanner.nextLine();

                if (atmService.chargeHiPass(hiPassMoney)) {
                    System.out.println("하이패스 충전이 완료되었습니다.");
                } else {
                    System.out.println("하이패스 충전에 실패했습니다.");
                }
                break;
            case 3:
                atmService.showCashbeeInfo();
                break;
            default:
                System.out.println("잘못된 메뉴 번호입니다.");
        }
    }

    public static void otherMenu(Scanner scanner, LanguageManager languageManager) {
        System.out.println(languageManager.getText("other_title"));
        selectLanguage(scanner, languageManager);
    }
}
