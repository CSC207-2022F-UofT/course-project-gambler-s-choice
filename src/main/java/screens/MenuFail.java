package screens;

public class MenuFail extends RuntimeException{

    public MenuFail(String error){
        super(error);
    }
}
