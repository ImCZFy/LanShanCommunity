package lanshan.imczfy.lanshancommunity.events;

import lanshan.imczfy.lanshancommunity.LanShanCommunity;
import lanshan.imczfy.lanshancommunity.objects.Channels;
import lanshan.imczfy.lanshancommunity.storage.FriendData;
import lanshan.imczfy.lanshancommunity.storage.ShoutData;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        LanShanCommunity.channel.put(e.getPlayer(), Channels.Public);
        ShoutData sd = new ShoutData("", System.currentTimeMillis() / 1000, "", "");
        LanShanCommunity.sd.put(e.getPlayer(), sd);
        FriendData fd = new FriendData(e.getPlayer(), null);
        LanShanCommunity.fd.put(e.getPlayer(), fd);
        LanShanCommunity.requests.put(e.getPlayer(), null);
    }
}
