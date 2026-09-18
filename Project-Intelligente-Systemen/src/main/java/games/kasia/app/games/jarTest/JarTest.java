package games.kasia.app.games.jarTest;

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
        System.out.println("ok");
    }
}
