package com.clashquickstart.killboss.param;

public class JoinParam {
    private long userId;

    public JoinParam(long userId) {
        this.userId = userId;
    }

    public JoinParam() {
    }

    public long getUserId() {
        return userId;
    }

}
