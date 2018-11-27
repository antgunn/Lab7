import javax.swing.*;
import java.awt.*;

public class GraphicalLCR extends JFrame {

    private LCRGame lcr;

    private JPanel mainPanel;
    private JPanel playersPanel;
    private JPanel dicePanel;

    // This is a comment...

    private  GraphicalLCR(LCRGame lcr) {
        this.lcr = lcr;
        mainPanel = new JPanel();
        add(mainPanel);
        setSize(350,500);
        setTitle("LCR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        playersPanel = new JPanel();
        dicePanel = new JPanel();
        mainPanel.add(playersPanel);
        mainPanel.add(dicePanel);
        playersPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Players"));
        dicePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Dice"));

        createPlayersPanel();
        createDicePanel();
    }

    private void createPlayersPanel() {
        JLabel label0 = new JLabel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        playersPanel.add(label0);
        playersPanel.add(label1);
        playersPanel.add(label2);

        for(Player p : lcr.getPlayers()) {
            switch (p.getId()) {
                case 0:
                    labelDesign(label0, p);
                    break;
                case 1:
                    labelDesign(label1, p);
                    break;
                case 2:
                    labelDesign(label2, p);
                    break;
            }
        }
    }

    private void createDicePanel() {
        JLabel diceLabel = new JLabel();
        for (Character die:lcr.getDice()) {
            diceLabel.setText(die.toString());
        }
    }

    private void labelDesign(JLabel label, Player p) {
        label.setText(p.getName() + "\n" + "Chips: " + p.getChips());
        label.setBorder(BorderFactory.createLineBorder(Color.blue));
    }


    public static void main(String args[]) {
        LCRGame lcr = new LCRGame();
        new GraphicalLCR(lcr);
    }
}
