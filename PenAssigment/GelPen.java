public class GelPen extends Pen {
    public GelPen(String inkColor, StartingMechanism startingMechanism) {
        super(inkColor, startingMechanism);
    }

    @Override
    protected String getPenLabel() {
        return "Gel pen";
    }
}
