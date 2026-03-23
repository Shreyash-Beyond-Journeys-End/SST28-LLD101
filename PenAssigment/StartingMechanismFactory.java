public class StartingMechanismFactory {
    public static StartingMechanism createMechanism(String mechanismType) {
        String normalizedMechanismType = normalize(mechanismType);

        switch (normalizedMechanismType) {
            case "cap":
                return buildCapMechanism();
            case "click":
                return buildClickMechanism();
            default:
                throw new IllegalArgumentException("Unsupported starting mechanism: " + mechanismType);
        }
    }

    private static CapMechanism buildCapMechanism() {
        return new CapMechanism();
    }

    private static ClickMechanism buildClickMechanism() {
        return new ClickMechanism();
    }

    private static String normalize(String value) {
        return value.trim().toLowerCase();
    }
}
