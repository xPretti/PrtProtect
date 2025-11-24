package dev.pretti.prtprotect.configs.types;

import dev.pretti.prtprotect.configs.interfaces.IConfigSetup;
import dev.pretti.prtprotect.configs.interfaces.IMessagesConfig;
import dev.pretti.prtprotect.configs.setups.DefaultConfigSetup;
import dev.pretti.prtprotect.utils.ReplaceUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MessagesConfig implements IMessagesConfig
{
  private List<String> help;
  private String       noPermission;
  private String       reload;
  private String       reloadError;

  private String interactBlockBlocked;
  private String interactEntityBlocked;
  private String interactItemBlocked;
  private String craftBlocked;

  @Override
  public boolean load(IConfigSetup configSetup) {
    if(configSetup instanceof DefaultConfigSetup) {
      DefaultConfigSetup config = (DefaultConfigSetup) configSetup;
      help                  = ReplaceUtils.toColorMessage(config.getStringList("messages.help"));
      noPermission          = ReplaceUtils.toColorMessage(config.getString("messages.no-permission"));
      reload                = ReplaceUtils.toColorMessage(config.getString("messages.reload"));
      reloadError           = ReplaceUtils.toColorMessage(config.getString("messages.reload-error"));
      interactBlockBlocked  = getMessageOrEmpty(config, "messages.interact-block-blocked");
      interactEntityBlocked = getMessageOrEmpty(config, "messages.interact-entity-blocked");
      interactItemBlocked   = getMessageOrEmpty(config, "messages.interact-item-blocked");
      craftBlocked          = getMessageOrEmpty(config, "messages.craft-blocked");
      return true;
    }
    return false;
  }

  private String getMessageOrEmpty(DefaultConfigSetup config, String msg) {
    String value = config.getString(msg);
    if(value == null || value.equalsIgnoreCase("none") || value.isEmpty()) {
      return null;
    }
    return ReplaceUtils.toColorMessage(value);
  }


  @Override
  public List<String> getHelp() {
    return help;
  }

  @Override
  public String getNoPermission() {
    return noPermission;
  }

  @Override
  public String getReload() {
    return this.reload;
  }

  @Override
  public String getReloadError() {
    return this.reloadError;
  }

  @Override
  public @Nullable String getInteractBlockBlocked() {
    return interactBlockBlocked;
  }

  @Override
  public @Nullable String getInteractEntityBlocked() {
    return interactEntityBlocked;
  }

  @Override
  public @Nullable String getInteractItemBlocked() {
    return interactItemBlocked;
  }

  @Override
  public @Nullable String getCraftBlocked() {
    return craftBlocked;
  }
}
