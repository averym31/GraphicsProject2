import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;

class DrawPanel extends JPanel implements MouseListener {

    private Deck deck;
    private Card currentCard;

    public DrawPanel() {
        deck = new Deck();
        currentCard = deck.getRandomCard();
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        g.drawImage(currentCard.getImage(), x, y, null);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}