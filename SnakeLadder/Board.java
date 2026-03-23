import java.util.Random;

public class Board {
    private int size;
    private int lastCell;
    private BoardEntity[] board;
    private boolean[] blocked;
    private Random random;

    public Board(int size) {
        this.size = size;
        this.lastCell = size * size - 1;
        this.board = new BoardEntity[size * size];
        this.blocked = new boolean[size * size];
        this.random = new Random();
        createBoard();
    }

    public int getLastCell() {
        return lastCell;
    }

    public BoardEntity getCell(int index) {
        return board[index];
    }

    private void createBoard() {
        int snakes = size - 1;
        int ladders = size - 1;

        if (snakes < 1) {
            snakes = 1;
        }
        if (ladders < 1) {
            ladders = 1;
        }

        addSnakes(snakes);
        addLadders(ladders);
    }

    private void addSnakes(int count) {
        int made = 0;
        int tries = 0;

        while (made < count && tries < 500) {
            tries++;
            int start = random.nextInt(lastCell - 1) + 1;
            int end = random.nextInt(start);

            if (end == 0) {
                continue;
            }

            if (blocked[start] || blocked[end]) {
                continue;
            }

            if (end >= start) {
                continue;
            }

            board[start] = new Snake(start, end);
            blocked[start] = true;
            blocked[end] = true;
            made++;
        }
    }

    private void addLadders(int count) {
        int made = 0;
        int tries = 0;

        while (made < count && tries < 500) {
            tries++;
            int start = random.nextInt(lastCell - 1) + 1;
            int end = random.nextInt(lastCell - start) + start + 1;

            if (end >= lastCell) {
                continue;
            }

            if (blocked[start] || blocked[end]) {
                continue;
            }

            if (end <= start) {
                continue;
            }

            board[start] = new Ladder(start, end);
            blocked[start] = true;
            blocked[end] = true;
            made++;
        }
    }
}
