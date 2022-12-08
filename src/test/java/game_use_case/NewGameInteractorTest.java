package game_use_case;

import game_entities.GameFactory;
import game_entities.GameFactoryInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NewGameInteractorTest {
    /**
     * Test case for creation of a new game with 4 players
     */
    @Test
    void player1Check() {
        NewGamePresenter presenter = new NewGamePresenter() {
            @Override
            public ResponseModel prepareSuccessView(ResponseModel outputData) {
                // Test for correct balance
                assertEquals(100, outputData.getPlayerBalance()[0]);
                assertEquals(100, outputData.getPlayerBalance()[1]);
                assertEquals(100, outputData.getPlayerBalance()[2]);
                assertEquals(100, outputData.getPlayerBalance()[3]);
                // Test that the deck is correct
                assertEquals(44, outputData.getDeck().length);
                // Test that table cards are correct
                assertEquals(5, outputData.getTableCard().length);
                assertNull(outputData.getTableCard()[0]);
                assertNull(outputData.getTableCard()[1]);
                assertNull(outputData.getTableCard()[2]);
                assertNull(outputData.getTableCard()[3]);
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
        NewGameInputBoundary interactor = new NewGameInteractor(presenter, gameFactory);

        int NUMBER_OF_PLAYERS = 4;
        String[] card1 = new String[NUMBER_OF_PLAYERS];
        String[] card2 = new String[NUMBER_OF_PLAYERS];
        int[] playerBalance = new int[NUMBER_OF_PLAYERS];
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            card1[i] = "SA";
            card2[i] = "SA";
            playerBalance[i] = 100;
        }
        RequestModel requestModel = new RequestModel(0, 0, 0, playerBalance,
                card1, card2, new String[]{"SA", "SA", "SA", "SA", "SA"},
                new String[NUMBER_OF_PLAYERS], new String[NUMBER_OF_PLAYERS], new String[5], 0,
                new boolean[NUMBER_OF_PLAYERS], new int[NUMBER_OF_PLAYERS], new String[]{"SA"}, "0", ""
        );
        interactor.create(requestModel);
    }
}
