package components;

public class BankData {
    private Account[] accounts;

    public BankData() {
        accounts = new Account[5];

        accounts[0] = new Account("1001", "1111", 1000000);
        accounts[1] = new Account("1002", "2222", 1000000);
        accounts[2] = new Account("1003", "3333", 1000000);
        accounts[3] = new LoanAccount("2001", "4444", 0, 500000);
        accounts[4] = new LoanAccount("2002", "5555", 0, 300000);
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void showAllAccounts() {
        System.out.println("===== 전체 계좌 목록 =====");

        for (int i = 0; i < accounts.length; i++) {
            System.out.println((i + 1) + "번 계좌");
            accounts[i].showAccountInfo();
            System.out.println();
        }
    }
}
