import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Name: Ron Nguyen
 * Course: CS20S
 * Teacher: Mr. Hardman
 * Lab #3, Program #2
 * Date Last Modified: 11/13/2017
 */
public class PlayField extends World
{
    private boolean startGame = false;
    private static Ball theBall;
    private static Score player1Score;
    private static Score player2Score;
    /**
     * Constructor for objects of class PlayField.
     * 
     */
    public PlayField()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        createBackground();
        initializePlayingField();
    }
    
    private void createBackground()
    {
        getBackground().setColor(Color.BLACK);
        getBackground().fillRect( 0, 0, getWidth(), getHeight() );
        
        getBackground().setColor(Color.GRAY);
        getBackground().fillRect( getWidth() / 2, 0, 2, getHeight() );
        
        Greenfoot.start();
        showText("Press SPACE to start game", 200, 550 );
    }
    
    private void initializePlayingField()
    {
        theBall = new Ball();
        player1Score = new Score(true);
        player2Score = new Score(false);
        
        
        addObject( theBall, getWidth() / 2, getHeight() / 2 );
        addObject( player1Score, 200, 50 );
        addObject( player2Score, 600, 50 );
        addObject( new Paddle(true), 10, getHeight() / 2 );
        addObject( new Paddle(false), getWidth() - 10, getHeight() / 2 );
    }
    
    public void act()
    {
        if(startGame == false)
        {
            checkKeyPress();
            checkWin();
        }
        else
        {
            if(player1Score.getScoreChanged() == true )
            {
                theBall.setRotation(Greenfoot.getRandomNumber(180) + 80);
            }
            else if(player2Score.getScoreChanged() == true )
            {
                theBall.setRotation(Greenfoot.getRandomNumber(180) - 80);
            } 
            //works partially, doesn't serve to the designated side all the time
        }
    }
    
    private void checkKeyPress()
    {
        if(Greenfoot.isKeyDown("space") )
        {
            startGame = true;
            showText("", 200, 550 );
            Greenfoot.delay(10);
            showText("3", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(60);
            showText("2", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(60);
            showText("1", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(60);
            theBall.setRotation(Greenfoot.getRandomNumber(360) - 100);
            theBall.setVelocity(5);
            showText("", getWidth() / 2, getHeight() / 2);
        }
    }
    
    public void reset()
    {
        theBall.setLocation( getWidth() / 2, getHeight() / 2 );
        theBall.setVelocity(0);
        startGame = false;
        showText("Press SPACE to begin round", 200, 550 );
    }
    
    private void checkWin()
    {
        GreenfootImage player1Win = new GreenfootImage( "Player 1 Wins!", 45, Color.BLUE, Color.BLACK );
        GreenfootImage player2Win = new GreenfootImage( "Player 2 Wins!", 45, Color.RED, Color.BLACK );
        
        if( player1Score.getScore() >= 7 )
        {
            removeObjects( getObjects(null) );
            getBackground().setColor(Color.BLACK);
            getBackground().fillRect( 0, 0, getWidth(), getHeight() );
            getBackground().drawImage( player1Win, getWidth() / 2, getHeight() / 2 );
            showText("", 200, 550 );
            
            Greenfoot.stop();
        }
        
        if(player2Score.getScore() >= 7 )
        {
            removeObjects( getObjects(null) );
            getBackground().setColor(Color.BLACK);
            getBackground().fillRect( 0, 0, getWidth(), getHeight() );
            getBackground().drawImage( player2Win, getWidth() / 2, getHeight() / 2 );
            showText("", 200, 550 );
           
            Greenfoot.stop();
        }
    }
    
    public boolean getStarted()
    {
        return startGame;
    }
}
