package game_entities;

import game_entities.Card;
import game_entities.Deck;

import java.util.*;

public class Game implements GameInterface{
    private final Player[] players;
    private final Pool pool;
    private final Deck deck;
    private final Card[] cards;

    private int currentWager;
    private int currentPlayer;

    private int firstPlayer;

    private int lastBet; //index of the last person who bet

    private boolean[] isActive;

    public Game(Player[] players){
        this.pool = new Pool(players);
        this.deck = new Deck();
        this.cards = new Card[5];
        this.players = players;

        //TODO Delete this
        Card card1 = new Card("10", "S");
        Card card2 = new Card("J", "S");
        Card card3 = new Card("K", "S");
        Card card4 = new Card("A", "S");
        Card card5 = new Card("Q", "S");
        this.cards[0] = card1;
        this.cards[1] = card2;
        this.cards[2] = card3;
//        this.cards[3] = card4;
//        this.cards[4] = card5;
    }

    public Game(Player[] players, int currentPlayer, int firstPlayer, int lastToBet, Card[] tableCard, int currentBet, boolean[] isActive, Pool pool, Deck deck) {
        this.players = players;
        this.firstPlayer = firstPlayer;
        this.currentPlayer = currentPlayer;
        this.lastBet = lastToBet;
        this.cards = tableCard;
        this.currentWager = currentBet;
        this.isActive = isActive;
        this.pool = pool;
        this.deck = deck;
    }

    /**
     * This method will add cards to the table
     */
    public void dealCards() {
        if (this.cards[0] == null) {
            this.cards[0] = this.deck.getCard();
            this.cards[1] = this.deck.getCard();
            this.cards[2] = this.deck.getCard();
        } else if (this.cards[3] == null) {
            this.cards[3] = this.deck.getCard();
        } else if (this.cards[4]== null) {
            this.cards[4] = this.deck.getCard();
        }
    }

    public void nextPlayer() {
        this.iteratePlayer();
        while(!this.isActive[this.currentPlayer]) {
            this.iteratePlayer();
        }
        //you can remove this bit if you want
        if (this.currentPlayer == this.lastBet) {
            this.currentPlayer = -1;
        }

    }

    private void iteratePlayer() {
        this.currentPlayer++;
        this.currentPlayer %= this.players.length;
    }

    public void nextRound() {
//        if (this.firstPlayer <= this.players.length) {
//            this.firstPlayer++;
//        } else {
//            this.firstPlayer = 0;
//        }
        this.currentPlayer = this.firstPlayer; // Set curr player
        this.lastBet = this.currentPlayer;
        this.currentWager = 0;

        this.dealCards();

        if (this.cards[4] != null) {

            Player[] activePlayers = new Player[this.isActive.length];
            for (int i = 0; i < this.isActive.length; i++) {
                if (this.isActive[i]) {
                    activePlayers[i] = this.players[i];
                }
            }

            String[] strCards = new String[5];

            for (int i =0; i < 5; i++) {
                strCards[i] = cards[i].toString();
            }

            this.findWinner(activePlayers, strCards);
        }

    }

    /**
     * This method will take in a list of players and the card flop and will output the list of winning players
     * @param players
     * @param flop
     * @return an array of players with their respective ranking
     */
    public int[] findWinner(Player[] players, String[] flop) {
        int[] scores = new int[players.length];


        String[] hand = new String[2];
        String tempCard = "";
        for (int i = 0; i < players.length; i++) {
            for (int j =0; j < players[i].getCards().length; j++) {
                tempCard = players[i].getCards()[j].toString();
                hand[j] = tempCard.substring(1) + tempCard.charAt(0);
            }
            scores[i] = calculateHand(hand, flop);
        }

        int[] rankings = new int[players.length];
        for (int x =0; x < rankings.length; x++) {
            rankings[x]++;
        }
        for (int i = 0; i < scores.length; i++) {
            for (int j =0; j < scores.length; j++) {
                if (scores[j] > scores[i]) {
                    rankings[i]++;
                }
            }
        }
        //this can pass into the pool class later
        pool.calculateWinnings(rankings);

        return rankings;
    }

    /**
     * This is a wrapper method
     * @param hand1
     * @param hand2
     * @return 0 for a tie, 1 for hand1, 2 for hand2
     */
    public int calcTwoWin(String[] hand1, String[] hand2) {

        if (calculateHand(hand1) > calculateHand(hand2)) {
            return 1;
        } else if (calculateHand(hand1) < calculateHand(hand2)) {
            return 2;
        }
        return 0;
    }

    /**
     * This function will return the score of a hand
     * @param fused array of cards
     * @return the score of the hand as an int from 1 to 130 depending on the kicker of the hand and its highest card
     */
    public int calculateHand(String[] fused) {
        //hand flag
        boolean isStraight = false;
        boolean isFlush = false;
        boolean is3Same = false;
        boolean is2Same = false;

        for (int i = 0; i < fused.length - 1; i++) {
            for (int j = 0; j < fused.length - i - 1; j++) {
                if (fused[j].charAt(1) > fused[j + 1].charAt(1)) {
                    // swap arr[j+1] and arr[j]
                    String temp = fused[j];
                    fused[j] = fused[j + 1];
                    fused[j + 1] = temp;
                }
            }
        }


        Integer[] rankArr = new Integer[7];
        char[] suitArr = new char[7];
        for (int i = 0; i < fused.length; i++) {
            if (fused[i].charAt(1) == 'T') {
                rankArr[i] = 10;
            } else if (fused[i].charAt(1) == 'J') {
                rankArr[i] = 11;
            } else if (fused[i].charAt(1) == 'Q') {
                rankArr[i] = 12;
            } else if (fused[i].charAt(1) == 'K') {
                rankArr[i] = 13;
            } else {
                rankArr[i] = fused[i].charAt(1) - '0';
            }
            suitArr[i] = fused[i].charAt(0);
        }



        //check isStraight
        isStraight = checkStraight(rankArr)[0] == 1;
        int straightMax = checkStraight(rankArr)[1];

        //check isFlush
        ArrayList<Integer> hCards = new ArrayList<>();
        ArrayList<Integer> dCards = new ArrayList<>();
        ArrayList<Integer> cCards = new ArrayList<>();
        ArrayList<Integer> sCards = new ArrayList<>();
        Integer[] flushC = new Integer[0];

        for (int i = 0; i < suitArr.length; i++) {
            if (suitArr[i] == 'H')
                hCards.add(rankArr[i]);
            else if (suitArr[i] == 'D')
                dCards.add(rankArr[i]);
            else if (suitArr[i] == 'C')
                cCards.add(rankArr[i]);
            else
                sCards.add(rankArr[i]);
        }
        if (hCards.size() >= 5) {
            isFlush = true;
            flushC = hCards.toArray(new Integer[0]);
        } else if (dCards.size() >= 5) {
            isFlush = true;
            flushC = dCards.toArray(new Integer[0]);
        } else if (cCards.size() >= 5) {
            isFlush = true;
            flushC = dCards.toArray(new Integer[0]);
        } else if (sCards.size() >= 5) {
            isFlush = true;
            flushC = sCards.toArray(new Integer[0]);
        }

        if (isFlush) {
            if (checkStraight(flushC)[0] == 1) {
                return 117 + (flushC[1] == 1 ? flushC[flushC.length - 1] - 1 : 13);
            }
        }

        //check pair
        HashMap<Integer, Integer> nums = new HashMap<Integer, Integer>();
        for (int i = 0; i < rankArr.length; i++) {
            if (nums.containsKey(rankArr[i])) {
                nums.put(rankArr[i], nums.get(rankArr[i]) + 1);
            } else {
                nums.put(rankArr[i], 1);
            }
        }

        ArrayList<Integer> pairs = new ArrayList<>();
        ArrayList<Integer> trios = new ArrayList<>();

        for (Integer i : nums.keySet()) {
            if (nums.get(i) == 2) {
                is2Same = true;
                pairs.add(i);
            } else if (nums.get(i) == 3) {
                is3Same = true;
                trios.add(i);
            } else if (nums.get(i) == 4) {
                return 91 + (i != 1 ? i - 1 : 13);
            }
        }

        Collections.sort(pairs);
        Collections.sort(trios);

        if (is3Same && is2Same || trios.size() > 1) {
            return 78 + (trios.get(0) != 1 ? trios.get(trios.size() - 1) - 1 : 13); //this is broken, need to change this
        } else if (isFlush) {
            return 65 + (flushC[0] != 1 ? flushC[flushC.length - 1] - 1 : 13);
        } else if (isStraight) {
            return 52 + (straightMax != 1 ? straightMax - 1 : 13);
        } else if (is3Same) {
            return 39 + (trios.get(0) != 1 ? trios.get(trios.size() - 1) - 1 : 13);
        } else if (pairs.size() >= 2) {
            return 26 + (pairs.get(0) != 1 ? pairs.get(pairs.size() - 1) - 1 : 13); //this is also broken
        } else if (is2Same) {
            return 13 + (pairs.get(0) != 1 ? pairs.get(pairs.size() - 1) - 1 : 13);
        }

        return rankArr[rankArr.length - 1];
    }

    /**
     * Helper method for checking if its a straight
     * @param rankArr
     * @return an int array where the first index is if its a straight and the second is the highest value in the straight
     */
    private int[] checkStraight(Integer[] rankArr) {
        int counter = 1;

        Integer[] unique = new HashSet<Integer>(Arrays.asList(rankArr)).toArray(new Integer[0]);

        Arrays.sort(unique);

        if (unique[0] == 1) {
            boolean aceStraight = true;
            int temp = 14 - unique.length;
            for (int i = unique.length - 4; i < unique.length; i++) {

                if (unique[i] != i + temp) {
                    aceStraight = false;
                }
            }

            if (aceStraight) {
                return new int[]{1, 1};
            }
        }
        for (int i = 1; i < unique.length; i++) {
            if (unique[i - 1] == unique[i] - 1) {
                counter++;
            } else {
                if (counter >= 5) {
                    return new int[]{1, unique[i - 2]};
                } else {
                    counter = 1;
                }
            }

        }
        if (counter >= 5) {
            return new int[]{1, unique[rankArr.length - 1]};
        }
        return new int[]{0, 0};
    }

    /**
     * Wrapper method for separate hand array and river array
     * @param hand
     * @param river
     * @return the score of the hand
     */
    public int calculateHand(String[] hand, String[] river) {
        String[] fused = new String[7];
        for (int i = 0; i < 7; i++) {
            if (i < 5) {
                fused[i] = river[i];
            } else {
                fused[i] = hand[i - 5];
            }
        }

        return calculateHand(fused);
    }


    public int getCurrentWager() {
        return this.currentWager;
    }

    @Override
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public int getFirstPlayer() {
        return this.firstPlayer;
    }

    @Override
    public int lastToBet() {
        return this.lastBet;
    }

    @Override
    public boolean[] getActive() {
        return this.isActive;
    }

    public void start(){
        Card noUse;
        // resets at the end of each round
        int[] currentCall = new int[players.length];  // the list of each player's call
        String decision;  // the decision made by current player
        int call;  // current highest bidding

        currentCall[0] = players[0].betSmallBlind();
        call = currentCall[0];
        pool.addMoney(players[0], currentCall[0]);

        currentCall[1] = players[1].betBigBlind(call);
        call = currentCall[1];
        pool.addMoney(players[1], currentCall[1]);

        for (Player p: players){p.receiveCard(deck.getCard());}
        for (Player p: players){p.receiveCard(deck.getCard());}

        //pre-flop round

        whileLoop(currentCall, 2, call);  // start with the third player

        // check winner after pre-flop round and exit game
        if (checkWinner(currentCall) != -1){
            int[] ranking = getRankWhenOtherFolds(currentCall);
            pool.calculateWinnings(ranking);
            return;
        }

        // ==========flop cards==========
        noUse = deck.getCard();
        cards[0] = deck.getCard();
        cards[1] = deck.getCard();
        cards[2] = deck.getCard();

        // round 1
        call = 0;
        currentCall = new int[players.length];

        // ask for everyone's decision for once and then do the while loop until the round is finished
        for (int i = 0; i < players.length; i++){
            if (!players[i].getFold()){
                decision = players[i].makeDecision(call, currentCall[i]);
                processDecision(players, i, decision, pool, call, currentCall);
            }
        }
        whileLoop(currentCall, 0, call);

        if (checkWinner(currentCall) != -1){
            int[] ranking = getRankWhenOtherFolds(currentCall);
            pool.calculateWinnings(ranking);
            return;
        }

        // ==========turn==========
        noUse = deck.getCard();
        cards[3] = deck.getCard();

        // round 2
        call = 0;
        currentCall = new int[players.length];

        // ask for everyone's decision for once and then do the while loop until the round is finished
        for (int i = 0; i < players.length; i++){
            if (!players[i].getFold()){
                decision = players[i].makeDecision(call, currentCall[i]);
                processDecision(players, i, decision, pool, call, currentCall);
            }
        }
        whileLoop(currentCall, 0, call);

        if (checkWinner(currentCall) != -1){
            int[] ranking = getRankWhenOtherFolds(currentCall);
            pool.calculateWinnings(ranking);
            return;
        }


        // river
        noUse = deck.getCard();
        cards[4] = deck.getCard();

        // round 3
        for (int i = 0; i < players.length; i++){
            if (!players[i].getFold()){
                decision = players[i].makeDecision(call, currentCall[i]);
                processDecision(players, i, decision, pool, call, currentCall);
            }
        }
        whileLoop(currentCall, 0, call);

        if (checkWinner(currentCall) != -1){
            int[] ranking = getRankWhenOtherFolds(currentCall);
            pool.calculateWinnings(ranking);
            return;
        }


        // get all the active players in a list and pass into findWinner
        int activePlayerCount = 0;
        for(Player p: players){
            if (!p.getFold())
                activePlayerCount++;
        }
        Player[] activePlayers = new Player[activePlayerCount];
        activePlayerCount = 0;
        for (Player player : players) {
            if (!player.getFold()) {
                activePlayers[activePlayerCount] = player;
                activePlayerCount++;
            }
        }
        int[] ranking = new int[activePlayerCount];
        // TODO: find the rank of <activePlayer> and assign to <ranking>
        pool.calculateWinnings(ranking);
    }

    private boolean roundContinues(int[] l){
        // this will return true iff all the non-zero integers in <l> are the same or -1
        boolean status = true;
        int value = -1;
        for (int j : l) {
            if (j != -1) {
                if (value == -1)  // assign the first non-zero integer in <l> to <value>
                    value = j;

                if (value != j) // change <status> to false if there are other non-zero integer not equal to <value>
                    status = false;

            }
        }
        return status;
    }

    private void whileLoop(int[] currentCall, int playerIndex, int call){
        while (roundContinues(currentCall)){
            String decision = players[playerIndex].makeDecision(call, currentCall[playerIndex]);
            processDecision(players, playerIndex, decision, pool, call, currentCall);
            // renew the player index
            if (playerIndex == players.length - 1){
                playerIndex = 0;
            } else {
                playerIndex++;
            }
        }
    }
    private int[] getRankWhenOtherFolds(int[] lst){
        int[] result = new int[lst.length];
        for (int i = 0; i < lst.length; i++){
            if (lst[i] == -1)
                result[i] = 2;
            else
                result[i] = 1;
        }
        return result;
    }

    // return the winner's index iff there is only 1 non-negative integer in <l>
    // return -1 if there are more than 1 non-zero integers in <l>
    // this method only checks the situation where everyone else folds
    private int checkWinner(int[] l){  // tested!

        int count = 0;
        int index = -1;
        for (int i = 0; i < l.length; i++){
            if (l[i] != -1){
                index = i;
                count++;
            }
        }
        if (count == 1){
            return index;
        } else {
            return -1;
        }
    }

    private void processDecision(Player[] players, int index, String d, Pool pl, int currentCall, int[] callLst){

        if (d.equals("C")) {
            pl.addMoney(players[index], currentCall-callLst[index]);
            callLst[index] = currentCall;
        } else if (d.charAt(0) == 'R') {
            int raisedTo = Integer.parseInt(d.substring(1));
            pl.addMoney(players[index], raisedTo-callLst[index]);
            callLst[index] = raisedTo;
        } else if (d.equals("F")){
            callLst[index] = -1;
        } else if (d.equals("K")){
            callLst[index] = 0;
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    @Override
    public Pool getPool() {
        return this.pool;
    }

    @Override
    public Deck getDeck() {
        return this.deck;
    }

    @Override
    public Card[] getTableCards() {
        return this.cards;
    }

    public Player getCurrPlayerObj(){
        return players[currentPlayer];
    }


    /**
     * Returns the total amount of money bet in the game
     *
     * @return the total amount of money bet in the game
     */
    public int totalBet() {
        return this.pool.totalBets();
    }

    public void changeCard() {
        cards[0] = new Card("5", "D");
    }

    public void check() {
        currentPlayer++;
        currentPlayer %= players.length;
        System.out.println(currentPlayer);
    }

    public int getCurrentPlayerAsInt() {
        return currentPlayer;
    }


}
