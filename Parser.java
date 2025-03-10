import java.util.StringTokenizer;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 */
public class Parser 
{
    private CommandWords aValidCommands;  // (voir la classe CommandWords) 
    /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();

    } // Parser()
    /**
     * Une fonction qui renvoie la liste des commandes valides.
     * @return la liste des commandes valides
     */
    public String getCommands(){
        return this.aValidCommands.getCommandList();
    }
    /**
     * @param pInputLine String à analyser.
     * @return The next command from the user.
     */
    public Command getCommand(final String pInputLine) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // prends en compte le premier mot
        else
            vWord1 = null;

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // prends en compte le second mot
        else
            vWord2 = null;
        // On vérifie si le mot existe. Si oui, on crée la commande.
        // Sinon, on créé une commande null. (unknown command).
        if ( this.aValidCommands.isCommand( vWord1 ) )
            return new Command( vWord1, vWord2 );
        else
            return new Command( null, vWord2 );
    } // getCommand(.)
} // Parser
