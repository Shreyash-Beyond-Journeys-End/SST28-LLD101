import java.util.Random;

public class StandardDice implements Dice {
    Random random = new Random();

    public int roll() {
        return random.nextInt(6) + 1;
    }
}
