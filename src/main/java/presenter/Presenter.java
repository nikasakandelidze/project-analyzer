package presenter;

public interface Presenter {
    void showMessage(String message);

    static Presenter create(){
        return new CommandLinePresenterImpl();
    }
}
