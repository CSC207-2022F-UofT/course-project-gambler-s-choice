package game_use_case;

import game_entities.GameFactory;
import game_entities.GameFactoryInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckInteractorTest {
    /**
     * Test case for a 2 player game when player 2 checks after player 1 checks
     */
    @Test
    void player2Call() {
        CheckPresenter presenter = new CheckPresenter() {
            @Override
            public ResponseModel prepareSuccessView(ResponseModel outputData) {
                // Test for correct balance
                assertEquals(100, outputData.getPlayerBalance()[0]);
                assertEquals(100, outputData.getPlayerBalance()[1]);
                assertEquals(0, outputData.getPlayerBets()[0]);
                assertEquals(0, outputData.getPlayerBets()[1]);
                // Test for correct user turn and last bet amount
                assertEquals(0, outputData.getCurrentPlayer());
                assertEquals(0, outputData.getCurrentBet());
                // Test if table cards are correct
                assertEquals("DA", outputData.getTableCard()[0]);
                assertEquals("DK", outputData.getTableCard()[1]);
                assertEquals("DQ", outputData.getTableCard()[2]);
                assertEquals("C10", outputData.getTableCard()[3]);
                assertNull(outputData.getTableCard()[4]);
                return null;
            }

            @Override
            public ResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected");
                return null;
            }
        };

        GameFactoryInterface gameFactory = new GameFactory();
        CheckInputBoundary interactor = new CheckInteractor(presenter, gameFactory);

        RequestModel input = new RequestModel(
                1, 0, 0, new int[]{100, 100},
                new String[]{"SA", "HA"}, new String[]{"SK", "HK"}, new String[]{"DA", "DK", "DQ", null, null},
                new String[2], new String[2], new String[5], 0, new boolean[]{true, true},
                new int[]{0, 0}, new String[]{"CA", "CK", "CQ", "CJ", "C10"}, "0", ""
        );
        interactor.create(input);
    }
}
