package future_features;

public interface HintInterface {
    String GetHint();
    // The Method is never used is because we are not able to merge every together, this is meant to be
    // call by some gateway or presenter, to provide player a hint for chance about the game.
}
