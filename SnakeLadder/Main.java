import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("board size");
        int boardSize = sc.nextInt();

        System.out.println("players");
        int playerCount = sc.nextInt();

        System.out.println("version");
        String value = sc.next().toUpperCase();
        GameVersion version = GameVersion.EASY;
        if (value.equals("DIFFICULT")) {
            version = GameVersion.DIFFICULT;
        }

        Game game = GameFactory.createGame(boardSize, playerCount, version);

        while (!game.isFinished()) {
            game.makeTurn();
        }

        sc.close();
    }
}
