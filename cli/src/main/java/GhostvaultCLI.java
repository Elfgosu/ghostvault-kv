import Commands.CmdResultType;
import Commands.CommandResult;
import Commands.Executable;

import java.util.Scanner;

/**
 * Class containing the scan loop interacting with the user.
 *
 * @author thilo
 * @version 09.04.26
 */
public class GhostvaultCLI {

    private final CLIParser cliParser;
    private final CmdExecutor cmdExecutor;

    /**
     * Constructor of class GhostvaultCLI.
     *
     * @param cliParser cli parser object
     * @param cmdExecutor command executor object
     */
    public GhostvaultCLI(CLIParser cliParser, CmdExecutor cmdExecutor) {
        this.cliParser = cliParser;
        this.cmdExecutor = cmdExecutor;

        scanLoop();
    }

    private void scanLoop(){

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("> ");

            String line = scanner.nextLine();

            try{
                Executable cmd = cliParser.parse(line);
                CommandResult result = cmdExecutor.executeCommand(cmd);

                System.out.println(result);

                if  (result.getType() == CmdResultType.SHUTDOWN) {
                    break;
                }
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Come again when the darkness falls.");
        System.exit(1);
    }
}
