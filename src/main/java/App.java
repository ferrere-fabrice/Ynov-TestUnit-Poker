import org.example.poker.card.Card;
import org.example.poker.hand.Hand;
import org.example.poker.table.Dealer;

public class App {
    public static void main(String[] args) {
        Hand handA = new Hand(
                Card.fromString("AP"),
                Card.fromString("2K"),
                Card.fromString("5T"),
                Card.fromString("VC"),
                Card.fromString("9C")
        );
        Hand handB = new Hand(
                Card.fromString("AK"),
                Card.fromString("RK"),
                Card.fromString("RT"),
                Card.fromString("VP"),
                Card.fromString("XC")
        );

        Dealer.GetResultHand(handB);

    }
}
