public class InkPen extends Pen {
    public InkPen(String inkColor, StartingMechanism startingMechanism) {
        super(inkColor, startingMechanism);
    }

    @Override
    public void write() {
        checkIfStarted();
        System.out.println(buildWriteMessage());
    }

    @Override
    protected String getPenLabel() {
        return "Ink pen";
    }

    @Override
    protected String buildWriteMessage() {
        return getPenLabel() + " writes  with  " + getInkColor() + " ink.";
    }
}
