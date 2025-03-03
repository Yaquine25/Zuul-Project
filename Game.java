/**
 * Classe Game - Démarrage du jeu Magic Garden.
 * @author Taki
 * @version 2024
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    /**
     * Créer le jeu et l'interface.
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }
    }   
// Game