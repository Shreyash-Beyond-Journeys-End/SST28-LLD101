import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private GameVersion version;
    private List<Player> winners;
    private int turn;

    public Game(Board board, List<Player> players, Dice dice, GameVersion version) {
        this.board = board;
        this.players = players;
        this.dice = dice;
        this.version = version;
        this.winners = new ArrayList<>();
        this.turn = 0;
    }

    public void makeTurn() {
        if (isFinished()) {
            return;
        }

        turn = nextActiveTurn(turn);
        Player current = players.get(turn);
        int sixCount = 0;
        boolean playAgain = true;

        while (playAgain) {
            playAgain = false;

            int diceValue = dice.roll();
            System.out.println(current.getName() + " turn");
            System.out.println("dice " + diceValue);

            int next = current.getPosition() + diceValue;
            if (next <= board.getLastCell()) {
                current.setPosition(next);
            }

            BoardEntity entity = board.getCell(current.getPosition());
            if (entity != null) {
                current.setPosition(current.getPosition() + entity.getJump());
            }

            System.out.println("now " + current.getPosition());

            if (current.getPosition() == board.getLastCell()) {
                winners.add(current);
                System.out.println(current.getName() + " won");
                if (!isFinished()) {
                    turn = nextActiveTurn((turn + 1) % players.size());
                }
                return;
            }

            if (diceValue == 6) {
                sixCount++;
                if (version == GameVersion.EASY) {
                    playAgain = true;
                } else if (sixCount < 3) {
                    playAgain = true;
                } else {
                    System.out.println("no extra turn");
                }
            }
        }

        turn = nextActiveTurn((turn + 1) % players.size());
    }

    public boolean isFinished() {
        return players.size() - winners.size() < 2;
    }

    public Player getWinner() {
        return winners.isEmpty() ? null : winners.get(0);
    }

    public List<Player> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    private int nextActiveTurn(int start) {
        for (int i = 0; i < players.size(); i++) {
            int index = (start + i) % players.size();
            if (!winners.contains(players.get(index))) {
                return index;
            }
        }
        return start;
    }
}
