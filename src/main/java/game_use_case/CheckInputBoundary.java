package game_use_case;

public interface CheckInputBoundary {
    /**
     * The function will create a new response model given the request model
     * @param inputData the inputted request model
     * @return a response model with updated values
     */
    ResponseModel create(RequestModel inputData);
}
