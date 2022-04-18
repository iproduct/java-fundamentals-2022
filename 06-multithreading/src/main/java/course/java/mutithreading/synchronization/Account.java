package course.java.mutithreading.synchronization;

public class Account {
    private String id;
    private long ballanceInCents;

    public Account() {
    }

    public Account(String id, long ballanceInCents) {
        this.id = id;
        this.ballanceInCents = ballanceInCents;
    }

    synchronized public long getBallanceInCents() {
        return ballanceInCents;
    }

    public String getId() {
        return id;
    }

    synchronized public void setBallanceInCents(long ballanceInCents) {
        this.ballanceInCents = ballanceInCents;
    }
}
