package EnemyC;

import static UtilityIG.Constants.Enemies.ORC;

import Managers.EnManage;

public class Orc extends Enemy {

    public Orc(float x, float y, int ID, EnManage em) {
        super(x, y, ID, ORC,em);

    }

}