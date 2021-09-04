package service.help;

public class HelpMessageConstants {
    public static String HELP_COUNT_WITH_EXTENSION = "Command: \"find -xc arg?\". (Where \"arg\" is optional. flags: x - for extension, c - for count). If argument is specified finds count of number of files with specified \"arg\" extension, if no arg is specified command will print all extension and according counts for all of them.";
    public static String HELP_LS_WITH_EXTENSION = "Command: \"find -xl arg\". (Where \"arg\" is mandatory. flags: x - for extension, l - for list). Finds and lists all of file names that have extension as one specified in \"arg\" argument.";
    public static String HELP_EXIT = "Command: \"exit\". Exits CLI program.";
    public static String GIT_HISTORY_USERS = "Command: \"git -ccu\". ( flags: cc - for commit count, u - for users). Processes and Outputs statistics and data of users and their commits from using git Version control used for project.";
}
