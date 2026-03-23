public class BallpointPen extends Pen {
    public BallpointPen(String inkColor, StartingMechanism startingMechanism) {
        super(inkColor, startingMechanism);
    }

    @Override
    protected String getPenLabel() {
        return "Ballpoint pen";
    }
}
