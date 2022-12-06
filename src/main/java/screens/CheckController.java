package screens;

import game_entities.Player;
import game_use_case.CheckInputBoundary;
import game_use_case.CheckRequestModel;
import game_use_case.CheckResponseModel;

public class CheckController {
    final CheckInputBoundary input;

    public CheckController(CheckInputBoundary input) {
        this.input = input;
    }

    CheckResponseModel create(int player, String move) {
        CheckRequestModel requestModel = new CheckRequestModel(player, move);
        return input.create(requestModel);
    }
}
