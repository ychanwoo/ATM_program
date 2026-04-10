package components;

public class LanguageManager {
    private int language;

    public LanguageManager() {
        this.language = 1;
    }

    public void setLanguage(int language) {
        if (language >= 1 && language <= 4) {
            this.language = language;
        } else {
            this.language = 1;
        }
    }

    public int getLanguage() {
        return language;
    }

    public String getText(String key) {
        return switch (language) {
            case 2 -> getEnglishText(key);
            case 3 -> getJapaneseText(key);
            case 4 -> getChineseText(key);
            default -> getKoreanText(key);
        };
    }

    private String getKoreanText(String key) {
        return switch (key) {
            case "language_title" -> "===== 언어 선택 =====";
            case "language_1" -> "1. 한국어";
            case "language_2" -> "2. English";
            case "language_3" -> "3. 일본어";
            case "language_4" -> "4. 중국어";
            case "select" -> "선택: ";
            case "selected_korean" -> "한국어가 선택되었습니다.";
            case "selected_english" -> "영어가 선택되었습니다.";
            case "selected_japanese" -> "일본어가 선택되었습니다.";
            case "selected_chinese" -> "중국어가 선택되었습니다.";
            case "default_language" -> "기본 언어는 한국어로 설정됩니다.";
            case "login_title" -> "===== 계좌 정보 입력 =====";
            case "input_account" -> "계좌번호 입력: ";
            case "input_password" -> "비밀번호 입력: ";
            case "login_success" -> "잠시만 기다려주세요.";
            case "login_fail" -> "다시 시도해주세요.";
            case "login_locked" -> "3회 실패로 프로그램을 종료합니다.";
            case "main_menu" -> "===== ATM 메인 메뉴 =====";
            case "menu_1" -> "1. 예금출금";
            case "menu_2" -> "2. 입금/무통장입금";
            case "menu_3" -> "3. 계좌이체";
            case "menu_4" -> "4. 통장정리";
            case "menu_5" -> "5. 예금조회";
            case "menu_6" -> "6. 장기카드대출";
            case "menu_7" -> "7. 캐시비(하이패스)";
            case "menu_8" -> "8. 다른업무(언어설정)";
            case "menu_0" -> "0. 종료";
            case "menu_input" -> "메뉴 선택: ";
            case "invalid_menu" -> "잘못된 메뉴 번호입니다.";
            case "exit" -> "ATM 프로그램을 종료합니다.";
            case "other_title" -> "===== 다른업무 =====";
            default -> "";
        };
    }

    private String getEnglishText(String key) {
        return switch (key) {
            case "language_title" -> "===== Language Selection =====";
            case "language_1" -> "1. Korean";
            case "language_2" -> "2. English";
            case "language_3" -> "3. Japanese";
            case "language_4" -> "4. Chinese";
            case "select" -> "Select: ";
            case "selected_korean" -> "Korean selected.";
            case "selected_english" -> "English selected.";
            case "selected_japanese" -> "Japanese selected.";
            case "selected_chinese" -> "Chinese selected.";
            case "default_language" -> "Default language is Korean.";
            case "login_title" -> "===== Login =====";
            case "input_account" -> "Enter account number: ";
            case "input_password" -> "Enter password: ";
            case "login_success" -> "Login success";
            case "login_fail" -> "Login failed";
            case "login_locked" -> "Program ends after 3 failed login attempts.";
            case "main_menu" -> "===== ATM Main Menu =====";
            case "menu_1" -> "1. Withdrawal";
            case "menu_2" -> "2. Deposit / Cash Transfer";
            case "menu_3" -> "3. Account Transfer";
            case "menu_4" -> "4. Bankbook Update";
            case "menu_5" -> "5. Account Inquiry";
            case "menu_6" -> "6. Long-term Card Loan";
            case "menu_7" -> "7. Cashbee (Hi-Pass)";
            case "menu_8" -> "8. Other Services (Language)";
            case "menu_0" -> "0. Exit";
            case "menu_input" -> "Select menu: ";
            case "invalid_menu" -> "Invalid menu number.";
            case "exit" -> "ATM program will end.";
            case "other_title" -> "===== Other Services =====";
            default -> "";
        };
    }

    private String getJapaneseText(String key) {
        return switch (key) {
            case "language_title" -> "===== 言語選択 =====";
            case "language_1" -> "1. 韓国語";
            case "language_2" -> "2. 英語";
            case "language_3" -> "3. 日本語";
            case "language_4" -> "4. 中国語";
            case "select" -> "選択: ";
            case "selected_korean" -> "韓国語が選択されました。";
            case "selected_english" -> "英語が選択されました。";
            case "selected_japanese" -> "日本語が選択されました。";
            case "selected_chinese" -> "中国語が選択されました。";
            case "default_language" -> "基本言語は韓国語です。";
            case "login_title" -> "===== ログイン =====";
            case "input_account" -> "口座番号入力: ";
            case "input_password" -> "暗証番号入力: ";
            case "login_success" -> "ログイン成功";
            case "login_fail" -> "ログイン失敗";
            case "login_locked" -> "ログイン3回失敗で終了します。";
            case "main_menu" -> "===== ATM メインメニュー =====";
            case "menu_1" -> "1. 出金";
            case "menu_2" -> "2. 入金 / 無通帳入金";
            case "menu_3" -> "3. 口座振替";
            case "menu_4" -> "4. 通帳整理";
            case "menu_5" -> "5. 預金照会";
            case "menu_6" -> "6. 長期カードローン";
            case "menu_7" -> "7. Cashbee (Hi-Pass)";
            case "menu_8" -> "8. その他業務(言語設定)";
            case "menu_0" -> "0. 終了";
            case "menu_input" -> "メニュー選択: ";
            case "invalid_menu" -> "無効なメニューです。";
            case "exit" -> "ATMプログラムを終了します。";
            case "other_title" -> "===== その他業務 =====";
            default -> "";
        };
    }

    private String getChineseText(String key) {
        return switch (key) {
            case "language_title" -> "===== 语言选择 =====";
            case "language_1" -> "1. 韩语";
            case "language_2" -> "2. 英语";
            case "language_3" -> "3. 日语";
            case "language_4" -> "4. 中文";
            case "select" -> "选择: ";
            case "selected_korean" -> "已选择韩语。";
            case "selected_english" -> "已选择英语。";
            case "selected_japanese" -> "已选择日语。";
            case "selected_chinese" -> "已选择中文。";
            case "default_language" -> "默认语言为韩语。";
            case "login_title" -> "===== 登录 =====";
            case "input_account" -> "输入账号: ";
            case "input_password" -> "输入密码: ";
            case "login_success" -> "登录成功";
            case "login_fail" -> "登录失败";
            case "login_locked" -> "登录失败3次，程序结束。";
            case "main_menu" -> "===== ATM 主菜单 =====";
            case "menu_1" -> "1. 取款";
            case "menu_2" -> "2. 存款 / 无卡存款";
            case "menu_3" -> "3. 账户转账";
            case "menu_4" -> "4. 存折整理";
            case "menu_5" -> "5. 存款查询";
            case "menu_6" -> "6. 长期卡贷款";
            case "menu_7" -> "7. Cashbee (Hi-Pass)";
            case "menu_8" -> "8. 其他业务(语言设置)";
            case "menu_0" -> "0. 退出";
            case "menu_input" -> "选择菜单: ";
            case "invalid_menu" -> "菜单编号错误。";
            case "exit" -> "ATM程序结束。";
            case "other_title" -> "===== 其他业务 =====";
            default -> "";
        };
    }
}
