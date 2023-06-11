package EnemyC;

import static UtilityIG.Constants.Enemies.WOLF;

import Managers.EnManage;

public class Wolf extends Enemy {

    public Wolf(float x, float y, int ID, EnManage em) {
        super(x, y, ID, WOLF,em);

    }

}