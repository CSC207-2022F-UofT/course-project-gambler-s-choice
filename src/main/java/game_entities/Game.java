package game_entities;

public class Game {
    private final Player[] players;
    private final Pool pool;
    private final Deck deck;
    private final Card[] cards;

    /**
     * The constructor of a single game
     * @param players a list of all the players playing the game
     */
    public Game(Player[] players){
        this.pool = new Pool(players);
        this.deck = new Deck();
        this.cards = new Card[5];
        this.players = players;
    }


    /**
     * The main body method of the game. In this method, the game deals card, ask for player's choice and deal with
     * the winner.
     */
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
        currentCall = new int[players.length];

        // ask for everyone's decision for once and then do the while loop until the round is finished
        for (int i = 0; i < players.length; i++){
            if (!players[i].getFold()){
                decision = players[i].makeDecision(call, currentCall[i]);
                processDecision(players, i, decision, pool, call, currentCall);
            }
        }
        whileLoop(currentCall, 0, 0);

        if (checkWinner(currentCall) != -1){
            int[] ranking = getRankWhenOtherFolds(currentCall);
            pool.calculateWinnings(ranking);
            return;
        }

        // ==========turn==========
        noUse = deck.getCard();
        cards[3] = deck.getCard();

        // round 2
        currentCall = new int[players.length];

        // ask for everyone's decision for once and then do the while loop until the round is finished
        for (int i = 0; i < players.length; i++){
            if (!players[i].getFold()){
                decision = players[i].makeDecision(call, currentCall[i]);
                processDecision(players, i, decision, pool, call, currentCall);
            }
        }
        whileLoop(currentCall, 0, 0);

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

    /**
     *
     * @param l a list of current betting amount
     *          e.g. [100, -1, 100, -1, -1, 200] means that the 2rd, 4th, 5th player has folded, 1st and 3rd player
     *              bets 100 and the 6th player raises to 200, meaning that this round is not over yet
     * @return a boolean
     */
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

    /**
     * Take turns to ask the players in the list to make decision, until everyone is on the same bet or folds
     * @param currentCall a list of current betting amount
     * @param playerIndex the starting position of the player that are asked to make decision
     * @param call the starting call amount
     */
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

    /**
     * @param lst a list of current betting amount
     * @return a list that has 1 on the winner's index and 2 on other indexes
     */
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

    /**
     * Checks if everyone else exits the game and returns the result
     * @param l a list of current betting amount
     * @return a boolean
     */
    private int checkWinner(int[] l){  // tested!
        // return the winner's index iff there is only 1 non-negative integer in <l>
        // return -1 if there are more than 1 non-zero integers in <l>
        // this method only checks the situation where everyone else folds
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


    /**
     * Decision is verified to be valid in the Player class, so this method directly processes the string
     * Checking in a round is considered as betting $0
     * @param players a list of current betting amount
     * @param index the index of the active player (who is currently making decision)
     * @param d decision
     * @param pl pool
     * @param currentCall a list of current betting amount
     * @param callLst a list of current betting amount
     */
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

    public Player getCurrPlayer(){
        return players[0];
    }

    public Card[] getBoardCards(){
        return cards;
    }
}