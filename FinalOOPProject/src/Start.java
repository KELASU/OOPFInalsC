import Game_Main.MainGUI;
import Game_Main.MainGame;

public class Start {
    public static void main(String[] args) {
        MainGUI game = new MainGUI();
        game.gameScreen.initInputs();
        game.start();

    }
}