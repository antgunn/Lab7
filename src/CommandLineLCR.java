import java.util.Scanner;

/**
 * This class is a skeleton. Don't change the overall
 * structure but just uncomment and add code where needed.
 */
public class CommandLineLCR {

  public static void main(String[] args) {

      // Use this as an test area when starting out
      // I.e. instantiate objects and test
      // Later just comment out (don't erase the test code!)

      //testDie();
      //testPlayer();
      //testLCRGame();

      runCommandLineLCR();
  }

  public static void runCommandLineLCR() {

    LCRGame lcr = buildLCRGame();
    System.out.println("LCR started");
    render(lcr);

    Scanner s = new Scanner(System.in);

    boolean done = false;
    boolean quit = false;
    while (!done) {
        for(Player p : lcr.getPlayers()) {
            if(!lcr.checkWinner() && !quit) {
                if (lcr.allowedToRoll(p)) {
                    System.out.println("Current player is " + p.getName());
                    System.out.print("> ");
                    String cmd = s.nextLine();
                    switch (cmd) {
                        case "r":
                            //  Player one player's turn and render the game's state
                            lcr.playerTurn(p);
                            render(lcr);
                            lcr.clearDice();
                            break;
                        case "q":
                            done = true;
                            quit = true;
                            break;
                        default:
                            System.out.println("Enter 'r' to continue or 'q' to quit");
                    }
                }
            } else {
                done = true;
            }
        }
    }


    if(!quit) {
      // Game is finished
      System.out.println("Game over! Winner is " + lcr.getWinner());
    } else {
      // Game was aborted
      System.out.println("Game aborted");
      render(lcr);
    }
  }


  private static LCRGame buildLCRGame() {
    LCRGame lcr = new LCRGame();
    return lcr;
  }

  private static void render(LCRGame lcr) {
        // This needs overridden toString method to work!
        for (Character d : lcr.getDice()) {
            System.out.print(d.toString() + "  ");
        }
        System.out.println();
        System.out.print("Players: ");
        for (Player p : lcr.getPlayers()) {
            System.out.print(p.toString() + " ");
        }
        System.out.println("");

  }

  private static void testDie() {
      Die die = new Die();
      char c = die.roll();
      System.out.println("Die symbol " + c);
  }

  private static void testPlayer() {
      Player player = new Player("Anton",0);
      String name = player.getName();
      player.setChips(6);
      int chips = player.getChips();
      String s = player.toString();
      System.out.println(s);
  }

  private static void testLCRGame() {
      // private  methodes in lcrgame, change to public to test...
      LCRGame lcr = new LCRGame();
      Player player = new Player("Anton", 0);
      //lcr.switchPlayer(player);
      //lcr.playerTurn(player);
      //lcr.getLeftPlayer();
      //lcr.getRightPlayer();
      //lcr.allowedToRoll();
      //lcr.checkWinner();
  }
}
