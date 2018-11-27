import java.util.Random;

public class Die {

    private int sides = 6;

    public Die() {}

    private int genRandomNumber() {
        Random random = new Random();
        int min = 1;
        int max = sides;
        int number = random.nextInt((max - min) + 1) + min;
        return number;
    }

    public char roll() {
        int number = genRandomNumber();
        char symbol = Character.MIN_VALUE;
        switch (number) {
            case 1:
                symbol = 'R';
                break;
            case 2:
                symbol = 'L';
                break;
            case 3:
                symbol = 'C';
                break;
            case 4:
                symbol = '.';
                break;
            case 5:
                symbol = '.';
                break;
            case 6:
                symbol = '.';
                break;

        }
        return symbol;
    }


}
