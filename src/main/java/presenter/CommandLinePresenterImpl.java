package presenter;

class CommandLinePresenterImpl implements Presenter {
    @Override
    public void showMessage(String message) {
        System.out.println("> " + message);
    }
}
