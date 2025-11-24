package dev.pretti.prtprotect.listeners;

import dev.pretti.prtprotect.PrtProtect;
import dev.pretti.prtprotect.configs.interfaces.IMessagesConfig;
import dev.pretti.prtprotect.constants.PermissionsConstants;
import dev.pretti.prtprotect.managers.BlockedManager;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class PlayerListener implements Listener
{
  private final IMessagesConfig messagesConfig;
  private final BlockedManager  blockedManager;

  public PlayerListener(PrtProtect plugin) {
    this.messagesConfig = plugin.getConfigManager().getMessagesConfig();
    this.blockedManager = plugin.getBlockedManager();
  }

  @EventHandler
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    Player player = event.getPlayer();
    if(player.hasPermission(PermissionsConstants.PERM_BYPASS)) {
      return;
    }
    EntityType type = event.getRightClicked().getType();
    if(!blockedManager.isPassEntity(type)) {
      String message = messagesConfig.getInteractEntityBlocked();
      if(message != null) {
        event.getPlayer().sendMessage(message);
      }
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onPlayerInteractMaterial(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if(player.hasPermission(PermissionsConstants.PERM_BYPASS)) {
      return;
    }
    if(event.getAction().equals(RIGHT_CLICK_AIR) || event.getAction().equals(RIGHT_CLICK_BLOCK)) {
      if(event.hasBlock()) {
        Block block = event.getClickedBlock();
        if(block == null) {
          return;
        }
        if(!blockedManager.isPassBlock(block.getType())) {
          String message = messagesConfig.getInteractBlockBlocked();
          if(message != null) {
            event.getPlayer().sendMessage(message);
          }
          event.setCancelled(true);
          return;
        }
      }
      if(event.hasItem()) {
        ItemStack item = event.getItem();
        if(item == null) {
          return;
        }
        if(!blockedManager.isPassItem(item.getType())) {
          String message = messagesConfig.getInteractItemBlocked();
          if(message != null) {
            event.getPlayer().sendMessage(message);
          }
          event.setCancelled(true);
          return;
        }
      }
    }

  }


}
