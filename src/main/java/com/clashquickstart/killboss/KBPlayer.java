package com.clashquickstart.killboss;

import com.clash.IPlayer;

public class KBPlayer implements IPlayer<Long> {
    private long userId;

    public KBPlayer(long userId) {
        this.userId = userId;
    }

    @Override
    public Long getId() {
        return userId;
    }
}
