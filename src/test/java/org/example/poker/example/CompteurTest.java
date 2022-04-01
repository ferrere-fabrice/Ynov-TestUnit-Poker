package org.example.poker.example;

import org.example.poker.card.Card;
import org.example.poker.hand.Hand;
import org.example.poker.table.Dealer;
import org.example.poker.tools.Output;
import org.junit.jupiter.api.*;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

// Cette classe est une suite de tests servant d'exemple et d'aide-mémoire de la syntaxe Java et JUnit.
// Elle n'est pas nécessaire à la réalisation de l'exercice.
public class CompteurTest {

    private final Predicate<Integer> estPair = (Integer nombre) -> (nombre % 2) == 0;

    @BeforeAll
    public static void globalSetUp() {
/*
        System.out.println("Ce code est exécuté une seule fois avant l'ensemble des tests");
*/
    }

    @BeforeEach
    public void setUp() {
        /*        System.out.println("Ce code est exécuté avant chaque test");*/
    }

    @AfterEach
    public void tearDown() {
/*
        System.out.println("Ce code est exécuté après chaque test");
*/
    }

    @AfterAll
    public static void globalTearDown() {
        /*        System.out.println("Ce code est exécuté une seule fois après l'ensemble des tests");*/
    }


    @Test
    public void TestBetterCard_OneWinner() {
        Output.PrintStartMessageTest();
        //given
        Card SimilarCard = Card.fromString("AP");
        Hand handA = new Hand(
                SimilarCard,
                Card.fromString("2K"),
                Card.fromString("5T"),
                Card.fromString("VC"),
                Card.fromString("9C"));

        Hand handB = new Hand(
                SimilarCard,
                Card.fromString("8K"),
                Card.fromString("RT"),
                Card.fromString("VP"),
                Card.fromString("XC")
        );

        //when
        Dealer dealerResult = Dealer.CompareStrongestCards(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(dealerResult.winners[0], handB, dealerResult.message);
        Output.PrintEndMessageTest(dealerResult);
    }

    @Test
    public void TestBetterCard_PerfectDraw() {
        Output.PrintStartMessageTest();
        //given
        Card SimilarCard1 = Card.fromString("AP");
        Card SimilarCard2 = Card.fromString("AC");
        Card SimilarCard3 = Card.fromString("AK");
        Card SimilarCard4 = Card.fromString("AT");
        Card SimilarCard5 = Card.fromString("RC");
        Hand handA = new Hand(
                SimilarCard1,
                SimilarCard2,
                SimilarCard3,
                SimilarCard4,
                SimilarCard5
        );

        Hand handB = new Hand(
                SimilarCard1,
                SimilarCard2,
                SimilarCard3,
                SimilarCard4,
                SimilarCard5
        );

        //when
        Dealer dealerResult = Dealer.CompareStrongestCards(handA, handB);

        //then
        assertEquals(dealerResult.winners.length, 2);
        Output.PrintEndMessageTest(dealerResult);
    }

    @Test
    public void haveBetterTwoPair() {
        Output.PrintStartMessageTest();
        Card SimilarCard1 = Card.fromString("AP");
        Card SimilarCard2 = Card.fromString("AC");
        Card SimilarCard3 = Card.fromString("RK");
        Card SimilarCard4 = Card.fromString("RT");
        Hand handA = new Hand(
                SimilarCard1,
                SimilarCard2,
                SimilarCard3,
                SimilarCard4,
                Card.fromString("4P"));

        Hand handB = new Hand(
                SimilarCard1,
                SimilarCard2,
                SimilarCard3,
                SimilarCard4,
                Card.fromString("3P"));

        Dealer dealerResult = Dealer.betterTwoPair(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handA, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }

    // Q3
    @Test
    public void haveBetterPairVsNone() {
        Output.PrintStartMessageTest();
        Hand handA = new Hand(
                Card.fromString("AP"),
                Card.fromString("9C"),
                Card.fromString("4K"),
                Card.fromString("5C"),
                Card.fromString("2P"));

        Hand handB = new Hand(
                Card.fromString("7P"),
                Card.fromString("7C"),
                Card.fromString("4K"),
                Card.fromString("5C"),
                Card.fromString("2P"));

        Dealer dealerResult = Dealer.betterPair(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handB, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }

    // Q4
    @Test
    public void haveBetterPair() {
        Output.PrintStartMessageTest();
        Hand handA = new Hand(
                Card.fromString("AP"),
                Card.fromString("AC"),
                Card.fromString("4K"),
                Card.fromString("5C"),
                Card.fromString("2P"));

        Hand handB = new Hand(
                Card.fromString("3P"),
                Card.fromString("3C"),
                Card.fromString("4K"),
                Card.fromString("5C"),
                Card.fromString("2P"));

        Dealer dealerResult = Dealer.betterPair(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handA, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }

    // Q5
    @Test
    public void haveSamePairGetStrongestCard() {
        Output.PrintStartMessageTest();
        Hand handA = new Hand(
                Card.fromString("AP"),
                Card.fromString("AC"),
                Card.fromString("4K"),
                Card.fromString("RC"),
                Card.fromString("2P"));

        Hand handB = new Hand(
                Card.fromString("AP"),
                Card.fromString("AC"),
                Card.fromString("4K"),
                Card.fromString("VC"),
                Card.fromString("2P"));

        Dealer dealerResult = Dealer.betterPair(handA, handB);


        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handA, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }


    // Q6
    @Test
    public void sameTwoPairVsOnePair() {
        Output.PrintStartMessageTest();
        Hand handA = new Hand(
                Card.fromString("AP"),
                Card.fromString("AC"),
                Card.fromString("4K"),
                Card.fromString("4C"),
                Card.fromString("2P"));

        Hand handB = new Hand(
                Card.fromString("AP"),
                Card.fromString("AC"),
                Card.fromString("4K"),
                Card.fromString("5C"),
                Card.fromString("2P"));

        Dealer dealerResult = Dealer.betterTwoPair(handA, handB);


        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handA, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }

    // Q7
    @Test
    public void sameStrongestPairCompareSecondPair() {
        Output.PrintStartMessageTest();
        Hand handA = new Hand(
                Card.fromString("6P"),
                Card.fromString("6C"),
                Card.fromString("AK"),
                Card.fromString("AC"),
                Card.fromString("4P"));

        Hand handB = new Hand(
                Card.fromString("VP"),
                Card.fromString("VC"),
                Card.fromString("AK"),
                Card.fromString("AC"),
                Card.fromString("3P"));

        Dealer dealerResult = Dealer.betterTwoPair(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handB, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }


    // Q8
    @Test
    public void twoSamePairButDifferentLastCard() {
        Output.PrintStartMessageTest();
        Hand handA = new Hand(
                Card.fromString("3P"),
                Card.fromString("3C"),
                Card.fromString("AK"),
                Card.fromString("AC"),
                Card.fromString("4P"));

        Hand handB = new Hand(
                Card.fromString("3P"),
                Card.fromString("3C"),
                Card.fromString("AK"),
                Card.fromString("AC"),
                Card.fromString("RP"));

        Dealer dealerResult = Dealer.betterTwoPair(handA, handB);


        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handB, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }

    // Q9 : Brelan
    @Test
    public void threeOfKind_Simple() {

        Output.PrintStartMessageTest();

        //given
        Hand handA = new Hand(
                Card.fromString("2P"),
                Card.fromString("3C"),
                Card.fromString("RK"),
                Card.fromString("RC"),
                Card.fromString("RP"));

        Hand handB = new Hand(
                Card.fromString("5K"),
                Card.fromString("2T"),
                Card.fromString("8K"),
                Card.fromString("9C"),
                Card.fromString("XP"));

        //when
        Dealer dealerResult = Dealer.ThreeOfKind(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handA, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }

    // Q10 : Brelan draw
    @Test
    public void twoHandHaveThreeOfKind() {

        Output.PrintStartMessageTest();

        //given
        Hand handA = new Hand(
                Card.fromString("9T"),
                Card.fromString("5K"),
                Card.fromString("DK"),
                Card.fromString("DC"),
                Card.fromString("DP"));

        Hand handB = new Hand(
                Card.fromString("2P"),
                Card.fromString("4P"),
                Card.fromString("RK"),
                Card.fromString("RC"),
                Card.fromString("RP"));

        //when
        Dealer dealerResult = Dealer.ThreeOfKind(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handB, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }

    // Brelan perfect draw
    @Test
    public void threeOfKind_PerfectDraw() {

        Output.PrintStartMessageTest();

        //given
        Hand handA = new Hand(
                Card.fromString("9T"),
                Card.fromString("5K"),
                Card.fromString("RK"),
                Card.fromString("RC"),
                Card.fromString("RP"));

        Hand handB = new Hand(
                Card.fromString("9P"),
                Card.fromString("5P"),
                Card.fromString("RK"),
                Card.fromString("RC"),
                Card.fromString("RP"));

        //when
        Dealer dealerResult = Dealer.ThreeOfKind(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 2);
        Output.PrintEndMessageTest(dealerResult);
    }

    // Q11 : Brelan draw latest card
    @Test
    public void threeOfKindDraw_LatestCard() {

        Output.PrintStartMessageTest();

        //given
        Hand handA = new Hand(
                Card.fromString("5T"),
                Card.fromString("4K"),
                Card.fromString("RK"),
                Card.fromString("RC"),
                Card.fromString("RP"));

        Hand handB = new Hand(
                Card.fromString("3P"),
                Card.fromString("4P"),
                Card.fromString("RK"),
                Card.fromString("RC"),
                Card.fromString("RP"));

        //when
        Dealer dealerResult = Dealer.ThreeOfKind(handA, handB);

        // assert
        assertEquals(dealerResult.winners.length, 1);
        assertEquals(handA, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }
}
