package dev.pretti.prtprotect.commands.subcommands;

import dev.pretti.prtprotect.PrtProtect;
import dev.pretti.prtprotect.commands.base.Command;
import dev.pretti.prtprotect.configs.types.MessagesConfig;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

public class ReloadCommand extends Command
{
  private final PrtProtect     plugin;
  private final MessagesConfig messagesConfig;

  /**
   * Construtor da classe
   */
  public ReloadCommand(PrtProtect plugin, @Nullable String command, @Nullable String permission) {
    super(command, permission);
    this.plugin         = plugin;
    this.messagesConfig = plugin.getConfigManager().getMessagesConfig();
  }

  @Override
  public boolean run(CommandSender sender, String command, String[] args) {
    if(isCommand(command)) {
      if(hasPermission(sender)) {
        boolean success = plugin.reload();
        sender.sendMessage(success ? messagesConfig.getReload() : messagesConfig.getReloadError());
      }
      else {
        sender.sendMessage(messagesConfig.getNoPermission());
      }
      return true;
    }
    return false;
  }
}
