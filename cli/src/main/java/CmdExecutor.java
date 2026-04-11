import Commands.CommandResult;
import Commands.Executable;
import ghostvaultkv.api.GhostvaultKVStore;

/**
 * Class to execute Ghostvault store commands.
 *
 * @author thilo
 * @version 09.04.26
 */
public class CmdExecutor {

    private final GhostvaultKVStore kvStore;

    /**
     * Constructor of class CmdExecutor.
     *
     * @param store object of store implementation
     */
    public CmdExecutor(GhostvaultKVStore store) {

        this.kvStore = store;
    }

    /**
     * Executes a given command to interact with the store.
     *
     * @param command command to execute
     */
    public CommandResult executeCommand(Executable command) {

        return command.execute(kvStore);
    }
}
