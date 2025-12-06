package dev.pretti.prtprotect;

import dev.pretti.prtprotect.commands.MainCommand;
import dev.pretti.prtprotect.configs.ConfigManager;
import dev.pretti.prtprotect.listeners.CraftListener;
import dev.pretti.prtprotect.listeners.PlayerListener;
import dev.pretti.prtprotect.managers.BlockedManager;
import dev.pretti.prtprotect.utils.LogUtils;
import dev.pretti.prtprotect.utils.SystemUtils;
import dev.pretti.prtprotect.versions.VersionsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PrtProtect extends JavaPlugin
{
  private static PrtProtect instance;

  private final BlockedManager  blockedManager = new BlockedManager();
  private       ConfigManager   configManager;
  private final VersionsManager versionsManager = new VersionsManager();

  private boolean isInitialized;

  /**
   * Implementações
   */
  @Override
  public void onLoad() {
    loadInstances();
  }

  @Override
  public void onEnable() {
    registerEvents();
    registerCommands();

    load();
  }

  @Override
  public void onDisable() {
    LogUtils.log("");
    LogUtils.logNormal("Finishing...");
    LogUtils.log("");
  }

  /**
   * Inicializadores
   */
  public boolean reload() {
    return load();
  }

  protected boolean load() {
    String initMessage = isInitialized ? "Re-Initializing..." : "Initializing...";
    LogUtils.log("");
    LogUtils.logNormal(initMessage);
    LogUtils.logNormal("Plugin version: §e" + getDescription().getVersion());
    LogUtils.logNormal("Server version: §e" + SystemUtils.getServerVersion());

    int errors = configManager.load() ? 0 : 1;

    boolean integrationsSuccess = loadIntegrations();

    errors += integrationsSuccess ? 0 : 1;
    boolean success = errors == 0;

    if(success) {
      LogUtils.logNormal("");
      LogUtils.logNormal("Everything initialized correctly.");
      LogUtils.log("");
    }
    else {
      LogUtils.logNormal("");
      LogUtils.logError("&4Something went wrong during the initialization process.");
      LogUtils.log("");
      if(!integrationsSuccess) {
        LogUtils.logError("&4Plugin is being turned off, mandatory dependencies are missing.");
        this.getServer().getPluginManager().disablePlugin(this);
      }
    }

    isInitialized = true;

    Bukkit.getScheduler().runTaskLater(this, this::delayedLoad, 20L);

    return success;
  }

  /**
   * Métodos de carregamento atrasado
   */
  public void delayedLoad() {
  }

  /**
   * Métodos de carregamentos da instância
   */
  protected void loadInstances() {
    instance      = this;
    configManager = new ConfigManager(this);
  }

  private boolean loadIntegrations() {
    return true;
  }

  /**
   * Métodos de registros de eventos
   */
  protected void registerEvents() {
    Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
    Bukkit.getPluginManager().registerEvents(new CraftListener(this), this);
    versionsManager.loadListeners();
  }

  /**
   * Métodos de registros de comandos
   */
  protected void registerCommands() {
    getCommand("PrtProtect").setExecutor(new MainCommand(this));
  }

  /**
   * Métodos de retornos
   */
  public static PrtProtect getInstance() {
    return instance;
  }

  public ConfigManager getConfigManager() {
    return configManager;
  }

  public BlockedManager getBlockedManager() {
    return blockedManager;
  }
}
