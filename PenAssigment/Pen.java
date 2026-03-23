public abstract class Pen {
    private String inkColor;
    private boolean started;
    private final StartingMechanism startingMechanism;

    public Pen(String inkColor, StartingMechanism startingMechanism) {
        this.inkColor = inkColor;
        this.startingMechanism = startingMechanism;
        this.started = false;
    }

    protected abstract String getPenLabel();

    public void write() {
        checkIfStarted();
        System.out.println(buildWriteMessage());
    }

    public void refill(String newColor) {
        inkColor = validateInkColor(newColor);
        System.out.println("Pen refilled. New ink color is " + inkColor + ".");
    }

    public void start() {
        startingMechanism.start(this);
    }

    public void close() {
        startingMechanism.close(this);
    }

    public void checkIfStarted() {
        if (!started) {
            throw new IllegalStateException("Cannot write before starting the pen.");
        }
    }

    public String getInkColor() {
        return inkColor;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    protected String buildWriteMessage() {
        return getPenLabel() + " writes  with " + getInkColor() + " ink.";
    }

    private String validateInkColor(String inkColor) {
        String normalizedInkColor = inkColor.trim();

        if (normalizedInkColor.isEmpty()) {
            throw new IllegalArgumentException("Ink color cannot be empty.");
        }

        return normalizedInkColor;
    }
}
