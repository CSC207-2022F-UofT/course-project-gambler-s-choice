import game_entities.Card;
import game_entities.Deck;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ComputerDecision {
    private Card[] hand;
    private Card[] flop;
    private int callAmount;

    public ComputerDecision(Card[] hand, Card[] flop, int callAmount) {
        this.hand = hand;
        this.flop = flop;
        this.callAmount = callAmount;
    }

    private List<Card[]> allCombinations(int n, Card[] possibleCards) {
        //  every possible combination of n cards out of total
        int length = possibleCards.length;
        List<Card[]> combinationsSoFar = new ArrayList<>();

        if(n == 0 || length == 0) {
            return combinationsSoFar;
        }

        for (int i = 0; i <= length - n; i++) {
            Card[] first = {possibleCards[i]};
            List<Card[]> rest = allCombinations(n - 1,
                    Arrays.copyOfRange(possibleCards, i + 1, length - 1));
            for (int j = 0; j < rest.size(); j++) {
                combinationsSoFar.add(addTwoArrays(first, rest.get(j)));
            }
        }
        return combinationsSoFar;
    }

    private List<Card[]> opponentsPossibleHands() {
        // returns every possible hand that the opponent might have
        Card[] knownCards = addTwoArrays(this.flop, this.hand);
        Card[] unknownCards = new Card[52 - this.hand.length - this.flop.length];
        int index = 0;
        Deck deck = new Deck();
        for(int i = 0; i < 52; i++) {
            Card cardFromDeck = deck.getCard();
            if (!Arrays.asList(knownCards).contains(cardFromDeck)) {
                unknownCards[index] = cardFromDeck;
            }
        }

        int numUnknown = 7 - this.flop.length;
        List unknownCombinations = allCombinations(numUnknown, unknownCards);
        Iterator<Card[]> it = unknownCombinations.iterator();

        List<Card[]> oppHandsSoFar = new ArrayList<Card[]>();

        while(it.hasNext()) {
            Card[] rest = it.next();
            for(int i = 0; i < this.flop.length; i++) {
                Card[] first = {this.flop[i]};
                oppHandsSoFar.add(addTwoArrays(first, rest));
            }
        }
        return oppHandsSoFar;
    }

    public float chanceOfWinning() {
        List<Card[]> oppHands = opponentsPossibleHands();
        Iterator<Card[]> it = oppHands.iterator();

        int winningHands = 0;
        // TODO a method to find the best hand

        while(it.hasNext()) {
            // TODO a method to compare 2 hands
        }

        return winningHands / oppHands.size();
    }

    private Card[] addTwoArrays(Card[] a1, Card[] a2) {
        Card[] sum = new Card[a1.length + a2.length];
        for(int i = 0; i < a1.length; i++) {
            sum[i] = a1[i];
        }
        for(int i = 0; i < a2.length; i++) {
            sum[i + a1.length] = a2[i];
        }
        return sum;
    }

    public String makeDecision() {
        if (flop.length == 0) {
            // TODO
        }

        float chance = chanceOfWinning();

        if (chance > 0.9) {
            return "A";
        } else if (chance > 0.5) {
            return "C";
        } else {
            if (callAmount == 0) {
                return "K";
            } else {
                return "F";
            }
        }
    }
}
