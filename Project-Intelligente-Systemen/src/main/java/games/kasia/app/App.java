package games.kasia.app;

import games.kasia.app.client.modules.gameManager.GameManager;
/**
 * 
 * App
 * 
 * yes
 */
public class App
{
    /**
     * The main method - entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        GameManager gameManager =  new GameManager();
        gameManager.test();
    }
}