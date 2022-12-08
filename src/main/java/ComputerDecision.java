import game_entities.Card;
import game_entities.Deck;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ComputerDecision {
    final private Card[] HAND;
    final private Card[] FLOP;
    final private int CALLAMOUNT;

    /**
     * Creates a ComputerDecision class with the given parameters.
     *
     * @param hand       the two cards a player holds
     * @param flop       upto 5 shared cards on the table
     * @param callAmount the current betting amount
     */
    public ComputerDecision(Card[] hand, Card[] flop, int callAmount) {
        this.HAND = hand;
        this.FLOP = flop;
        this.CALLAMOUNT = callAmount;
    }


    /**
     * Finds every possible combination of n cards without replacement from the pool of possibleCards
     *
     * @param n             the number of cards in each combination
     * @param possibleCards the cards that can be used to create the combinations
     * @return a List of every unique array of n cards
     */
    private List<Card[]> allCombinations(int n, Card[] possibleCards) {
        // uses recursion, making a recursive call with n = n - 1 for each possible card
        int length = possibleCards.length;
        List<Card[]> combinationsSoFar = new ArrayList<>();

        if (n == 0 || length == 0) {
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
        /**
         * Finds every possible combination of 5 cards an opponent might have given the cards
         * we know about
         * @return a List of every possible hand an opponent might have
         */

        // we start by knowing the 2 cards in our hand and up to 5 cards on the table
        Card[] knownCards = addTwoArrays(this.FLOP, this.HAND);
        Card[] unknownCards = new Card[52 - this.HAND.length - this.FLOP.length];
        int index = 0;
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            Card cardFromDeck = deck.getCard();
            if (!Arrays.asList(knownCards).contains(cardFromDeck)) {
                unknownCards[index] = cardFromDeck;
            }
        }

        // the opponent can make a hand using the 2 cards they hold, and upto 5 cards on the table
        // of those 7 cards we already know the cards in FLOP, so we call allCombinations with the
        // rest of the unknown cards and then we can find every combination the opponent could get
        int numUnknown = 7 - this.FLOP.length;
        List unknownCombinations = allCombinations(numUnknown, unknownCards);
        Iterator<Card[]> it = unknownCombinations.iterator();

        List<Card[]> oppHandsSoFar = new ArrayList<>();

        while (it.hasNext()) {
            Card[] rest = it.next();
            for (int i = 0; i < this.FLOP.length; i++) {
                Card[] first = {this.FLOP[i]};
                oppHandsSoFar.add(addTwoArrays(first, rest));
            }
        }
        return oppHandsSoFar;
    }
    /**
     * Finds the probability given as the relative frequency of the player's hand beating the opponents hand
     *
     * @return the estimated probability of winning the round
     */
    public float chanceOfWinning() {
        List<Card[]> oppHands = opponentsPossibleHands();
        Iterator<Card[]> it = oppHands.iterator();

        // first find the strongest hand for the player
        CombinationChecker best = null;
        boolean FIRST = true;
        for (game_entities.Card[] combo : allCombinations(5, addTwoArrays(HAND, FLOP))) {
            CombinationChecker cc = new CombinationChecker(combo);
            if (FIRST) {
                best = cc;
                FIRST = false;
            }
            if (cc.compareTo(best) > 0) {
                best = cc;
            }
        }

        // then compare that hand to every possible hand the opponent might have
        int winningHands = 0;
        while (it.hasNext()) {
            CombinationChecker cc = new CombinationChecker(it.next());
            if (best.compareTo(cc) > 0) {
                winningHands++;
            }
        }

        return (float) winningHands / (float) oppHands.size();
    }

    /**
     * Adds two arrays together to form a new array
     *
     * @param a1 the first array
     * @param a2 the second array
     * @return array of every element in a1 then a2
     */
    private Card[] addTwoArrays(Card[] a1, Card[] a2) {
        Card[] sum = new Card[a1.length + a2.length];
        for (int i = 0; i < a1.length; i++) {
            sum[i] = a1[i];
        }
        for (int i = 0; i < a2.length; i++) {
            sum[i + a1.length] = a2[i];
        }
        return sum;
    }

    /**
     * Makes a decision: all in, call, check or fold, depending on the chance of winning
     *
     * @return the decision given as a String
     */
    public String makeDecision() {

        if (FLOP.length == 0) {
            if (CALLAMOUNT == 0) {
                return "K";
            } else {
                return "C";
            }
        }

        float chance = chanceOfWinning();

        if (chance > 0.9) {
            return "A";
        } else if (chance > 0.5) {
            return "C";
        } else {
            if (CALLAMOUNT == 0) {
                return "K";
            } else {
                return "F";
            }
        }
    }
}