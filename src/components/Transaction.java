package components;

public class Transaction {
    private String type;
    private String accountNumber;
    private int amount;
    private int balanceAfter;

    public Transaction(String type, String accountNumber, int amount, int balanceAfter) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public String getType() {
        return type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

    public void showTransactionInfo() {
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("  거래종류 : " + type);
        System.out.println("  계좌번호 : " + accountNumber);
        System.out.println("  거래금액 : " + amount + "원");
        System.out.println("  거래후잔액 : " + balanceAfter + "원");
        System.out.println("└──────────────────────────────────────────────┘");
    }
}
