package components;

public class ATMService {
    private BankData bankData;
    private Account currentAccount;
    private int loginFailCount;
    private Transaction[] transactions;
    private int transactionCount;
    private Cashbee cashbee;

    public ATMService(BankData bankData) {
        this.bankData = bankData;
        this.currentAccount = null;
        this.loginFailCount = 0;
        this.transactions = new Transaction[100];
        this.transactionCount = 0;
        this.cashbee = new Cashbee();
    }

    public boolean login(String accountNumber, String password) {
        Account[] accounts = bankData.getAccounts();

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                if (accounts[i].getPassword().equals(password)) {
                    currentAccount = accounts[i];
                    loginFailCount = 0;
                    return true;
                } else {
                    loginFailCount++;
                    return false;
                }
            }
        }

        loginFailCount++;
        return false;
    }

    public boolean isLoginLocked() {
        return loginFailCount >= 3;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public boolean deposit(int money) {
        if (currentAccount == null || money <= 0) {
            return false;
        }

        currentAccount.deposit(money);
        addTransaction("입금", currentAccount.getAccountNumber(), money, currentAccount.getBalance());
        return true;
    }

    public boolean withdraw(int money) {
        if (currentAccount == null || money <= 0) {
            return false;
        }

        if (currentAccount.withdraw(money)) {
            addTransaction("출금", currentAccount.getAccountNumber(), money, currentAccount.getBalance());
            return true;
        }

        return false;
    }

    public boolean transfer(String targetAccountNumber, int money) {
        if (currentAccount == null || money <= 0) {
            return false;
        }

        if (currentAccount.getAccountNumber().equals(targetAccountNumber)) {
            return false;
        }

        Account[] accounts = bankData.getAccounts();
        Account targetAccount = null;

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountNumber().equals(targetAccountNumber)) {
                targetAccount = accounts[i];
                break;
            }
        }

        if (targetAccount == null) {
            return false;
        }

        if (currentAccount.withdraw(money)) {
            targetAccount.deposit(money);
            addTransaction("계좌이체", currentAccount.getAccountNumber(), money, currentAccount.getBalance());
            return true;
        }

        return false;
    }

    public boolean cashTransfer(String targetAccountNumber, int money) {
        if (money <= 0) {
            return false;
        }

        Account[] accounts = bankData.getAccounts();

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountNumber().equals(targetAccountNumber)) {
                accounts[i].deposit(money);
                addTransaction("무통장입금", accounts[i].getAccountNumber(), money, accounts[i].getBalance());
                return true;
            }
        }

        return false;
    }

    public boolean chargeCashbee(int money) {
        if (currentAccount == null || money <= 0) {
            return false;
        }

        if (currentAccount.withdraw(money)) {
            cashbee.chargeCashbee(money);
            addTransaction("캐시비충전", currentAccount.getAccountNumber(), money, currentAccount.getBalance());
            return true;
        }

        return false;
    }

    public boolean chargeHiPass(int money) {
        if (currentAccount == null || money <= 0) {
            return false;
        }

        if (currentAccount.withdraw(money)) {
            cashbee.chargeHiPass(money);
            addTransaction("하이패스충전", currentAccount.getAccountNumber(), money, currentAccount.getBalance());
            return true;
        }

        return false;
    }

    public void showCashbeeInfo() {
        cashbee.showInfo();
    }

    public void showCurrentAccountInfo() {
        if (currentAccount == null) {
            System.out.println("로그인된 계좌가 없습니다.");
            return;
        }

        currentAccount.showAccountInfo();
    }

    public void showTransactionHistory() {
        if (currentAccount == null) {
            System.out.println("로그인된 계좌가 없습니다.");
            return;
        }

        boolean found = false;

        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getAccountNumber().equals(currentAccount.getAccountNumber())) {
                transactions[i].showTransactionInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("거래내역이 없습니다.");
        }
    }

    private void addTransaction(String type, String accountNumber, int amount, int balanceAfter) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount] = new Transaction(type, accountNumber, amount, balanceAfter);
            transactionCount++;
        }
    }
}
