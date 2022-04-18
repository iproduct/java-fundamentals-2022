package course.java.mutithreading.synchronization;

import java.util.Random;

class Client implements Runnable {
    private long id;
    private Bank bank;
    private Random rand = new Random();
    private volatile boolean canceled;

    public Client(long id, Bank bank) {
        this.id = id;
        this.bank = bank;
    }

    public void cancel() {
        canceled = true;
    }

    @Override
    public void run() {
        while(!canceled && !Thread.interrupted()) {
            bank.transferAmountInCents(rand.nextInt(100) + 1L,
                    bank.getAccountA(), bank.getAccountB());
            try {
                Thread.sleep(rand.nextInt(100));
            } catch (InterruptedException e) {
                System.out.printf("Client No. %d was interrupted.", id); ;
            }
        }
    }
}

public class Bank {
    public static final long ACCOUNT_A_AMOUNT = 100000;
    public static final long ACCOUNT_B_AMOUNT = 300000;
    private Account accountA = new Account("A", ACCOUNT_A_AMOUNT);
    private Account accountB = new Account("B", ACCOUNT_B_AMOUNT);

    public Account getAccountA() {
        return accountA;
    }

    public Account getAccountB() {
        return accountB;
    }

    public boolean transferAmountInCents(long amount, Account from, Account to) {
        if(from.getBallanceInCents() < amount) return false; // not enough money
        from.setBallanceInCents(from.getBallanceInCents() - amount);
        to.setBallanceInCents(to.getBallanceInCents() + amount);
        System.out.printf("Transfer from %s to %s: %8.2f Euro%n",
                from.getId(), to.getId(), amount / 100.0);
        return true;
    }

    public boolean checkAmountsTotal() {
        return accountA.getBallanceInCents() + accountB.getBallanceInCents() ==
                ACCOUNT_A_AMOUNT + ACCOUNT_B_AMOUNT;
    }

    public static void main(String[] args) {

    }
}
