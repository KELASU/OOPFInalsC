package Obj;

import java.awt.geom.Point2D;

public class attack {

    private Point2D.Float pos;
    private int id, AttackType, dmg;
    private float xSpeed, ySpeed, rotation;
    private boolean active = true;

    public attack(float x, float y, float xSpeed, float ySpeed, int dmg, float rotation, int id, int AttackType) {
        pos = new Point2D.Float(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.rotation = rotation;
        this.id = id;
        this.AttackType = AttackType;
    }

    public void reuse(int x, int y, float xSpeed, float ySpeed, int dmg, float rotate) {
        pos = new Point2D.Float(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.rotation = rotate;
        active = true;
    }

    public void move() {
        pos.x += xSpeed;
        pos.y += ySpeed;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public int getAttackType() {
        return AttackType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDmg() {
        return dmg;
    }

    public float getRotation() {
        return rotation;
    }

}
