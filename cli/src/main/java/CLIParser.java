import Commands.*;

/**
 * Class to parse user input via CLI.
 *
 * @author thilo
 * @version 09.04.26
 */
public class CLIParser {

    /**
     * Explicit constructor of class CLIParser.
     */
    public CLIParser() {
    }

    /**
     * Parses incoming line and creates a respective command.
     *
     * @param line incoming input line
     * @return corresponding command to input line
     */
    public Executable parse(String line) {

        String[] parts = line.split(" ", 3);
        if (parts.length < 1) {
            throw new IllegalArgumentException("Invalid command line");
        }

        Executable command;

        switch (parts[0]) {
            case "delete" : {
                if(parts.length < 2) {throw new IllegalArgumentException("Unexpected value: " + line);} else {command = new DeleteCommand(parts[1]);}
            } break;
            case "close" : command = new CloseCommand(); break;
            case "put" : if(parts.length < 3) {throw new IllegalArgumentException("Unexpected value: " + line);} else {command = new PutCommand(parts[1], parts[2]);} break;
            case "get" : if(parts.length < 2) {throw new IllegalArgumentException("Unexpected value: " + line);} else {command = new GetCommand(parts[1]);} break;
            case "help" : command = new HelpCommand(); break;
            default : throw new IllegalArgumentException("Unexpected value: " + line);
        }

        return command;
    }
}
