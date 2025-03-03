/**
 * La classe Beamer
 *
 * @author Taki
 * @version 2024
 */
public class Beamer extends Item
{
    private Room aRoomCharged; 
    private boolean aIsCharged;
    /**
     * Beamer est le constructeur de la classe
     * @param pName est le nom de l'item.
     * @param pDescription est la description de l'item.
     * @param pWeight est le poids de l'item.
     * @param pRoom est la pièce où le beamer est posé.
     */
    public Beamer(final String pName, final String pDescription, final int pWeight, final Room pRoom){
        super(pName,pDescription, pWeight);
        this.aRoomCharged= null;
        this.aIsCharged=false;
    }
    /**
     * getRoomCharged est l'accesseur de la pièce chargé.
     * @return aRoomCharged la pièce chargé.
     */
    public Room getRoomCharged(){
        return this.aRoomCharged;
    } 
    /**
     * isCharged est l'accesseur du boolean aIsCharged.
     * @return aIsCharged true ou false.
     */
    public boolean isCharged(){
        return this.aIsCharged;
    } 
    /**
     * charge permet de modifier la pièce chargé et de placer le boolean à true.
     * @param pRoomCharged est la pièce chargé.
     */
    public void charge(final Room pRoomCharged){
        this.aRoomCharged= pRoomCharged;
        this.aIsCharged=true;
    }
    /**
     * fire permet de remettre le boolean à false.
     */
    public void fire(){
        if (this.aIsCharged){
                this.aIsCharged=false;
        }
    }
}