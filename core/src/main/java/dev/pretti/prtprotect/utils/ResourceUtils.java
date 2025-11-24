package dev.pretti.prtprotect.utils;

import dev.pretti.prtprotect.PrtProtect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ResourceUtils
{
  private static final PrtProtect plugin = PrtProtect.getInstance();

  /**
   * Métodos de retorno da configuração específica
   */
  public static FileConfiguration getConfig(String fileDir) {
    try {
      File file = new File(plugin.getDataFolder() + File.separator + fileDir);
      return YamlConfiguration.loadConfiguration(file);
    } catch(Throwable e) {
      System.err.println("Error returning the config: " + e.getMessage());
    }
    return (null);
  }

  /**
   * Métodos de criação de um arquivo
   */
  public static boolean CreateConfig(String fileDir) {
    if(!ExistConfig(fileDir)) {
      plugin.saveResource(fileDir, false);
      return (true);
    }
    return (false);
  }

  /**
   * Métodos de retorno de uma resource
   */
  public static FileConfiguration getResource(String resource) {
    InputStream inputStream = plugin.getResource(resource);
    if(inputStream != null) {
      try(Reader reader = new InputStreamReader(inputStream)) {
        return (YamlConfiguration.loadConfiguration(reader));
      } catch(Exception e) {
        System.err.println("Error returning the resource: " + e.getMessage());
      }
    }
    return (null);
  }


  /**
   * Retorna se a config existe
   */
  public static boolean ExistConfig(String fileDir) {
    String dir = plugin.getDataFolder() + File.separator + fileDir;
    return (FileUtils.FileExist(dir));
  }
}
