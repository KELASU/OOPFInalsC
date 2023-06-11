package Game_Main;

public enum Current_Condition {

    PLAYING, MENU, SETTINGS, EDIT, GAME_OVER;

    public static Current_Condition gameState = MENU;

    public static void SetGameState(Current_Condition state) {
        gameState = state;
    }

}