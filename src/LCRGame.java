import java.util.ArrayList;
import java.util.List;

public class LCRGame {

    private static Die die;
    private static Player player1;
    private static Player player2;
    private static Player player3;
    private Player currentPlayer;

    private static List<Player> players = new ArrayList<Player>();
    private static List<Character> dice = new ArrayList<Character>();

    private final int MAXIMUM_PLAYERS = 2; // 0 is the first
    private Player winner;

    public LCRGame() {
        die = new Die();
        player1 = new Player("Anton", 0);
        player2 = new Player("Kalle", 1);
        player3 = new Player("Andreas", 2);
        winner = new Player("", 0);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        currentPlayer = player1;
    }

    public void playerTurn(Player player) {
        char symbol = Character.MIN_VALUE;
        int chips = player.getChips();
        for(int i = 0; i < chips; i++) {
            symbol = die.roll();
            distributeChip(symbol, player);
            dice.add(symbol);
            if (i == 2) { // dont roll more than 3 dice
                break;
            }
        }
        changeCurrentPlayer(player);
    }

    private void changeCurrentPlayer(Player player) {
        if(player.getId()==MAXIMUM_PLAYERS) {
            currentPlayer = player1;
        } else {
            currentPlayer = players.get(player.getId()+1);
        }
    }

    public boolean allowedToRoll(Player player) {
        if(player.getChips() == 0) {
            return false;
        }
        return true;
    }

    public boolean checkWinner() {
        boolean p1 = allowedToRoll(player1);
        boolean p2 = allowedToRoll(player2);
        boolean p3 = allowedToRoll(player3);

        if(p1&&!p2&&!p3) {
            winner = player1;
            return true;
        } else if (!p1&&p2&&!p3) {
            winner = player2;
            return true;
        } else if (!p1&&!p2&&p3) {
            winner = player3;
            return true;
        } else {
            return false;
        }
    }

    private Player getLeftPlayer(Player player) {
        Player leftPlayer;
        if(player.getId() == player3.getId()) {
            leftPlayer = player1;
        } else {
            leftPlayer = players.get(player.getId()+1);
        }
        return leftPlayer;
    }

    private Player getRightPlayer(Player player) {
        Player rightPlayer;
        if(player.getId() == player1.getId()) {
            rightPlayer = player3;
        } else {
            rightPlayer = players.get(player.getId()-1);
        }
        return rightPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Character> getDice() {
        return dice;
    }

    public static void setDice(List<Character> dice) {
        LCRGame.dice = dice;
    }

    public void clearDice() {
        dice.clear();
    }

    public Player getWinner() {
        return winner;
    }



    private void passToRight(Player player) {
        Player rightPlayer = getRightPlayer(player);
        int currentPlayerChips = player.getChips();
        int rightPlayerChips = players.get(rightPlayer.getId()).getChips();
        player.setChips(currentPlayerChips-1);
        players.get(rightPlayer.getId()).setChips(rightPlayerChips+1);
    }

    private void passToLeft(Player player) {
        Player leftPlayer = getLeftPlayer(player);
        int currentPlayerChips = player.getChips();
        int leftPlayerChips = players.get(leftPlayer.getId()).getChips();
        player.setChips(currentPlayerChips-1);
        players.get(leftPlayer.getId()).setChips(leftPlayerChips+1);
    }

    private void placeOnCenter(Player player) {
        int currentPlayerChips = player.getChips();
        player.setChips(currentPlayerChips-1);
    }

    public void distributeChip(char symbol, Player player) {
        switch (symbol) {
            case 'R':
                passToRight(player);
                break;
            case 'L':
                passToLeft(player);
                break;
            case 'C':
                placeOnCenter(player);
                break;
            case '.':
                break;
        }
    }
}