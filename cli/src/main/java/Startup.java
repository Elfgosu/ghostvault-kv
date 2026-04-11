import ghostvaultkv.api.GhostvaultKVStoreImpl;

import java.io.File;

/**
 * Startup class for Ghostvault.
 *
 * @author thilo
 * @version 07.04.26
 */
public class Startup {
    public static void main(String[] args) {

        System.out.println("-- Ghostvault --\nThe night is best for hiding all.");

        File dir = new File("./data");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.println("Failed to create directory!");
                System.exit(1);
            }
            System.out.println("Creating directory: " + dir.getAbsolutePath());
        }

        String path = "./data/ghostvault.log";

        GhostvaultCLI cli = new GhostvaultCLI(new CLIParser(), new CmdExecutor(new GhostvaultKVStoreImpl(path)));
    }
}
