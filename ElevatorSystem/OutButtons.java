public class OutButtons {
    private boolean upwardRequest;
    private boolean downwardRequest;

    void pressUp() {
        upwardRequest = true;
    }

    void pressDown() {
        downwardRequest = true;
    }

    boolean isUpRequested() {
        return upwardRequest;
    }

    boolean isDownRequested() {
        return downwardRequest;
    }

    void resetUp() {
        upwardRequest = false;
    }

    void resetDown() {
        downwardRequest = false;
    }
}
