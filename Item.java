
/**
 * La classe item permet de de créer des objets dans le jeu.
 * @author Taki
 * @version 2024
 */
public class Item
{
    //initialisation des attributs de l'objet item 
    private String aName;
    private String aDescription;
    private int aWeight;
    /**
     * Item est le constructeur de la classe.
     * @param pName est le nom de l'item.
     * @param pDescription est la description de l'item.
     * @param pWeight est le poids de l'item.
     */
    public Item(final String pName, final String pDescription, final int pWeight){
        this.aName= pName;
        this.aDescription= pDescription;
        this.aWeight= pWeight;
    }
    /**
     * getName est l'accesseur du nom de l'item.
     * @return le nom de l'item.
     */
    public String getName(){
        return this.aName;
    }
    /**
     * getDescription est l'accesseur de la description de l'item.
     * @return la description de l'item.
     */
    public String getDescription(){
        return this.aDescription;
    }
    /**
     * getWeight est l'accesseur du poids de l'item.
     * @return le poids de l'item.
     */
    public int getWeight(){
        return this.aWeight;
    }
    
}
