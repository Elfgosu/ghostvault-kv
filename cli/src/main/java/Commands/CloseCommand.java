package Commands;

import ghostvaultkv.api.GhostvaultKVStore;

/**
 * Command to close Ghostvault.
 *
 * @author thilo
 * @version 09.04.26
 */
public class CloseCommand implements Executable {

    /**
     * Explicit constructor of class CloseCommand
     */
    public CloseCommand() {

    }

    @Override
    public CommandResult execute(GhostvaultKVStore store) {

        store.close();

        return CommandResult.shutdown("Ghostvault is closing.");
    }
}
