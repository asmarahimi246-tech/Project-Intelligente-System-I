package games.kasia.app.games.jarTest;

import java.util.Scanner;

import games.kasia.app.games.Game;

/**
 * Testing if jars work
 */
public class JarTest extends Game{
    public JarTest() {
        super();
        super.name = "jar-test";
    }

    @Override
    public void run() {
        System.out.println("word niet meer gebruikt want de jar werkt all");
        super.closeInSeconds();
    }

	@Override
	public boolean isOnline() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isOnline'");
	}
}
