package lanshan.imczfy.lanshancommunity;

import lanshan.imczfy.lanshancommunity.commands.chatsystem.AdminChatCommand;
import lanshan.imczfy.lanshancommunity.commands.chatsystem.ArtChatCommand;
import lanshan.imczfy.lanshancommunity.commands.chatsystem.ChatMainCommand;
import lanshan.imczfy.lanshancommunity.commands.chatsystem.StaffChatCommand;
import lanshan.imczfy.lanshancommunity.commands.shoutsystem.ForceShoutCommand;
import lanshan.imczfy.lanshancommunity.commands.shoutsystem.ShoutCommand;
import lanshan.imczfy.lanshancommunity.commands.shoutsystem.ShoutTpCommand;
import lanshan.imczfy.lanshancommunity.events.ChatListener;
import lanshan.imczfy.lanshancommunity.events.JoinListener;
import lanshan.imczfy.lanshancommunity.events.LeaveListener;
import lanshan.imczfy.lanshancommunity.objects.Channels;
import lanshan.imczfy.lanshancommunity.storage.FriendData;
import lanshan.imczfy.lanshancommunity.storage.ShoutData;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.List;

public final class LanShanCommunity extends Plugin {

    public static LanShanCommunity instance;
    public static HashMap<ProxiedPlayer, Channels> channel = new HashMap<ProxiedPlayer, Channels>();
    public static HashMap<ProxiedPlayer, ShoutData> sd = new HashMap<ProxiedPlayer, ShoutData>();
    public static HashMap<ProxiedPlayer, FriendData> fd = new HashMap<ProxiedPlayer, FriendData>();
    public static HashMap<ProxiedPlayer, List<ProxiedPlayer>> requests = new HashMap<ProxiedPlayer, List<ProxiedPlayer>>();

    @Override
    public void onEnable() {
        instance = this;
        getProxy().getConsole().sendMessage("§eCawnemaCommunity §a加载成功！");
        getProxy().getPluginManager().registerCommand(this, new ChatMainCommand());
        getProxy().getPluginManager().registerCommand(this, new ArtChatCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
        getProxy().getPluginManager().registerCommand(this, new AdminChatCommand());
        getProxy().getPluginManager().registerCommand(this, new ShoutCommand());
        getProxy().getPluginManager().registerCommand(this, new ForceShoutCommand());
        getProxy().getPluginManager().registerCommand(this, new ShoutTpCommand());
        /*
        getProxy().getPluginManager().registerCommand(this, new FriendMainCommand());
         */
        getProxy().getPluginManager().registerListener(this, new ChatListener());
        getProxy().getPluginManager().registerListener(this, new JoinListener());
        getProxy().getPluginManager().registerListener(this, new LeaveListener());
       /* try {
            ConfigurationUtil.loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        } */
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
