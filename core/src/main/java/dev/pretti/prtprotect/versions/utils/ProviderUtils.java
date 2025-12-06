package dev.pretti.prtprotect.versions.utils;


import dev.pretti.prtprotect.PrtProtect;
import dev.pretti.prtprotect.utils.SystemUtils;
import dev.pretti.prtprotect.utils.VersionsProviderUtils;
import org.bukkit.event.Listener;

public class ProviderUtils
{
  public static Listener getCrafterListener() {
    int version = SystemUtils.getServerVersionInt();
    if(version >= 121) {
      return VersionsProviderUtils.getClassVersion("v1_21", "CrafterListener", Listener.class, PrtProtect.getInstance());
    }
    return null;
  }
}
