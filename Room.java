/**
 * Classe Room - un lieu du jeu Magic Garden.
 *
 * @author Taki
 * @version 2024
 */
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
public class Room
{
    private String aDescription;
    private HashMap <String,Room> aExits;
    private String aImageName;
    private HashMap <String,Item> aItems;
    private ItemList aItemList;
    /**
     * Room est un constructeur naturel
     * @param pDescription String décrivant la pièce
     * @param pImage String qui contient l'image de la pièce
     */
    public Room( final String pDescription , final String pImage) 
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String,Room>();
        this.aItems= new HashMap<String, Item>();
        this.aImageName = pImage;
        this.aItemList= new ItemList();
    }
    /**
     * getDescription est un accesseur de la description.
     * @return la description du lieu.
     */
    public String getDescription(){
        return this.aDescription;
    }
    /**
     * getExit est un accesseur des sorties.
     * @param pDirection est un String qui indique la direction des sorties.
     * @return la sortie.
     */
    public Room getExit(final String pDirection){
        return this.aExits.get(pDirection);
    }
    /**
     * getItemList est un accesseur des listes d'item.
     * @return la liste d'item.
     */
    public ItemList getItemList(){
        return this.aItemList;
    }
    
    /**
     * getExitString est une fonction qui renvoie les sorties d'une pièce.
     * @return un String qui indique les sorties possibles d'une pièce.
     */
    public String getExitString(){
        String vReturnExit="Exits: ";
        Set<String> keys=aExits.keySet();
        for(String aExits : keys){
            vReturnExit+=" " + aExits;
        }
        return vReturnExit;
    }
    /**
     * setExit est une procédure qui définit les directions d'une pièce.
     *@param pDirection est la direction de la sortie
     *@param pSuite est la pièce dans la pDirection 
     */
    public void setExit( final String pDirection, final Room pSuite){
        aExits.put(pDirection,pSuite);
    }
    /**
     * getLongDescription est une fonction qui renvoie
     la description d'une pièce et ses sorties.
     *@return la description d'une pièce et ses sorties.
     */
    public String getLongDescription(){
    return "You are " + this.aDescription + ".\n" + this.getExitString() + ".\n" + "Items in this place: "+ this.aItemList.getItemString();
    }
    
    /**
     * getImageName est une fonction qui permet d'afficher le nom d'une image.
     * @return un String décrivant le nom de l'image de la pièce
     */
    public String getImageName()
    {
        return this.aImageName;
    }
    /**
     * isExit est une fonction qui renvoie vrai ou faux selon que la Room passée en paramètre est une des sorties de la pièce ou pas.
     * @param pR est la pièce.
     * @return true ou false.
     */
    public boolean isExit(final Room pR){
         return this.aExits.containsValue(pR);
    }
}// Room
