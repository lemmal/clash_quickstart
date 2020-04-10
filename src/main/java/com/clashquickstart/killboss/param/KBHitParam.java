package com.clashquickstart.killboss.param;

public class KBHitParam {
    private long userId;
    private int hp;

    public KBHitParam(long userId, int hp) {
        this.userId = userId;
        this.hp = hp;
    }

    public KBHitParam() {
    }

    public long getUserId() {
        return userId;
    }

    public int getHp() {
        return hp;
    }
}
