package lanshan.imczfy.lanshancommunity.events;

import lanshan.imczfy.lanshancommunity.LanShanCommunity;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerDisconnectEvent e) {
        if (LanShanCommunity.channel.containsKey(e.getPlayer())) {
            LanShanCommunity.channel.remove(e.getPlayer());
        }
        if (LanShanCommunity.sd.containsKey(e.getPlayer())) {
            LanShanCommunity.sd.remove(e.getPlayer());
        }
    }
}
