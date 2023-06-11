package EnemyC;

import static UtilityIG.Constants.Enemies.KNIGHT;

import Managers.EnManage;

public class Knight extends Enemy {

    public Knight(float x, float y, int ID, EnManage em) {
        super(x, y, ID, KNIGHT,em);

    }

}