package EnemyC;

import static UtilityIG.Constants.Enemies.BAT;

import Managers.EnManage;

public class Bat extends Enemy {

    public Bat(float x, float y, int ID, EnManage em) {
        super(x, y, ID, BAT,em);

    }

}
