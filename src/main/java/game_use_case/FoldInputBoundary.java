package game_use_case;

/**
 * Represents the input boundary in clean architecture. Will
 */
public interface FoldInputBoundary {
    /**
     * The function will create a new response model given the request model
     * @param input the inputted request model
     * @return a response model with updated values
     */
    ResponseModel create(RequestModel input);
}
