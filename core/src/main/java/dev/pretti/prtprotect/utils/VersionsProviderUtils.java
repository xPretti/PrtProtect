package dev.pretti.prtprotect.utils;

import dev.pretti.prtprotect.PrtProtect;
import org.bukkit.Bukkit;

public class VersionsProviderUtils
{
  /**
   * Métodos de retornos de pacotes
   */
  public static String getVersionsPackage() {
    String packageName = PrtProtect.class.getPackage().getName();
    return packageName + ".versions";
  }

  public static String getVersionsPackage(String version) {
    return getVersionsPackage() + "." + version;
  }


  /**
   * Métodos de retornos de classes
   */
  public static <T> T getClassVersion(String version,
                                      String clazzName,
                                      Class<T> type) {
    try {
      Class<?> foundClass = Class.forName(getVersionsPackage(version) + "." + clazzName);
      return type.cast(foundClass.getDeclaredConstructor().newInstance());
    } catch(Exception e) {
      Bukkit.getLogger().severe("Invalid " + type.getSimpleName() + " provider version " + version + ": " + e.getMessage());
      return null;
    }
  }

  public static <T> T getClassVersion(String version,
                                      String clazzName,
                                      Class<T> type,
                                      Object... args) {
    try {
      Class<?> foundClass = Class.forName(getVersionsPackage(version) + "." + clazzName);

      Class<?>[] paramTypes = new Class<?>[args.length];
      for(int i = 0; i < args.length; i++) {
        paramTypes[i] = args[i].getClass();
      }

      return type.cast(
              foundClass.getDeclaredConstructor(paramTypes).newInstance(args)
      );

    } catch(Exception e) {
      Bukkit.getLogger().severe("Invalid " + type.getSimpleName() + " provider version " + version + ": " + e.getMessage());
      return null;
    }
  }
}
