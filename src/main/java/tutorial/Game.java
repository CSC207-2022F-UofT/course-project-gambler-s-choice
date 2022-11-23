package tutorial;

import java.util.*;

public class Game {

    private String[] river;

    private ArrayList<Player> players;

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    //debugging method
    public void setRiver(String[] arr) {
        this.river = arr;
    }

    //format to return new int[6] = {1, 1, 2, 3, 4, 5} where p1 and p2 are both first
    public int[] findWinner(Player[] players, String[] flop) {
        ArrayList<Integer> winningPlayers = new ArrayList<>();
        int[] scores = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            scores[i] = calculateHand(players[i].getPlayerHand(), flop);
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

        return rankings;
    }

    /**
     *
     * @param hand1
     * @param hand2
     * @return
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
     * @return the score of the hand as an int from 1 to 130
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


    public static void main(String[] args) {
        Game g = new Game();
        //GameScreen g2 = new GameScreen(1);
        Player[] players = new Player[]{
                new Player(1, new String[]{"H1", "C1"}),
                new Player(2, new String[]{"H2", "C7"}),
                new Player(3, new String[]{"D6", "S8"}),
                new Player(4, new String[]{"C4", "D1"}),
                new Player(5, new String[]{"H1", "C1"})};
        String[] flop = {"S1", "H3", "C3", "D4", "D5"};
//
        int[] results = g.findWinner(players, flop);
        for (int x = 0; x < results.length;x++) {
            System.out.println(results[x]);
        }
     }
}
