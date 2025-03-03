/**
 * La classe CommandWords contient les mots de commande acceptés par le jeu.
 * @author Taki 
 * @version 2024
 */

public class CommandWords
{
    private static final String[] aValidCommands={ "quit", "help", "go","look","eat", "back", "test", "take", "drop", "inventory","charge", "fire"};
/**
 * La méthode isCommand permet de vérifier si les
 mots de commandes entrées en paramètre sont valides ou non.
 * @param pMot est un String qui correspond au mot entrée par l'utilisateur.
 * @return la validité du mot de commande.
 */
    public boolean isCommand( final String pMot ){
        for(int i=0; i<aValidCommands.length ; i++){
            if(pMot.equals(aValidCommands[i])){
                return true;
            }
            }
        return false;
        }
/**
 * La fonction renvoie les commandes valides.
 * @return toutes les commandes valides.
*/
    public String getCommandList(){
        String vCommandWord="";
        for(String vCommand: aValidCommands){
            vCommandWord+=vCommand + " ";
        }
        return vCommandWord;
    }
}

