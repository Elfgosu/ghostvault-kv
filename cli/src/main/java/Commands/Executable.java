package Commands;

import ghostvaultkv.api.GhostvaultKVStore;

/**
 * Interface to implement command pattern in CLI.
 *
 * @author thilo
 * @version 09.04.26
 */
public interface Executable {
    CommandResult execute(GhostvaultKVStore kvStore);
}
