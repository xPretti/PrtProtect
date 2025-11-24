package dev.pretti.prtprotect.configs.setups;

import dev.pretti.prtprotect.configs.interfaces.IConfigSetup;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ConfigSetup implements IConfigSetup
{
  private final FileConfiguration config;
  private final String            configPath;

  /**
   * Construtor da classe
   */
  public ConfigSetup(@NotNull FileConfiguration config, String configPath) {
    this.config     = config;
    this.configPath = configPath;
  }

  /**
   * Retornos da classe
   */
  @Override
  public @NotNull FileConfiguration getConfig() {
    return this.config;
  }

  @Override
  public String getConfigPath() {
    return this.configPath;
  }

  /**
   * Métodos de retornos padrão
   */
  @Override
  public @Nullable String getString(@NotNull String param) {
    return config.getString(param);
  }

  @Override
  public int getInt(@NotNull String param) {
    return config.getInt(param);
  }

  @Override
  public long getLong(@NotNull String param) {
    return config.getLong(param);
  }

  @Override
  public boolean getBoolean(@NotNull String param) {
    return config.getBoolean(param);
  }

  @Override
  public double getDouble(@NotNull String param) {
    return config.getDouble(param);
  }

  @Override
  public @Nullable List<String> getStringList(@NotNull String param) {
    return config.getStringList(param);
  }

  public List<Map<?,?>> getMapList(String s) {
    return config.getMapList(s);
  }
}
