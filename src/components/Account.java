package components;

public class Account {
    private String accountNumber;
    private String password;
    private int balance;

    public Account(String accountNumber, String password, int balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int money) {
        if (money > 0) {
            balance += money;
        }
    }

    public boolean withdraw(int money) {
        if (money <= 0) {
            return false;
        }

        if (balance >= money) {
            balance -= money;
            return true;
        }

        return false;
    }

    public void showAccountInfo() {
        System.out.println("계좌번호: " + accountNumber);
        System.out.println("잔액: " + balance + "원");
    }
}
