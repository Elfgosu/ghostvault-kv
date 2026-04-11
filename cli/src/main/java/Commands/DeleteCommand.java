package Commands;

import ghostvaultkv.api.GhostvaultKVStore;

/**
 * Command to delete entry in Ghostvault.
 *
 * @author thilo
 * @version 09.04.26
 */
public class DeleteCommand implements Executable {

    private final String key;

    /**
     * Constructor of class DeleteCommand.
     */
    public DeleteCommand(String key) {
        this.key = key;
    }

    @Override
    public CommandResult execute(GhostvaultKVStore store) {

        store.delete(this.key);

        return CommandResult.delete(key + " has left the vault.");
    }
}
