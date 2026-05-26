import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener {

    private Deck deck;
    private Card currentCard;
    Card[][] screen = new Card[3][3];
    private int numReset;
    Rectangle playAgainHitbox;
    Rectangle replaceCardsHitbox;
    private ArrayList<Card> highlighted;

    public DrawPanel() {
        highlighted = new ArrayList<Card>();
        deck = new Deck();
        playAgainHitbox = new Rectangle(100, 300, 150, 50);
        replaceCardsHitbox = new Rectangle(320, 10, 150, 50);
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
        Font customFont = new Font("Verdana", Font.PLAIN, 12);
        g.setFont(customFont);
        int x = 50;
        int y = 10;
        for (int r = 0; r < screen.length; r++) {
            for (int c = 0; c < screen.length; c++) {
                g.drawImage(screen[r][c].getImage(), x, y, null);
                Rectangle cardHitBox = new Rectangle(x, y, screen[r][c].getImage().getWidth(), screen[r][c].getImage().getHeight());
                screen[r][c].setHitbox(cardHitBox);
                x += 100;
            }
            y += 100;
            x = 50;
        }
        if (highlighted != null){
            for (Card card : highlighted) {
                g.drawRect((int) card.getHitbox().getX(), (int) card.getHitbox().getY(), (int) card.getHitbox().getWidth(), (int) card.getHitbox().getHeight());
            }
        }

        g.drawString("Number of times game has been reset: " + numReset, x, y + 80);
        g.drawString("Number of cards left: " + deck.getCardDeck().size(), x, y + 100);

        g.drawRect(320, 10, 150, 50);
        g.drawString("REPLACE CARDS", 345, 40);

        g.drawRect(100, 300, 150, 50);
        g.drawString("PLAY AGAIN!", 135,  330);

    }

    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        int button = e.getButton();
        // 1 = lclick, 3 = rclick

        // when u click on the play again button
        if (playAgainHitbox.contains(p) && button == 1){
            numReset++;
            deck = new Deck();
            for (int r = 0; r < screen.length; r++){
                for (int c = 0; c < screen[0].length; c++){
                    currentCard = deck.getRandomCard();
                    screen[r][c] = currentCard;
                }
            }
        }

        // when u click on card
        for (int r = 0; r < screen.length; r++) {
            for (int c = 0; c < screen[0].length; c++) {
//                if (!deck.getCardDeck().isEmpty() && button == 1) {
//                    if (screen[r][c].getHitbox().contains(p)) {
//                        screen[r][c] = deck.getRandomCard();
//                    }
//                }
                if (button == 3 && screen[r][c].getHitbox().contains(p)) {
                    if (!highlighted.contains(screen[r][c])){
                        highlighted.add(screen[r][c]);
                    }
                    else {
                        highlighted.remove(screen[r][c]);
                    }
                }
            }
        }

        // when u click on the replace cards button
        if (replaceCardsHitbox.contains(p) && button == 1){
            if (highlighted != null){
                for (int i = 0; i < highlighted.size(); i++){
                    highlighted.remove(i);
                    i--;
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}