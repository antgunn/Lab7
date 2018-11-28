import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class GraphicalLCR extends JFrame {

    private LCRGame lcr;

    private JPanel mainPanel;
    private JPanel playersPanel;
    private JPanel dicePanel;
    private JPanel buttonsPanel;

    private  GraphicalLCR(LCRGame lcr) {
        this.lcr = lcr;
        mainPanel = new JPanel();
        add(mainPanel);

        // Frame settings
        setSize(350,500);
        setTitle("LCR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        playersPanel = new JPanel();
        dicePanel = new JPanel();
        buttonsPanel = new JPanel();

        // Add other panels to mainPanel
        mainPanel.add(playersPanel);
        mainPanel.add(dicePanel);
        mainPanel.add(buttonsPanel);

        // Set layout
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        // Set border around all panels
        playersPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Players"));
        dicePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Dice"));
        buttonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Actions"));

        // Create panels
        createPlayersPanel();
        createDicePanel();
        createButtonsPanel();
    }

    private void createButtonsPanel() {
        JButton nextRoundButton = new JButton();
        JButton exitButton = new JButton();
        JLabel label = new JLabel();

        label.setText("Next player: " + lcr.getCurrentPlayer().getName());

        buttonsPanel.add(nextRoundButton);
        buttonsPanel.add(exitButton);

        nextRoundButton.setText("Next Round");
        exitButton.setText("Exit");

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
        dicePanel.add(diceLabel);
        String text = "";
        for (Character die:lcr.getDice()) {
            text = text + " " + die.toString();
        }
        diceLabel.setText(text);
    }

    private void labelDesign(JLabel label, Player p) {
        label.setText("<html>" + p.getName() + "<br>" + "Chips: " + p.getChips() + "</html>");
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBackground(Color.white);
    }


    public static void main(String args[]) {
        LCRGame lcr = new LCRGame();
        lcr.setDice('C');
        lcr.setDice('R');
        lcr.setDice('L');
        new GraphicalLCR(lcr);
    }
}
