package dev.pretti.prtprotect.commands;

import dev.pretti.prtprotect.PrtProtect;
import dev.pretti.prtprotect.commands.base.Commands;
import dev.pretti.prtprotect.commands.subcommands.ReloadCommand;
import dev.pretti.prtprotect.configs.types.MessagesConfig;
import dev.pretti.prtprotect.constants.PermissionsConstants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class MainCommand extends Commands implements CommandExecutor, TabCompleter
{
  private final MessagesConfig messagesConfig;

  public MainCommand(PrtProtect plugin) {
    super(null, PermissionsConstants.PERM_COMMAND, true);
    this.messagesConfig = plugin.getConfigManager().getMessagesConfig();

    register(new ReloadCommand(plugin, "reload", PermissionsConstants.PERM_COMMAND_RELOAD));
  }


  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
    if(hasPermission(sender)) {
      if(!run(sender, s, strings)) {
        messagesConfig.getHelp().forEach(sender::sendMessage);
      }
    }
    else {
      sender.sendMessage(messagesConfig.getNoPermission());
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if(args.length == 1) {
      return getCommandsNames(args[0]);
    }
    else if(args.length > 1) {
      return runAutoComplete(sender, args[args.length - 2]);
    }
    return null;
  }
}
