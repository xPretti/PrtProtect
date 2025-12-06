package dev.pretti.prtprotect.versions.v1_21;

import dev.pretti.prtprotect.PrtProtect;
import dev.pretti.prtprotect.managers.BlockedManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CrafterCraftEvent;
import org.bukkit.inventory.Recipe;

public class CrafterListener implements Listener
{
  private final BlockedManager blockedManager;

  public CrafterListener(PrtProtect plugin) {
    this.blockedManager = plugin.getBlockedManager();
  }

  @EventHandler
  public void onCrafterCraft(CrafterCraftEvent event) {
    Recipe recipe = event.getRecipe();
    if(!blockedManager.isPassCraft(recipe.getResult().getType())) {
      event.setCancelled(true);
    }
  }
}



