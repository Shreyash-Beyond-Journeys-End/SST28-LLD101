public class PenFactory {
    public static Pen createPen(String penType, String inkColor, String mechanismType) {
        StartingMechanism mechanism = StartingMechanismFactory.createMechanism(mechanismType);
        String normalizedPenType = normalize(penType);

        switch (normalizedPenType) {
            case "ballpoint":
            case "ballpoint pen":
                return buildBallpointPen(inkColor, mechanism);
            case "gel":
            case "gel pen":
                return buildGelPen(inkColor, mechanism);
            case "ink":
            case "ink pen":
                return buildInkPen(inkColor, mechanism);
            default:
                throw new IllegalArgumentException("Unsupported pen type: " + penType);
        }
    }

    private static BallpointPen buildBallpointPen(String inkColor, StartingMechanism mechanism) {
        return new BallpointPen(inkColor, mechanism);
    }

    
    private static GelPen buildGelPen(String inkColor, StartingMechanism mechanism) {
        return new GelPen(inkColor, mechanism);
    }

    private static InkPen buildInkPen(String inkColor, StartingMechanism mechanism) {
        return new InkPen(inkColor, mechanism);
    }

    private static String normalize(String value) {
        return value.trim().toLowerCase();
    }
}
