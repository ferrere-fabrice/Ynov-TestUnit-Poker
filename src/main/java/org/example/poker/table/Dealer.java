package org.example.poker.table;

import org.example.poker.hand.Hand;

import java.util.*;
import java.util.stream.Collectors;

public class Dealer {
    public Dealer(String message, Hand[] winners) {
        this.message = message;
        this.winners = winners;
    }

    public String message;

    public Hand[] winners;


    public static Map<Integer, Long> GetResultHand(Hand hand) {
        Map<Integer, Long> countHand = hand.getCards().stream().collect(Collectors.groupingBy(e -> (Integer) e.value.score, Collectors.counting()));

/*        System.out.println(countHand.toString());*/
        return countHand;
    }

    public static Dealer CompareStrongestCards(Hand handA, Hand handB) {

        Map<Integer, Long> countHandA = GetResultHand(handA);
        Map<Integer, Long> countHandB = GetResultHand(handB);

        while (countHandA.size() != 0 && countHandB.size() != 0) {


            Integer ScoreMaxHandA =  Collections.max(countHandA.keySet());
            Integer ScoreMaxHandB = Collections.max(countHandB.keySet());

            // draw
            if (ScoreMaxHandA.equals(ScoreMaxHandB)) {
                countHandA.remove(ScoreMaxHandA);
                countHandB.remove(ScoreMaxHandB);
                continue;
            }

            return ScoreMaxHandA > ScoreMaxHandB ? new Dealer("La main A gagne", new Hand[]{ handA }) : new Dealer("La main B gagne", new Hand[]{ handB });
        }
        return new Dealer("Égalité parfait entre la main A et B", new Hand[]{handA, handB});
    }

    public static Dealer betterPair(Hand hand1, Hand hand2) {
        Map<Integer, Long> resultHand1 = GetResultHand(hand1);
        Map<Integer, Long> resultHand2 = GetResultHand(hand2);
        int pair1 = 0;
        int pair2 = 0;
        int highestCard1 = 0;
        int highestCard2 = 0;
        for (Map.Entry<Integer, Long> entry : resultHand1.entrySet()) {
            if (entry.getValue() == 2) {
                if (pair1 < entry.getKey()) {
                    pair1 = entry.getKey();
                }
            }
            if (entry.getValue() == 1 && highestCard1 < entry.getKey()) {
                highestCard1 = entry.getKey();
            }
        }
        for (Map.Entry<Integer, Long> entry : resultHand2.entrySet()) {
            if (entry.getValue() == 2) {
                if (pair2 < entry.getKey()) {
                    pair2 = entry.getKey();
                }
            }
            if (entry.getValue() == 1 && highestCard2 < entry.getKey()) {
                highestCard2 = entry.getKey();
            }
        }

        // REFRACTOR
        if (pair1 > pair2) {
            return new Dealer("La main A gagne", new Hand[]{ hand1 });
        } else if (pair1 < pair2) {
            return new Dealer("La main B gagne", new Hand[]{ hand2 });
        }  else if (pair1 == pair2) {
            if(highestCard1 > highestCard2) {
                return new Dealer("La main A gagne", new Hand[]{ hand1 });
            }
            return new Dealer("La main B gagne", new Hand[]{ hand2 });
        }
        return new Dealer("Égalité parfait entre la main A et B", new Hand[]{hand1, hand2});
    }

    public static Dealer betterTwoPair(Hand hand1, Hand hand2) {
        Map<Integer, Long> resultHand1 = GetResultHand(hand1);
        Map<Integer, Long> resultHand2 = GetResultHand(hand2);
        ArrayList<Integer> pair1 = new ArrayList<>();
        ArrayList<Integer> pair2 = new ArrayList<>();
        Integer hand1HighestCard = 0;
        Integer hand2HighestCard = 0;
        for (Map.Entry<Integer, Long> entry : resultHand1.entrySet()) {
            if (entry.getValue() == 2) {
                pair1.add(entry.getKey());
            }
            if (entry.getValue() == 1 & hand1HighestCard <= entry.getKey()) {
                hand1HighestCard = entry.getKey();
            }
        }
        for (Map.Entry<Integer, Long> entry : resultHand2.entrySet()) {
            if (entry.getValue() == 2) {
                pair2.add(entry.getKey());
            }
            if (entry.getValue() == 1 & hand2HighestCard <= entry.getKey()) {
                hand2HighestCard = entry.getKey();
            }
        }

        System.out.println(pair1);
        System.out.println(pair2);

        Collections.sort(pair1, Collections.reverseOrder());
        Collections.sort(pair2, Collections.reverseOrder());
        if (pair1.size() == 2 & pair2.size() == 2) {
            if (pair1.get(0) > pair2.get(0)) {
                return new Dealer("La main A gagne", new Hand[]{ hand1 });
            }
            if (Collections.max(pair1) < Collections.max(pair2)) {
                return new Dealer("La main B gagne", new Hand[]{ hand2 });
            }
            if (pair1.get(1) > pair2.get(1)) {
                return new Dealer("La main A gagne", new Hand[]{ hand1 });
            }
            if (pair1.get(1) < pair2.get(1)) {
                return new Dealer("La main B gagne", new Hand[]{ hand2 });
            }
            if (hand1HighestCard > hand2HighestCard) {
                return new Dealer("La main A gagne", new Hand[]{ hand1 });
            }
            if (hand1HighestCard < hand2HighestCard) {
                return new Dealer("La main B gagne", new Hand[]{ hand2 });
            }
        } else if (pair1.size() == 2) {
            return new Dealer("La main A gagne", new Hand[]{ hand1 });
        }
        return new Dealer("Égalité parfait entre la main A et B", new Hand[]{hand1, hand2});
    }


    public static Dealer ThreeOfKind(Hand hand1, Hand hand2) {
        Map<Integer, Long> resultHand1 = GetResultHand(hand1);
        Map<Integer, Long> resultHand2 = GetResultHand(hand2);


        // sécurisé le code s'il n'y a pas de brelan ? return null et dans ce cas on passe au cas suivant




        return new Dealer("Égalité parfait entre la main A et B", new Hand[]{hand1, hand2});
    }



    @Override
    public String toString() {

        if(Arrays.stream(winners).count() == 1)
            return message + "\n" + winners[0].toString();

        StringBuilder str = new StringBuilder();
        for (Hand winner : winners) {
            str.append("\n" + winner.toString());
        }
        return str.insert(0,message).toString();
    }
}