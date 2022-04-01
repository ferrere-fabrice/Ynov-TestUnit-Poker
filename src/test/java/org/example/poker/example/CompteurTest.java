package org.example.poker.example;

import org.example.poker.card.Card;
import org.example.poker.hand.Hand;
import org.example.poker.table.Dealer;
import org.example.poker.tools.Output;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void TestBetterCard_OneWinner(){
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

        //then
        assertEquals(Arrays.stream(dealerResult.winners).count(), 1);
        assertEquals(dealerResult.winners[0], handB, dealerResult.message);
        System.out.println(dealerResult.toString());
    }

    @Test
    public void TestBetterCard_PerfectDraw(){

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
        assertEquals(Arrays.stream(dealerResult.winners).count(), 2);
        System.out.println(dealerResult.toString());
    }

    @Test
    public void haveBetterTwoPair() {
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
        assertEquals(handA, dealerResult.winners[0]);
        System.out.println(dealerResult.toString());
    }

    // Q3
    @Test
    public void haveBetterPairVsNone() {
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
        assertEquals(handB, dealerResult.winners[0]);
        System.out.println(dealerResult.toString());
    }

    // Q4
    @Test
    public void haveBetterPair() {
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
        assertEquals(handA, dealerResult.winners[0]);
        System.out.println(dealerResult.toString());
    }

    // Q5
    @Test
    public void haveSamePairGetStrongestCard() {
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
        assertEquals(handA, dealerResult.winners[0]);
        System.out.println(dealerResult.toString());
    }



    // Q6
    @Test
    public void sameTwoPairVsOnePair() {
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
        assertEquals(handA, dealerResult.winners[0]);
        System.out.println(dealerResult.toString());
    }

    // Q7
    @Test
    public void sameStrongestPairCompareSecondPair() {

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
        assertEquals(handB, dealerResult.winners[0]);
        System.out.println(dealerResult.toString());
    }


    // Q8
    @Test
    public void twoSamePairButDifferentLastCard() {
        Output.PrintMessage(String.valueOf(Thread.currentThread().getStackTrace()), Output.ANSI_BLUE);
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
        assertEquals(handB, dealerResult.winners[0]);
        Output.PrintEndMessageTest(dealerResult);
    }

}
