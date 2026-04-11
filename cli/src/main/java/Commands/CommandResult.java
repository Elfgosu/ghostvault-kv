package Commands;

/**
 * Class to define and organize the results of commands.
 *
 * @author thilo
 * @version 11.05.26
 */
public class CommandResult {

    final private CmdResultType type;
    final private String message;

    /**
     * Constructor of class CommandResult.
     *
     * @param type type of result
     * @param message string message of result
     */
    public CommandResult(CmdResultType type, String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * Returns the type of the command result.
     *
     * @return the type of the command result
     */
    public CmdResultType getType() {
        return this.type;
    }

    /**
     * Returns the message of the command result.
     *
     * @return the message of the command result
     */
    public String getMessage() {
        return this.message;
    }

    static CommandResult ok(String message) {
        return new CommandResult(CmdResultType.OK, message);
    }

    static CommandResult delete(String message) {
        return new CommandResult(CmdResultType.DELETE, message);
    }

    static CommandResult error(String message) {
        return new CommandResult(CmdResultType.ERROR, message);
    }

    static CommandResult value(String message) {
        return new CommandResult(CmdResultType.VALUE, message);
    }

    static CommandResult shutdown(String message) {
        return new CommandResult(CmdResultType.SHUTDOWN, message);
    }

    static CommandResult help(String message) {
        return new CommandResult(CmdResultType.HELP, message);
    }
}
