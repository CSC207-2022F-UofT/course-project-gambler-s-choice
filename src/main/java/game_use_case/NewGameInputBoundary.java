package game_use_case;

public interface NewGameInputBoundary {
    /**
     * The function will create a new response model given the request model
     * @param input the inputted request model
     * @return a response model with updated values
     */
    ResponseModel create(RequestModel input);
}
