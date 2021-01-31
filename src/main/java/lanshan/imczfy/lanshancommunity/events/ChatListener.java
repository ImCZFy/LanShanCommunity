package lanshan.imczfy.lanshancommunity.events;

import lanshan.imczfy.lanshancommunity.LanShanCommunity;
import lanshan.imczfy.lanshancommunity.objects.Channels;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import top.dsbbs2.bukkitcord.api.IPlayer;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        if (e.isCommand()) {
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        if (LanShanCommunity.channel.containsKey(p)) {

            Channels channel = LanShanCommunity.channel.get(p);
            switch (channel) {
                case Public:
                    return;
                case Art:
                    e.setCancelled(true);
                    BungeeCord.getInstance().getPluginManager().dispatchCommand(p, "artchat " + e.getMessage());
                    //p.chat("/artchat " + e.getMessage());
                    return;
                case Staff:
                    e.setCancelled(true);
                    BungeeCord.getInstance().getPluginManager().dispatchCommand(p, "staffchat " + e.getMessage());
                    //p.chat("/staffchat " + e.getMessage());
                    return;
                case Admin:
                    e.setCancelled(true);
                    BungeeCord.getInstance().getPluginManager().dispatchCommand(p, "adminchat " + e.getMessage());
                    //p.chat("/adminchat " + e.getMessage());
                    break;
            }
        }
    }
}