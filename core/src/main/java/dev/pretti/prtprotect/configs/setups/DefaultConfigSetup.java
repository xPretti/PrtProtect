package dev.pretti.prtprotect.configs.setups;

import dev.pretti.prtprotect.utils.FileConfigurationUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class DefaultConfigSetup extends ConfigSetup
{
  private final FileConfiguration defaultConfig;

  /**
   * Construtor da classe
   */
  public DefaultConfigSetup(FileConfiguration config, FileConfiguration defaultConfig, String configPath) {
    super(config, configPath);
    this.defaultConfig = defaultConfig;
  }

  /**
   * Retornos da classe
   */
  public FileConfiguration getDefaultConfig() {
    return this.defaultConfig;
  }

  /**
   * Métodos de retornos padrão
   */
  @Override
  public @Nullable String getString(@NotNull String param) {
    return FileConfigurationUtils.getString(getConfig(), defaultConfig, param);
  }

  @Override
  public int getInt(@NotNull String param) {
    return FileConfigurationUtils.getInt(getConfig(), defaultConfig, param);
  }

  @Override
  public long getLong(@NotNull String param) {
    return FileConfigurationUtils.getLong(getConfig(), defaultConfig, param);
  }

  @Override
  public boolean getBoolean(@NotNull String param) {
    return FileConfigurationUtils.getBoolean(getConfig(), defaultConfig, param);
  }

  @Override
  public double getDouble(@NotNull String param) {
    return FileConfigurationUtils.getDouble(getConfig(), defaultConfig, param);
  }

  @Override
  public @Nullable List<String> getStringList(@NotNull String param) {
    return FileConfigurationUtils.getStringList(getConfig(), defaultConfig, param);
  }

  @Override
  public List<Map<?,?>> getMapList(String s) {
    return FileConfigurationUtils.getMapList(getConfig(), defaultConfig, s);
  }
}
