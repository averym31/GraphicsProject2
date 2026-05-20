import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener {

    private Deck deck;
    private Card currentCard;
    Card[][] screen = new Card[3][3];

    public DrawPanel() {
        deck = new Deck();
        for (int r = 0; r < screen.length; r++){
            for (int c = 0; c < screen[0].length; c++){
                currentCard = deck.getRandomCard();
                screen[r][c] = currentCard;
            }
        }
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        for (int r = 0; r < screen.length; r++){
            for (int c = 0; c < screen[0].length; c++){
                g.drawImage(screen[r][c].getImage(), x + (100*c), y + (100*r), null);
            }
        }
        g.drawString("There are " + deck.getCardDeck().size() + " cards left in the deck!", 100, 100);
    }

    public void mousePressed(MouseEvent e) {
        if (!deck.getCardDeck().isEmpty()){
            for (int r = 0; r < screen.length; r++){
                for (int c = 0; c < screen[0].length; c++){
                    currentCard = deck.getRandomCard();
                    screen[r][c] = currentCard;
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}