public class Ladder extends BoardEntity {
    public Ladder(int start, int end) {
        this.jump = end - start;
    }
}
