package service.help;

public interface HelpMessageBuilder {
    HelpMessage getHelpMessage();

    static HelpMessageBuilder create(){
        return new HelpMessageBuilderImpl();
    }
}
