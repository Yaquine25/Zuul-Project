import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import java.net.URL;
/**
 *  UserInterface crée l'interface du jeu.
 *
 * @author Taki
 * @version 2024
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    
    private JButton    aButtonLook;
    private JButton    aButtonEast;
    private JButton    aButtonWest;
    private JButton    aButtonNorth;
    private JButton    aButtonSouth;
    private JButton    aButtonQuit;
    private JButton    aButtonHelp;
    private JButton    aButtonUp;
    private JButton    aButtonDown;
    private JButton    aButtonBack;
    private JButton    aButtonInventory;
    private JPanel     aPanelButton;
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * @param pText String the text.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText String the text.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * @param pImageName String image name.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the entry field.
     * @param pOnOff enable or disable.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 ); // cursor blink
            this.aEntryField.addActionListener( this ); // reacts to entry
        }
        else { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "MAGIC GARDEN" ); // change the title !
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        this.aImage = new JLabel();
        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        
        this.aPanelButton = new JPanel();
        this.aPanelButton.setLayout( new GridLayout(6,2) );
        vPanel.add(this.aPanelButton, BorderLayout.EAST);
        //add a button Look
        this.aButtonLook= new JButton("look");
        this.aButtonLook.addActionListener( this );
        this.aPanelButton.add(this.aButtonLook);
        
        this.aButtonHelp= new JButton("help");
        this.aButtonHelp.addActionListener( this );
        this.aPanelButton.add(this.aButtonHelp);
        
        this.aButtonQuit= new JButton("quit");
        this.aButtonQuit.addActionListener( this );
        this.aPanelButton.add(this.aButtonQuit);
        
        this.aButtonInventory= new JButton("inventory");
        this.aButtonInventory.addActionListener( this );
        this.aPanelButton.add(this.aButtonInventory);
        
        this.aButtonBack= new JButton("back");
        this.aButtonBack.addActionListener( this );
        this.aPanelButton.add(this.aButtonBack);
        
        this.aButtonEast= new JButton("east");
        this.aButtonEast.addActionListener( this );
        this.aPanelButton.add(this.aButtonEast);
        
        this.aButtonWest= new JButton("west");
        this.aButtonWest.addActionListener( this );
        this.aPanelButton.add(this.aButtonWest);
        
        this.aButtonNorth= new JButton("north");
        this.aButtonNorth.addActionListener( this );
        this.aPanelButton.add(this.aButtonNorth);
        
        this.aButtonSouth= new JButton("south");
        this.aButtonSouth.addActionListener( this );
        this.aPanelButton.add(this.aButtonSouth);
    
        this.aButtonUp= new JButton("up");
        this.aButtonUp.addActionListener( this );
        this.aPanelButton.add(this.aButtonUp);
        
        this.aButtonDown= new JButton("down");
        this.aButtonDown.addActionListener( this );
        this.aPanelButton.add(this.aButtonDown);
        
        
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        // to end program when window is closed
        this.aMyFrame.addWindowListener(
            new WindowAdapter() { // anonymous class
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        if(pE.getSource()==this.aButtonLook){
            this.aEngine.interpretCommand("look");
        }
        else if(pE.getSource()==this.aButtonHelp){
            this.aEngine.interpretCommand("help");
        }
        else if(pE.getSource()==this.aButtonQuit){
            this.aEngine.interpretCommand("quit");
        }
        else if(pE.getSource()==this.aButtonInventory){
            this.aEngine.interpretCommand("inventory");
        }
        else if(pE.getSource()==this.aButtonEast){
            this.aEngine.interpretCommand("go east");
        }
        else if(pE.getSource()==this.aButtonWest){
            this.aEngine.interpretCommand("go west");
        }
        else if(pE.getSource()==this.aButtonNorth){
            this.aEngine.interpretCommand("go north");
        }
        else if(pE.getSource()==this.aButtonSouth){
            this.aEngine.interpretCommand("go south");
        }
        else if(pE.getSource()==this.aButtonUp){
            this.aEngine.interpretCommand("go up");
        }
        else if(pE.getSource()==this.aButtonBack){
            this.aEngine.interpretCommand("back");
        }
        else if(pE.getSource()==this.aButtonDown){
            this.aEngine.interpretCommand("go down");
        }
        else{
        // because there is only one possible action (text input) :
        this.processCommand();
        }// never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered in the entry field.  
     * Read the command and do whatever is necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 

