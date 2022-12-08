package game_use_case;

/**
 * Used as an input boundary for clean architecture
 */
public interface LeaveInputBoundary {
    /**
     * The function will create a new output given the request model
     * @param input the inputted request model
     * @return a response model with updated values
     */
    boolean create(RequestModel input);
}
