import java.util.HashMap ; 

/**
 * ItemList 
 *
 * @author Taki
 * @version 2024
 */
public class ItemList
{
    private HashMap <String,Item> aItemList;
    
    /**
     * ItemList est le constructeur par défaut de la classe.
     */
    public ItemList(){
        this.aItemList= new HashMap<String, Item>();
    }
    
    /**
     * getItem est un accesseur des items de la liste.
     * @param pIn String le nom d'un item.
     * @return l'item de la liste.
     */
    public Item getItem(final String pIn){
        return this.aItemList.get(pIn);
    }
    
    /**
     * addItem est une procédure qui permet d'ajouter un item dans la liste.
     * @param pName est le nom de l'item.
     * @param pItem est l'objet item.
     */
    public void addItem(final String pName, final Item pItem){
        this.aItemList.put(pName,pItem);
    }
    /**
     * removeItem est une procédure qui permet de supprimer un item de la liste.
     * @param pName est le nom de l'item.
     */
    public void removeItem(final String pName){
        this.aItemList.remove(pName);
    }
    
    /**
     * getItemString est une fonction qui renvoie les items présent dans la liste.
     * @return un String contenant le ou les item(s) de la liste.
     */
    public String getItemString(){
        if(this.aItemList.isEmpty()){
            return "There are no items here.";
        }
        return String.join(", ",this.aItemList.keySet());
    }
}
