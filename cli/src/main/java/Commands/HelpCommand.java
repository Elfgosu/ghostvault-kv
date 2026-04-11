package Commands;

import ghostvaultkv.api.GhostvaultKVStore;

/**
 * Command to show help information for Ghostvault.
 *
 * @author thilo
 * @version 11.04.26
 */
public class HelpCommand implements Executable {

    /**
     * Constructor of class HelpCommand.
     */
    public HelpCommand() {
    }

    @Override
    public CommandResult execute(GhostvaultKVStore store) {

        return CommandResult.help(
                """
                -- Ghostvault Help --
                Available commands:
                put <key> <value>        [Inserts an entry into the vault]
                get <key>                [Retrieves a value for a key from the vault]
                delete <key>             [Deletes an entry from the vault]
                close                    [Closes the vault and shuts down the program]
                help                     [Shows this help page and available commands]
                """
        );
    }
}
