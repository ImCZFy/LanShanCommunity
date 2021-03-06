package lanshan.imczfy.lanshancommunity.commands.chatsystem;


import lanshan.imczfy.lanshancommunity.LanShanCommunity;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCommand extends Command {

    public StaffChatCommand() {
        super("staffchat", "chat.art", new String[] {"schat", "sc"});
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (!commandSender.hasPermission("chat.art")) {
            commandSender.sendMessage("§c你没有使用该命令的权限！");
            return;
        } else {
            for (ProxiedPlayer p : LanShanCommunity.instance.getProxy().getPlayers()) {
                if (p.hasPermission("chat.staff")) {
                    p.sendMessage(returnMsg(commandSender, getMsg(args)));
                }
            }
        }
    }
    public static String getMsg(String[] args) {
        StringBuilder sb = new StringBuilder();
        int a = 0;
        for (String str : args) {
            if (a == 0) {
                sb.append(str);
            } else {
                sb.append(" ");
                sb.append(str);
            }
            a++;
        }
        return sb.toString().replace("&", "§");
    }
    LuckPerms lp = LuckPermsProvider.get();
    private String returnMsg(CommandSender s, String msg) {
        String p = lp.getUserManager().getUser(s.getName()).getCachedData().getMetaData().getPrefix();
        String sb = lp.getUserManager().getUser(s.getName()).getCachedData().getMetaData().getSuffix();
        String format = ChatColor.YELLOW + "工作人员 §7» " + ChatColor.translateAlternateColorCodes('&', p) + s.getName()+ ChatColor.GRAY + ": " + ChatColor.WHITE + msg;
        return format;
    }
}
