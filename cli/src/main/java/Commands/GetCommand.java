package Commands;

import ghostvaultkv.api.GhostvaultKVStore;

/**
 * Command to get entry from Ghostvault.
 *
 * @author thilo
 * @version 09.04.26
 */
public class GetCommand implements Executable {

    private final String key;

    /**
     * Constructor of class GetCommand.
     *
     * @param key key to get value for
     */
    public GetCommand(String key) {

        this.key = key;
    }

    @Override
    public CommandResult execute(GhostvaultKVStore store) {

        try {
            return CommandResult.value(store.get(key));
        } catch (IllegalArgumentException e) {
            return CommandResult.error("ERROR: " + e.getMessage());
        }
    }
}
