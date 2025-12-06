package dev.pretti.prtprotect.versions;

import dev.pretti.prtprotect.PrtProtect;
import dev.pretti.prtprotect.versions.utils.ProviderUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class VersionsManager
{
  public void loadListeners() {
    Listener listener = ProviderUtils.getCrafterListener();
    if(listener != null) {
      Bukkit.getPluginManager().registerEvents(listener, PrtProtect.getInstance());
    }
  }
}
