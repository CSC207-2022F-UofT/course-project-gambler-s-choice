package menu_use_case;
/**
 * Used as an input boundary for clean architecture
 */
public interface MenuInputBoundary {
    /**
     * The function will create a new response model given the request model
     * @param menuRequestModel the inputted request model
     * @return a response model with updated values
     */
    MenuResponseModel create(MenuRequestModel menuRequestModel);
}
