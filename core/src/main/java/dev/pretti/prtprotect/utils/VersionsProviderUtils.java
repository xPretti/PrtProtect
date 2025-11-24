package dev.pretti.prtprotect.utils;

import dev.pretti.prtprotect.PrtProtect;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;

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
  public static <T> T getClassVersion(String version, String clazzName, Class<T> clazz) {
    try {
      Class<?> foundClass = Class.forName(getVersionsPackage(version) + "." + clazzName);
      return clazz.cast(foundClass.getDeclaredConstructor().newInstance());
    } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      Bukkit.getLogger().severe("Invalid " + clazz.getSimpleName() + " provider version " + version + ": " + e.getMessage());
    }
    return null;
  }


}
