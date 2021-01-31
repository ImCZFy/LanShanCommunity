package lanshan.imczfy.lanshancommunity.commands.chatsystem;

import lanshan.imczfy.lanshancommunity.LanShanCommunity;
import lanshan.imczfy.lanshancommunity.objects.Channels;
import lanshan.imczfy.lanshancommunity.utils.ConfigurationUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;

public class ChatMainCommand extends Command {

    public ChatMainCommand() {
        super("chat");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("§c未知用法！用法: /chat [频道]");
            sb.append("\n");
            sb.append("§c您可用的频道: Public/All (公共)");
            if (commandSender.hasPermission("chat.builder")) {
                sb.append(", Art (美工组, 建筑组频道)");
            }
            if (commandSender.hasPermission("chat.staff")) {
                sb.append(", Staff (工作人员频道)");
            }
            if (commandSender.hasPermission("chat.admin")) {
                sb.append(", Admin (管理组频道)");
            }
            commandSender.sendMessage(sb.toString());
            return;
        }
        if (args.length == 1) {
            if (!(commandSender instanceof ProxiedPlayer)) {
                commandSender.sendMessage("§c后台无法使用聊天系统！");
            } else {
                if (!LanShanCommunity.channel.containsKey((ProxiedPlayer) commandSender)) {
                    LanShanCommunity.channel.put((ProxiedPlayer) commandSender, Channels.Public);
                    return;
                } else {
                    Channels channel = LanShanCommunity.channel.get((ProxiedPlayer) commandSender);
                    switch (args[0]) {
                        case "reload":
                            if (commandSender.hasPermission("chat.reload") || commandSender.hasPermission("chat.*")) {
                                try {
                                    ConfigurationUtil.loadConfig();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                commandSender.sendMessage("§c你没有使用该命令的权限！");
                            }
                        case "all":
                        case "public":
                        case "a":
                            if (channel.equals(Channels.Public)) {
                                commandSender.sendMessage("§c你当前正在公共频道中！");
                                return;
                            } else {
                                LanShanCommunity.channel.put((ProxiedPlayer) commandSender, Channels.Public);
                                commandSender.sendMessage("§a你正处于§6公共§a频道中");
                                return;
                            }
                        case "art":
                        case "builder":
                        case "build":
                        case "b":
                            if (channel.equals(Channels.Art)) {
                                commandSender.sendMessage("§c你当前正在美工频道中！");
                                return;
                            } else {
                                if (commandSender.hasPermission("chat.art")) {
                                    LanShanCommunity.channel.put((ProxiedPlayer) commandSender, Channels.Art);
                                    commandSender.sendMessage("§a你正处于§6美工§a频道中");
                                    return;
                                }

                            }
                        case "staff":
                        case "s":
                            if (channel.equals(Channels.Staff)) {
                                commandSender.sendMessage("§c你当前正在工作人员频道中！");
                                return;
                            } else {
                                if (commandSender.hasPermission("chat.staff")) {
                                    LanShanCommunity.channel.put((ProxiedPlayer) commandSender, Channels.Staff);
                                    commandSender.sendMessage("§a你正处于§6工作人员§a频道中");
                                    return;
                                }
                            }
                        case "admin":
                            if (channel.equals(Channels.Admin)) {
                                commandSender.sendMessage("§c你当前正在管理组频道中！");
                                return;
                            } else {
                                if (commandSender.hasPermission("chat.admin")) {
                                    LanShanCommunity.channel.put((ProxiedPlayer) commandSender, Channels.Admin);
                                    commandSender.sendMessage("§a你正处于§6管理组§a频道中");
                                    return;
                                }
                            }
                    }
                }
            }
        }
    }
}
