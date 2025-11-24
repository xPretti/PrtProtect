package dev.pretti.prtprotect.configs;


import dev.pretti.prtprotect.PrtProtect;
import dev.pretti.prtprotect.configs.interfaces.IConfig;
import dev.pretti.prtprotect.configs.interfaces.IConfigManager;
import dev.pretti.prtprotect.configs.setups.ConfigSetup;
import dev.pretti.prtprotect.configs.setups.DefaultConfigSetup;
import dev.pretti.prtprotect.configs.types.BlockedConfig;
import dev.pretti.prtprotect.configs.types.MessagesConfig;
import dev.pretti.prtprotect.utils.LogUtils;
import dev.pretti.prtprotect.utils.ResourceUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class ConfigManager implements IConfigManager
{
  private static final String CONFIG_NAME   = "config.yml";
  private static final String MESSAGES_NAME = "messages.yml";

  private final BlockedConfig  blockedConfig;
  private final MessagesConfig messagesConfig = new MessagesConfig();

  /**
   * Contrutor da classe
   */
  public ConfigManager(PrtProtect plugin) {
    this.blockedConfig = new BlockedConfig(plugin.getBlockedManager());
  }

  /**
   * Métodos de carregamentos
   */
  @Override
  public boolean load() {
    createConfigs();
    return loaderConfigs();
  }

  @Override
  public boolean reload() {
    return this.load();
  }

  /**
   * Retornos das configurações
   */
  @NotNull
  public MessagesConfig getMessagesConfig() {
    return messagesConfig;
  }

  /**
   * Métodos de carregamentos privados
   */
  private void createConfigs() {
    ResourceUtils.CreateConfig(CONFIG_NAME);
    ResourceUtils.CreateConfig(MESSAGES_NAME);
  }

  private boolean loaderConfigs() {
    FileConfiguration configuration        = ResourceUtils.getConfig(CONFIG_NAME);
    FileConfiguration configurationDefault = ResourceUtils.getResource(CONFIG_NAME);

    LogUtils.logNormal("");

    int errors = 0;
    errors += loadConfig("blocked..", this.blockedConfig, new DefaultConfigSetup(configuration, configurationDefault, CONFIG_NAME));
    errors += loadConfig("messages..", this.messagesConfig, new DefaultConfigSetup(ResourceUtils.getConfig(MESSAGES_NAME), ResourceUtils.getResource(MESSAGES_NAME), MESSAGES_NAME));
    return (errors <= 0);
  }

  private int loadConfig(String category, IConfig config, ConfigSetup setup) {
    if(category != null) {
      LogUtils.logNormal("&8Loading " + category);
    }
    return config.load(setup) ? 0 : 1;
  }

}
