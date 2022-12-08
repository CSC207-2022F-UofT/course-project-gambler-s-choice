package game_use_case;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeaveInteractorTest {
    /**
     * Test when the user leaves
     */
    @Test
    void checkLeave() {
        LeavePresenter leavePresenter = new LeavePresenter() {
            @Override
            public boolean exitGame(boolean exit) {
                assertEquals(false, exit);
                return false;
            }

            @Override
            public ResponseModel prepareFailView(String error) {
                fail("Game Failure from leave testcase");
                return null;
            }
        };

        LeaveInputBoundary leave  = new LeaveInteractor(leavePresenter);

        RequestModel input = new RequestModel(
                1, 0, 0, new int[]{100, 100},
                new String[]{"SA", "HA"}, new String[]{"SK", "HK"}, new String[]{"DA", "DK", "DQ", null, null},
                new String[2], new String[2], new String[5], 25, new boolean[]{true, true},
                new int[]{25, 0}, new String[]{"CA", "CK", "CQ", "CJ", "C10"}, "", "test"
        );
        leave.create(input);
    }
}
