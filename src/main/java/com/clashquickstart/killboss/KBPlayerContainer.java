package com.clashquickstart.killboss;

import com.clash.IPlayerContainer;
import com.clash.IResult;
import com.clash.bean.BeanConstruct;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@BeanConstruct(IPlayerContainer.class)
public class KBPlayerContainer implements IPlayerContainer<Long> {
    private Map<Long, KBPlayer> id2Player = new HashMap<>();

    @Override
    public int getPlayerNumber() {
        return id2Player.size();
    }

    @Override
    public Collection<KBPlayer> getPlayers() {
        return id2Player.values();
    }

    @Override
    public KBPlayer getPlayer(Long id) {
        return id2Player.get(id);
    }

    @Override
    public IResult join(Long id) {
        KBPlayer player = new KBPlayer(id);
        id2Player.put(id, player);
        return () -> true;
    }

    @Override
    public IResult leave(Long id) {
        id2Player.remove(id);
        return () -> true;
    }

    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
