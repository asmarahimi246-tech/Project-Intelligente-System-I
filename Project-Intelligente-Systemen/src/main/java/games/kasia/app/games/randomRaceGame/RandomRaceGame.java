package games.kasia.app.games.randomRaceGame;
import games.kasia.app.games.Game;

/**
 * Used to test gameManager
 */
public class RandomRaceGame extends Game {
    public RandomRaceGame() {
        super();
        super.name = "Mario kart wii";
    }

    @Override
    public void run() {
        String[] frames = {
            "laden 10%\n-",
            "laden 15%\\n-",
            "laden 30%\\n---",
            "laden 50%\\n-----",
            "laden 70%\\n-------",
            "laden 75%\\n-------",
            "laden 80%\\n--------",
            "laden 90%\\n---------",
            "laden 95%\\n---------",
            "laden 99%\\n---------",
            "laden 99%\\n---------",
            "laden 99%\\n---------",
            "laden 99%\\n---------",
            "laden 100%\\n----------"
        };

        for (int i = 0; i < 10; i++) {
            clearScreen();
            System.out.println("Mario Kart");
            System.out.println(frames[i % frames.length]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // wacht 300ms tussen frames
        }
        clearScreen();
        System.out.println();
        System.out.println("It is a serious crime to copy video game according to \ncopyright law. Please refer to your Nintendo game \ninstruction booklet for farther information.");
        System.out.println();
        System.out.println("           ___");
        System.out.println("          /___\\");
        System.out.println("          (o o)");
        System.out.println("         /  V  \\   <-- Mario");
        System.out.println("        /(  _  )\\");
        System.out.println("          ^^ ^^");
        System.out.println();
        System.out.println("ビデオゲームのコピーは著作権法により重大\nな犯罪となります。詳しくは、お持ちの\n任天堂のゲーム取扱説明書を参照してください。");
    }

    /**
     * Clearscreen
     */
    private static void clearScreen() {
        // ANSI escape code voor clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
