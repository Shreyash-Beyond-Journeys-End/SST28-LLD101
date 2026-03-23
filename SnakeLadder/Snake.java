public class Snake extends BoardEntity {
    public Snake(int start, int end) {
        this.jump = end - start;
    }
}
