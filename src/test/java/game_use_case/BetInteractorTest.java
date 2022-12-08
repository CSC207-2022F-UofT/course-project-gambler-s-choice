package game_use_case;

import game_entities.GameFactory;
import game_entities.GameFactoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BetInteractorTest {

    /**
     * Test case for a 2 player game when player 1 bets 25 dollars
     */
    @Test
    void player1bet25() {
        BetPresenter presenter = new BetPresenter() {
            @Override
            public ResponseModel prepareSuccessView(ResponseModel outputData) {
                // Test for correct balance
                assertEquals(75, outputData.getPlayerBalance()[0]);
                assertEquals(100, outputData.getPlayerBalance()[1]);
                assertEquals(25, outputData.getPlayerBets()[0]);
                assertEquals(0, outputData.getPlayerBets()[1]);
                // Test for correct user turn and last bet amount
                assertEquals(1, outputData.getCurrentPlayer());
                assertEquals(25, outputData.getCurrentBet());
                return null;
            }

            @Override
            public ResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected");
                return null;
            }
        };

        GameFactoryInterface gameFactory = new GameFactory();
        BetInputBoundary interactor = new BetInteractor(presenter, gameFactory);

        RequestModel input = new RequestModel(
                0, 0, 0, new int[]{100, 100},
                new String[]{"SA", "HA"}, new String[]{"SK", "HK"}, new String[]{"DA", "DK", "DQ", null, null},
                new String[2], new String[2], new String[5], 0, new boolean[]{true, true},
                new int[]{0, 0}, new String[]{"CA", "CK", "CQ", "CJ", "C10"}, "25", ""
        );
        interactor.create(input);
    }

    /**
     * Test case for a 2 player game when player 2 bets 100 dollars
     */
    @Test
    void player2BetAllIn() {
        BetPresenter presenter = new BetPresenter() {
            @Override
            public ResponseModel prepareSuccessView(ResponseModel outputData) {
                // Test for correct balance
                assertEquals(75, outputData.getPlayerBalance()[0]);
                assertEquals(0, outputData.getPlayerBalance()[1]);
                assertEquals(25, outputData.getPlayerBets()[0]);
                assertEquals(100, outputData.getPlayerBets()[1]);
                // Test for correct user turn and last bet amount
                assertEquals(0, outputData.getCurrentPlayer());
                assertEquals(100, outputData.getCurrentBet());
                // Test to see if inactivity is correct
                assertTrue(outputData.getIsActive()[0]);
                assertFalse(outputData.getIsActive()[1]);
                return null;
            }

            @Override
            public ResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected");
                return null;
            }
        };

        GameFactoryInterface gameFactory = new GameFactory();
        BetInputBoundary interactor = new BetInteractor(presenter, gameFactory);

        RequestModel input = new RequestModel(
                1, 0, 0, new int[]{75, 100},
                new String[]{"SA", "HA"}, new String[]{"SK", "HK"}, new String[]{"DA", "DK", "DQ", null, null},
                new String[2], new String[2], new String[5], 0, new boolean[]{true, true},
                new int[]{25, 0}, new String[]{"CA", "CK", "CQ", "CJ", "C10"}, "100", ""
        );
        interactor.create(input);
    }

    /**
     * Test case for if they bet more than they have in balance
     */
    @Test
    void betMoreThanBalance() {
        BetPresenter presenter = new BetPresenter() {
            @Override
            public ResponseModel prepareSuccessView(ResponseModel outputData) {
                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public ResponseModel prepareFailView(String error) {
                assertEquals("Cannot bet more than balance", error);
                return null;
            }
        };

        GameFactoryInterface gameFactory = new GameFactory();
        BetInputBoundary interactor = new BetInteractor(presenter, gameFactory);

        RequestModel input = new RequestModel(
                1, 0, 0, new int[]{100, 100},
                new String[]{"SA", "HA"}, new String[]{"SK", "HK"}, new String[]{"DA", "DK", "DQ", null, null},
                new String[2], new String[2], new String[5], 0, new boolean[]{true, true},
                new int[]{0, 0}, new String[]{"CA", "CK", "CQ", "CJ", "C10"}, "125", ""
        );
        interactor.create(input);
    }
}
