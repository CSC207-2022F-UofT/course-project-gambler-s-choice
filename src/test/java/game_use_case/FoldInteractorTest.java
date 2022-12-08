package game_use_case;

import game_entities.GameFactory;
import game_entities.GameFactoryInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoldInteractorTest {


    /**
     * test case for when the second player folds
     */
    @Test
    void secondPlayerFold() {
        FoldPresenter foldPresenter = new FoldPresenter() {
            @Override
            public ResponseModel prepareSuccessView(ResponseModel outputData) {
                assertEquals(false, outputData.getIsActive()[1]);
                assertEquals(true, outputData.getIsActive()[0]);
                return null;
            }

            @Override
            public ResponseModel prepareFailView(String error) {
                fail("Unexpected Game Failure in Test Fold Use Case");
                return null;
            }
        };

        GameFactoryInterface gameFactory = new GameFactory();
        FoldInputBoundary foldInteractor = new FoldInteractor(foldPresenter, gameFactory);

        RequestModel input = new RequestModel(
                1, 0, 0, new int[]{100, 100},
                new String[]{"SA", "HA"}, new String[]{"SK", "HK"}, new String[]{"DA", "DK", "DQ", null, null},
                new String[2], new String[2], new String[5], 25, new boolean[]{true, true},
                new int[]{25, 0}, new String[]{"CA", "CK", "CQ", "CJ", "C10"}, "", ""
        );
        foldInteractor.create(input);
    }

    /**
     * Test case for when every other player except the first one folds
     */
    @Test
    void allPlayerFold() {
        FoldPresenter foldPresenter = new FoldPresenter() {
            @Override
            public ResponseModel prepareSuccessView(ResponseModel outputData) {
                assertEquals(false, outputData.getIsActive()[2]);
                assertEquals(false, outputData.getIsActive()[1]);
                assertEquals(true, outputData.getIsActive()[0]);
                return null;
            }

            @Override
            public ResponseModel prepareFailView(String error) {
                fail("Unexpected Game Failure in Test Fold Use Case");
                return null;
            }
        };

        GameFactoryInterface gameFactory = new GameFactory();
        FoldInputBoundary foldInteractor = new FoldInteractor(foldPresenter, gameFactory);

        RequestModel input = new RequestModel(
                2, 0, 0, new int[]{100, 100, 100},
                new String[]{"SA", "HA", "DA"}, new String[]{"SK", "HK", "DK"}, new String[]{"CA", "CK", "DQ", null, null},
                new String[3], new String[3], new String[5], 25, new boolean[]{true, false, true},
                new int[]{25, 0, 0}, new String[]{"CA", "CK", "CQ", "CJ", "C10"}, "", ""
        );
        foldInteractor.create(input);
    }

    /**
     * Test for when the first player tries to fold
     */
    @Test
    void firstPlayerFold() {
        FoldPresenter foldPresenter = new FoldPresenter() {
            @Override
            public ResponseModel prepareSuccessView(ResponseModel outputData) {
                fail("Fold use case failed");
                return null;
            }

            @Override
            public ResponseModel prepareFailView(String error) {
                assertEquals("Don't fold. You should check instead", error);
                return null;
            }
        };

        GameFactoryInterface gameFactory = new GameFactory();
        FoldInputBoundary foldInteractor = new FoldInteractor(foldPresenter, gameFactory);

        RequestModel input = new RequestModel(
                0, 0, 0, new int[]{100, 100},
                new String[]{"SA", "HA"}, new String[]{"SK", "HK"}, new String[]{"DA", "DK", "DQ", null, null},
                new String[2], new String[2], new String[5], 0, new boolean[]{true, true},
                new int[]{0, 0}, new String[]{"CA", "CK", "CQ", "CJ", "C10"}, "", ""
        );
        foldInteractor.create(input);
    }

}
