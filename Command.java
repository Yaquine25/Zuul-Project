 

/**
 * Classe Command - une commande de mon jeu.
 * @author Taki 
 * @version 2024
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    /**
     * Command est le constructeur de la classe.
     * @param pCommandWord est un String, c'est la commande 
     entrée par l'utilisateur.
     * @param pSecondWord est un String, c'est le second mot entrée
     par l'utilisateur qui permet de nous renseigner
     sur la direction à prendre.
     */
    public Command(final String pCommandWord, final String pSecondWord){
        this.aCommandWord=pCommandWord;
        this.aSecondWord=pSecondWord; 
    }
    /**
     * getCommandWord est un accesseur de l'attribut aCommandWord.
     * @return la commande.
     */
    public String getCommandWord(){
        return this.aCommandWord;
    }
    /**
     * getSecondWord est un accesseur de l'attribut aSecondWord.
     * @return le second mot 
     */
    public String getSecondWord(){
        return this.aSecondWord;
    }
    /**
     * hasSecondWord est une fonction qui retourne s'il y a un second mot
     entrée ou non.
     * @return true ou false.
     */
    public boolean hasSecondWord(){
        return this.aSecondWord!=null;
        
    }
    /**
     * isUnknown est une fonction qui retourne s'il y a un mot de commande 
     entrée ou non.
     * @return true ou false.
     */
    public boolean isUnknown(){
        return this.aCommandWord==null;
    }
} // Command
