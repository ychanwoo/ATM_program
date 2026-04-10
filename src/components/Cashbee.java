package components;

public class Cashbee {
    private int cashbeeBalance;
    private int hiPassBalance;

    public Cashbee() {
        this.cashbeeBalance = 0;
        this.hiPassBalance = 0;
    }

    public int getCashbeeBalance() {
        return cashbeeBalance;
    }

    public int getHiPassBalance() {
        return hiPassBalance;
    }

    public boolean chargeCashbee(int money) {
        if (money <= 0) {
            return false;
        }

        cashbeeBalance += money;
        return true;
    }

    public boolean chargeHiPass(int money) {
        if (money <= 0) {
            return false;
        }

        hiPassBalance += money;
        return true;
    }

    public boolean useCashbee(int money) {
        if (money <= 0) {
            return false;
        }

        if (cashbeeBalance >= money) {
            cashbeeBalance -= money;
            return true;
        }

        return false;
    }

    public boolean useHiPass(int money) {
        if (money <= 0) {
            return false;
        }

        if (hiPassBalance >= money) {
            hiPassBalance -= money;
            return true;
        }

        return false;
    }

    public void showInfo() {
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│             캐시비 / 하이패스 정보          │");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("  캐시비 잔액   : " + cashbeeBalance + "원");
        System.out.println("  하이패스 잔액 : " + hiPassBalance + "원");
        System.out.println("└──────────────────────────────────────────────┘");
    }
}
