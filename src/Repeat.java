public enum Repeat {

    SINGLE("Однократная"),
    DAILY("Ежедневная"),
    WEEKLY("Еженедельная"),
    MONTHLY("Ежемесячная"),
    YEARLY("Ежегодная");

    private final String msg;

    Repeat(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
