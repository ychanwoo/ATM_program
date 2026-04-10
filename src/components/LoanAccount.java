package components;

public class LoanAccount extends Account {
    private int loanLimit;

    public LoanAccount(String accountNumber, String password, int balance, int loanLimit) {
        super(accountNumber, password, balance);
        this.loanLimit = loanLimit;
    }

    public int getLoanLimit() {
        return loanLimit;
    }

    @Override
    public boolean withdraw(int money) {
        if (money <= 0) {
            return false;
        }

        if (getBalance() + loanLimit >= money) {
            setBalance(getBalance() - money);
            return true;
        }

        return false;
    }

    @Override
    public void showAccountInfo() {
        System.out.println("계좌번호: " + getAccountNumber());
        System.out.println("잔액: " + getBalance() + "원");
        System.out.println("대출한도: " + loanLimit + "원");
    }
}
