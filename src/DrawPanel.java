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
    private boolean isValid;
    Rectangle playAgainHitbox;
    Rectangle replaceCardsHitbox;

    public DrawPanel() {
        deck = new Deck();
        playAgainHitbox = new Rectangle(100, 300, 150, 50);
        replaceCardsHitbox = new Rectangle(320, 10, 150, 50);
        isValid = false;
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
                if (screen[r][c].isHighlighted()){
                    g.drawRect((int) screen[r][c].getHitbox().getX(), (int) screen[r][c].getHitbox().getY(), (int) screen[r][c].getHitbox().getWidth(), (int) screen[r][c].getHitbox().getHeight());
                }
                x += 100;
            }
            y += 100;
            x = 50;
        }

        g.drawString("Number of times game has been reset: " + numReset, x, y + 80);
        g.drawString("Number of cards left: " + deck.getCardDeck().size(), x, y + 100);

        if (deck.getCardDeck().isEmpty()){
            g.drawString("Congrats, you win!, Click the play again button to start a new game.", x, y + 120);
        }
        if (!deck.validMoveSet(screen)){
            g.drawString("You lose!, Click the play again button to start a new game.", x, y + 120);
        }

        g.drawRect(320, 10, 150, 50);
        g.drawString("REPLACE CARDS", 345, 40);

        g.drawRect(100, 300, 150, 50);
        g.drawString("PLAY AGAIN!", 135,  330);

    }

    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        int button = e.getButton();
        int sumH = 0;
        String JackQueenKing = "";
        // 1 = lclick, 3 = rclick

        // when u click on the play again button
        if (playAgainHitbox.contains(p) && button == 1){
            numReset++;
            for (Card[] cards : screen) {
                for (int c = 0; c < screen[0].length; c++) {
                    cards[c].resetHighlight();
                }
            }
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
                if (button == 3 && screen[r][c].getHitbox().contains(p)) {
                    screen[r][c].setHighlighted();
                }
            }
        }

        // when u click on the replace cards button
        if (replaceCardsHitbox.contains(p) && button == 1){
            for (int r = 0; r < screen.length; r++) {
                for (int c = 0; c < screen[0].length; c++) {
                    if (Card.numHighlighted == 2){
                        if (screen[r][c].isHighlighted()){
                            if (screen[r][c].getValue() == "A"){
                                sumH += 1;
                            }
                            else {
                                sumH += Integer.parseInt(screen[r][c].getValue());
                            }
                        }
                    }
                    else if (Card.numHighlighted == 3){
                        if (screen[r][c].getValue() == "J"){
                            JackQueenKing += "J";
                        }
                        if (screen[r][c].getValue() == "Q"){
                            JackQueenKing += "Q";
                        }
                        if (screen[r][c].getValue() == "K"){
                            JackQueenKing += "K";
                        }
                        if (JackQueenKing.contains("J") && JackQueenKing.contains("Q") && JackQueenKing.contains("K")){
                            sumH = 11;
                        }
                    }
                }
            }
            if (sumH == 11){
                for (int r = 0; r < screen.length; r++) {
                    for (int c = 0; c < screen[0].length; c++) {
                        if (screen[r][c].isHighlighted()){
                            screen[r][c] = deck.getRandomCard();
                            Card.numHighlighted--;
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