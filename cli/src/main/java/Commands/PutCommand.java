package Commands;

import ghostvaultkv.api.GhostvaultKVStore;

/**
 * Command to put entry in Ghostvault.
 *
 * @author thilo
 * @version 09.04.26
 */
public class PutCommand implements Executable {

    private final String key;
    private final String value;

    /**
     * Constructor of class PutCommand.
     *
     * @param key key for the entry to put
     * @param value value for the entry to put
     */
    public PutCommand(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public CommandResult execute(GhostvaultKVStore store) {

        try {
            store.put(key, value);

            return CommandResult.ok(key + " ...hides in the shade.");
        } catch (IllegalArgumentException e) {
            return CommandResult.error("ERROR: " + e.getMessage());
        }
    }
}
