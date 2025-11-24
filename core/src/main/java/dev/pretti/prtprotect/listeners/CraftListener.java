package dev.pretti.prtprotect.listeners;

import dev.pretti.prtprotect.PrtProtect;
import dev.pretti.prtprotect.configs.interfaces.IMessagesConfig;
import dev.pretti.prtprotect.constants.PermissionsConstants;
import dev.pretti.prtprotect.managers.BlockedManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Recipe;

public class CraftListener implements Listener
{
  private final IMessagesConfig messagesConfig;
  private final BlockedManager  blockedManager;

  public CraftListener(PrtProtect plugin) {
    this.messagesConfig = plugin.getConfigManager().getMessagesConfig();
    this.blockedManager = plugin.getBlockedManager();
  }

  @EventHandler
  public void onCraftPrepare(PrepareItemCraftEvent event) {
    Recipe recipe = event.getRecipe();
    if(recipe == null) {
      return;
    }
    if(!blockedManager.isPassCraft(recipe.getResult().getType())) {
      HumanEntity player = event.getView().getPlayer();
      if(player.hasPermission(PermissionsConstants.PERM_BYPASS)) {
        return;
      }
      event.getInventory().setResult(null);
      if(player instanceof Player) {
        String message = messagesConfig.getCraftBlocked();
        if(message != null) {
          player.sendMessage(message);
        }
      }
    }
  }

}



