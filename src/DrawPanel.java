import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener {

    private Deck deck;
    private Card currentCard;
    Card[][] screen = new Card[3][3];
    private boolean rclick;

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
                Rectangle h = new Rectangle(50 + (100*c),10 + (100*r), screen[r][c].getImage().getWidth(), screen[r][c].getImage().getHeight());
                screen[r][c].setHitbox(h);
                if (rclick) {
                    g.drawRect(50 + (100*c),10 + (100*r), screen[r][c].getImage().getWidth(), screen[r][c].getImage().getHeight());
                }
            }
        }
        g.drawString("There are " + deck.getCardDeck().size() + " cards left in the deck!", 100, 300);
    }

    public void mousePressed(MouseEvent e) {

        Point p = e.getPoint();
        int button = e.getButton();
        // 1 = lclick, 3 = rclick
            for (int r = 0; r < screen.length; r++) {
                for (int c = 0; c < screen[0].length; c++) {
                    if (screen[r][c].getHitbox().contains(p)) {
                        if (!deck.getCardDeck().isEmpty()){
                            if (button == 1){
                                screen[r][c] = deck.getRandomCard();
                            }
                            else if (button == 3){
                                rclick = !rclick;
                            }
                        }
                    }
                }
            }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}