import java.util.Stack ;
import java.util.HashMap;
/**
 * Player
 * @author Taki
 * @version 2024
 */
public class Player
{
    private String aNamePlayer;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private ItemList aItemCarried;
    private int aWeightMax;
    private int aWeight;
    /**
     * Player est le constructeur de la classe.
     * @param pNamePlayer String le nom du joueur.
     * @param pCurrentRoom Room la première salle où le joueur commence à jouer. 
     */
    public Player(final String pNamePlayer, final Room pCurrentRoom){
       this.aNamePlayer=pNamePlayer;
       this.aCurrentRoom=pCurrentRoom;
       this.aPreviousRooms= new Stack<Room>();
       this.aItemCarried= new ItemList();
       this.aWeightMax=15;
       this.aWeight=0;
    }
    /**
     * getWeight est un accesseur du poids.
     * @return le poids d'un item.
     */
    public int getWeight(){
        return this.aWeight;
    }
    /**
     * getNamePlayer est un accesseur du nom du joueur.
     * @return le nom du joueur.
     */
    public String getNamePlayer(){
        return this.aNamePlayer;
    }
    /**
     * getCurrentRoom est un accesseur de la pièce actuelle.
     * @return la pièce actuelle du joueur.
     */
    public Room getCurrentRoom(){
        return this.aCurrentRoom;
    }
    /**
     * setCurrentRoom est un modificateur de la pièce actuelle.
     * @param pCurrentRoom, la pièce qui remplacera la pièce actuelle du joueur.
     */
    public void setCurrentRoom(final Room pCurrentRoom){
        this.aCurrentRoom= pCurrentRoom;
    }
    
    /**
     * getPreviousRoom est un accesseur des précédentes pièces.
     * @return les pièces précédentes du joueur.
     */
    public Stack<Room> getPreviousRooms(){
        return this.aPreviousRooms;
    }
    
    /**
     * getItemCarried est un accesseur de la liste des items porté par le joueur.
     * @return la liste des items porté par le joueur.
     */
    public ItemList getItemCarried(){
        return this.aItemCarried;
    }
    
    /**
     * La procédure goRoom permet de controler le changement de pièce.
     * @param pDir est une commande entrée par l'utilisateur.
     */
    public void goRoom(final Command pDir){
        Room vNextRoom=this.aCurrentRoom.getExit(pDir.getSecondWord());
        this.aPreviousRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = vNextRoom;
    }
    
    /**
     * La fonction back permet de revenir dans la pièce précédente.
     * @return true ou false.
     */
    public boolean back(){
        if(this.aPreviousRooms.isEmpty()){
            return false;
        }
        if(this.aCurrentRoom.isExit(this.aPreviousRooms.peek())){
            this.aCurrentRoom=this.aPreviousRooms.peek();
            this.aPreviousRooms.pop();
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * takeItem permet de savoir si on peut prendre l'item ou pas.
     * @param pItemName String est le nom de l'item en question.
     * @return true ou false.
     */
    public boolean takeItem(final String pItemName){
        Item vItem= this.aCurrentRoom.getItemList().getItem(pItemName);
        
        if(vItem==null){
            return false;
        }
        if(this.aWeight+vItem.getWeight()<=this.aWeightMax){
            this.aWeight+=vItem.getWeight();
            this.aCurrentRoom.getItemList().removeItem(vItem.getName());
            this.aItemCarried.addItem(vItem.getName(), vItem);
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * DropItem permet de savoir si on peut déposer l'item ou pas.
     * @param pItemName String est le nom de l'item en question.
     * @return true ou false.
     */
    public boolean dropItem(final String pItemName){
        Item vItem= this.aItemCarried.getItem(pItemName);
        
        if(vItem==null){
            return false;
        }
        else{
            this.aWeight-=vItem.getWeight();
            this.aCurrentRoom.getItemList().addItem(vItem.getName(), vItem);
            this.aItemCarried.removeItem(vItem.getName());
            return true;
        }
    }
    
    /**
     * eatBeans permet de savoir si le joueur a mangé "the magic beans".
     * @param pI String est le nom de l'item.
     * @return true ou false.
     */
    public boolean eatBeans(final String pI){
        if(pI.equals("magic_beans")){
            this.aWeight-=this.aItemCarried.getItem(pI).getWeight();
            this.aItemCarried.removeItem("magic_beans");
            this.aWeightMax=this.aWeightMax*2;
            return true;
        }
        else{
            return false;
        }
    }
}
