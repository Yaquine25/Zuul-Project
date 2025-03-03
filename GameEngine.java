//import java.util.Stack ;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
/**
 *Classe GameEngine - le moteur du jeu Magic Garden.
 * @author Taki 
 * @version 2024
 */
public class GameEngine
{
    //Initialisation des attributs :
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;
    private int aTemps;
    private Beamer aBeamer;
    /**
     * Le constructeur Game appelle la méthode createRooms() et l'attribut aParser qui permet de lire la commande tapé au clavier.
     */
    public GameEngine(){
        this.createRooms();
        this.aParser= new Parser();
        this.aTemps=0;
    }
    /**
     * setGUI est une procédure qui active l'interface
     * @param pUserInterface est un objet UserInterface 
     c'est une interface qui permet d'intéragir avec le jeu.
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }
    
    /**
     * createRooms est une procédure qui déclare/crée les 11 lieux, positionne les sorties pour créer le "réseau" de lieux
     et initialise le lieu courant. Elle crée aussi les items et les place dans différentes pièces.
     */
    private void createRooms(){
        //déclarer/créer les 11 lieux
        Room vEnchantedGlade=new Room("in the enchanted glade","image/clairiereenchente.jpg");
        Room vLake=new Room("around the lake","image/lac.jpg");
        Room vGreenHouse= new Room("in the greenhouse","image/serre.jpg");
        Room vFlowerGarden = new Room("in the flower garden","image/jardindefleurs.jpg");
        Room vHeavenlyPalace = new Room("in the heavely palace", "image/Palaisceleste.jpg");
        Room vVegetableGarden= new Room("in the vegetable garden","image/Potager.jpg");
        Room vRainbowBridge= new Room("on the rainbowbridge","image/pontarcenciel.jpg");
        Room vDarkCave = new Room("in the dark cave", "image/Grottemalefique.png");
        Room vSparklingWaterfalls = new Room("next to the sparkling waterfalls", "image/cascadescintillante.jpg");
        Room vLabyrinth = new Room("in the labyrinth","image/Labyrinthe.jpg");
        Room vDwarfTavern = new Room("in the dwarf tavern","image/tavernedesnains.jpg");
        //positionner les sorties pour créer le "réseau" de lieux
        vDwarfTavern.setExit("down",vEnchantedGlade);
        //vEnchantedGlade.setExit("up",vDwarfTavern);
        vEnchantedGlade.setExit("east",vSparklingWaterfalls);
        vLake.setExit("north",vVegetableGarden);
        vGreenHouse.setExit("south", vFlowerGarden);
        vGreenHouse.setExit("west",vSparklingWaterfalls);
        vFlowerGarden.setExit("north",vGreenHouse);
        vFlowerGarden.setExit("south",vRainbowBridge);
        vHeavenlyPalace.setExit("north",vSparklingWaterfalls);
        vHeavenlyPalace.setExit("south",vLabyrinth);
        vVegetableGarden.setExit("east", vLabyrinth);
        vVegetableGarden.setExit("south",vLake);
        //vRainbowBridge.setExit("north",vFlowerGarden);
        vRainbowBridge.setExit("south" ,vDarkCave);
        vDarkCave.setExit("north",vRainbowBridge);
        vSparklingWaterfalls.setExit("east", vGreenHouse);
        vSparklingWaterfalls.setExit("south",vHeavenlyPalace);
        vSparklingWaterfalls.setExit("west",vEnchantedGlade);
        vLabyrinth.setExit("north",vHeavenlyPalace);
        vLabyrinth.setExit("west", vVegetableGarden);
        //initialiser le joueur 
        this.aPlayer= new Player("Capucine", vDwarfTavern);
        this.aBeamer= new Beamer("crystal"," With this crystal, you can teleport anywhere by charging it !",3,vEnchantedGlade);
        
        Item vlistedesingredients= new Item("list_of_ingredients","Let's start by looking at the list of ingredients",0);
        Item vpierreprecieuse= new Item("stones","Admire the precious stones buried in the river",7);
        Item vrose= new Item("enchanted_rose","This rose can bewitch anyone",3);
        Item vfougere= new Item("fern","You've finally found the fern leaf ",3);
        Item vmorceauarcenciel= new Item("rainbow_piece","Be careful! The rainbow piece is heavier than it looks!",6);
        Item vharicotmagique= new Item("magic_beans","Wow! The magic beans are delicious!", 5);
        Item vbavedecrapaud= new Item("toad_slime","It's disgusting! You have to harvest toad slime",2);
        
        vLabyrinth.getItemList().addItem("fern",vfougere);
        vDwarfTavern.getItemList().addItem("list_of_ingredients",vlistedesingredients);
        vSparklingWaterfalls.getItemList().addItem("stones",vpierreprecieuse);
        vFlowerGarden.getItemList().addItem("enchanted_rose",vrose);
        vRainbowBridge.getItemList().addItem("rainbow_piece",vmorceauarcenciel);
        vVegetableGarden.getItemList().addItem("magic_beans",vharicotmagique);
        vLake.getItemList().addItem("toad_slime",vbavedecrapaud);
        vEnchantedGlade.getItemList().addItem("crystal", aBeamer);
    
    }
    /**
     * La procédure goRoom permet de controler le changement de pièce.
     * @param pDir est une commande entrée par l'utilisateur.
     */
    public void goRoom(final Command pDir){
        if (!pDir.hasSecondWord()){
            this.aGui.println("Go where ?");
            return;
            }
        
        if(this.aPlayer.getCurrentRoom().getExit(pDir.getSecondWord())==null){
            this.aGui.println("There is no door!");
            return;
        }
        
        else {
            this.aPlayer.goRoom(pDir);
            this.printLocationInfo();
        }
    }
    /**
     * La procédure back permet de revenir dans la pièce précédente.
     * @param pC est la commande entrée par l'utilisateur.
     */
    private void back(final Command pC){
        if (pC.hasSecondWord()){
            this.aGui.println("The back command doesn't accept a second word.");
        }
        else if(this.aPlayer.getPreviousRooms().empty()){
            this.aGui.println("You can't go back");
        }
        else if(!this.aPlayer.back()){
            this.aGui.println("You can't go back because the climbing plant has blocked the passageway");
        }
        else {
            this.printLocationInfo();
            return;
        }
    }
    /**
     * La procédure take permet d'attraper un item.
     * @param pI est la commande entrée par l'utilisateur.
     */
    public void take(final Command pI){
        if (!pI.hasSecondWord()){
            this.aGui.println("Take what ?");
        }
        String vItemName= pI.getSecondWord();
        Item vItem=this.aPlayer.getCurrentRoom().getItemList().getItem(vItemName);
        if(this.aPlayer.takeItem(vItemName)){
            this.aGui.println( "You took the " + vItemName );
            this.aGui.println(vItem.getDescription());
        }
        else{
            this.aGui.println("This item isn't here or you have reached the maximum weight and cannot carry this item.");
        }
    }
    /**
     * La procédure drop permet de déposer un item.
     * @param pI est la commande entrée par l'utilisateur.
     */
    public void drop(final Command pI){
        if (!pI.hasSecondWord()){
            this.aGui.println("Drop what ?");
        }
        String vItemName= pI.getSecondWord();
        if(this.aPlayer.dropItem(vItemName)){
            this.aGui.println( "You drop the " + vItemName );
        }
        else{
            this.aGui.println("You haven't this item");
        }
    }
    /**
     * inventory permet d'afficher les items et le poids actuellement porté par le joueur.
     * @param pI est la commande entrée par l'utilisateur.
     */
    public void inventory(final Command pI){
        this.aGui.println("The items currently carried : "+this.aPlayer.getItemCarried().getItemString()+ ". Total Weight: "+ this.aPlayer.getWeight()+"kg");
    }
    /**
     * charge permet de charger la pièce.
     * @param pC est la commande entrée par l'utilisateur.
     */
    private void charge(final Command pC){
        if (!pC.hasSecondWord()){
            this.aGui.println("Which beamer must be charged?");
            return;
        }
        String vBeamerName= pC.getSecondWord();
        Room vCurrentRoom= this.aPlayer.getCurrentRoom();
        Item vItem= this.aPlayer.getItemCarried().getItem(vBeamerName);
        if(vItem==null){
            this.aGui.println( "You didn't take the beamer !") ;
            return;
        }
        else{
            Beamer vBeamer= (Beamer) vItem;
            vBeamer.charge(this.aPlayer.getCurrentRoom()); 
            this.aGui.println( "You have charged the beamer !");
            return;
        }
    }
    /**
     * fire permet de se téléporter à la pièce chargé.
     * @param pC est la commande entrée par l'utilisateur.
     */
        public void fire(final Command pC){
        if (!pC.hasSecondWord()){
            this.aGui.println( "Which beamer must be fired?");
            return;
        }
        String vBeamerName= pC.getSecondWord();
        Beamer vBeamer= (Beamer) this.aPlayer.getItemCarried().getItem(vBeamerName);
        
        if(vBeamer==null){
            this.aGui.println( "You didn't take the beamer and you didn't charge it!");
            return;
        }
        else if(!vBeamer.isCharged()){
            this.aGui.println( "You didn't charge the beamer!");
            return;
        }
            this.aPlayer.setCurrentRoom(vBeamer.getRoomCharged());
            this.aBeamer.fire();
            this.aPlayer.dropItem(vBeamerName);
            this.printLocationInfo();
            this.aGui.println("Well done! you've saved time !");
            return;
    }
    
    /**
     * printLocationInfo est une procédure qui affiche les sorties disponibles.
     */
    private void printLocationInfo(){
      if (this.aPlayer != null && this.aPlayer.getCurrentRoom() != null) {
       this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        if (this.aPlayer.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
        }
    }
    }
    
    /**
     * procédure qui affiche le message de bienvenue au début du jeu.
     */
    private void printWelcome(){
        this.aGui.print( "\n" );
        this.aGui.println( "Welcome to the Magic Garden!" );
        this.aGui.println( "Many mysterious creatures inhabit the fairy garden." );
        this.aGui.println( "Type 'help' if you need help." );
        this.aGui.print( "\n" );
        this.printLocationInfo();
        }
        
    /**
     * procédure qui affiche le message d'aide.
     */
    private void printHelp(){
        this.aGui.println("You are lost. You are alone.");
        this.aGui.println("You wander in the garden");
        this.aGui.println("Your command words are:");
        this.aGui.println(this.aParser.getCommands());
    }

    /**
     * look est une commande qui permet d'afficher la description du lieu dans lequel on se trouve.
     */
    private void look(){
         this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }
    
    /**
     * eat est une procédure qui permet au joueur de manger.
     * @param pM est la commande entrée par l'utilisateur.
     */
    private void eat(final Command pM){
        if(!pM.hasSecondWord()){
            this.aGui.println("eat what ?");
            return;
        }
        String vItem=pM.getSecondWord();
        if(this.aPlayer.eatBeans(vItem)){
            this.aGui.println("You ate the magic beans! You became very strong so you can carry more items than before !");
        }
        else{
            this.aGui.println("You have eaten now and you are not hungry any more.");
        }
    }
    
    
    /**
     * test est une commande qui exécute successivement toutes les commandes
     lues dans le fichier de texte
     * @param pCoFichier Command qui contient deux mot : test et le nom du fichier.
     */
    private void test(final Command pCoFichier){
        if(!pCoFichier.hasSecondWord()){
            this.aGui.println("What is the file name ?");
            return;
        }
        try{
        File vFile=new File(pCoFichier.getSecondWord()+".txt");
        Scanner vTest = new Scanner(vFile);
        while ( vTest.hasNextLine() ) {
                String vLine = vTest.nextLine();
                interpretCommand(vLine);
              }
        vTest.close();
        }
        catch(final FileNotFoundException pFileException){
            this.aGui.println("An error has occurred");
        }
    }
    
    /**
     * interpretCommand est une fonction qui appelle la bonne 
     méthode en fonction de la commande passée en paramètre.
     * @param pCb Command est la commande entrée par l'utilisateur.
     */
    public void interpretCommand( final String pCb){
        this.aGui.println( "> " + pCb );
        Command vC = this.aParser.getCommand( pCb );
        this.aTemps+=1;
        if(this.aTemps>=45){
            this.aGui.println("Game over!");
            this.endGame();
            return;
        }
        if(vC.isUnknown()){
            this.aGui.println("I don't know what you mean...");
            return;
        }
        String vCommand = vC.getCommandWord();
        if (vCommand.equals("quit")){
                if ( vC.hasSecondWord() ){
                this.aGui.println( "Quit what?" );
                return;
            }
            else{
                this.endGame();
                return;
            }
            }
        else if (vCommand.equals("charge")){
                this.charge(vC);
            }  
        else if (vCommand.equals("fire")){
                this.fire(vC);
            }
        else if (vCommand.equals("help")){
                this.printHelp();
            }
        else if (vCommand.equals("inventory")){
                this.inventory(vC);
            }
        else if (vCommand.equals("take")){
                this.take(vC);
            }
        else if (vCommand.equals("drop")){
                this.drop(vC);
            }
        else if (vCommand.equals("test")){
            this.test(vC);
        }
        else if (vCommand.equals("go")){
                this.goRoom(vC);
            }
         else if (vCommand.equals("back")){
                this.back(vC);
            }
        else if (vC.getCommandWord().equals("look")){
                if (vC.hasSecondWord()){
                    this.aGui.println("I don't know how to look at something in particular yet.");
                }
                else{
                    this.look();
                }
            }
        else if (vCommand.equals("eat")){
            this.eat(vC);
        }
        else{
            this.aGui.println("Erreur du programmeur : commande non reconnue !");
            }
        }
    /**
     * endGame est une procédure qui permet de terminer le jeu.
     */
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }
    
    
}
