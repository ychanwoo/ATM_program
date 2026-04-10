package components;

public class ATMService {
    private BankData bankData;
    private Account currentAccount;
    private int loginFailCount;

    public ATMService(BankData bankData) {
        this.bankData = bankData;
        this.currentAccount = null;
        this.loginFailCount = 0;
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

    public void logout() {
        currentAccount = null;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public boolean deposit(int money) {
        if (currentAccount == null) {
            return false;
        }

        if (money <= 0) {
            return false;
        }

        currentAccount.deposit(money);
        return true;
    }

    public boolean withdraw(int money) {
        if (currentAccount == null) {
            return false;
        }

        if (money <= 0) {
            return false;
        }

        return currentAccount.withdraw(money);
    }

    public boolean transfer(String targetAccountNumber, int money) {
        if (currentAccount == null) {
            return false;
        }

        if (money <= 0) {
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
                return true;
            }
        }

        return false;
    }

    public void showCurrentAccountInfo() {
        if (currentAccount == null) {
            System.out.println("로그인된 계좌가 없습니다.");
            return;
        }

        currentAccount.showAccountInfo();
    }
}
