package dev.pretti.prtprotect.configs.types;

import dev.pretti.prtprotect.configs.interfaces.IBlockedConfig;
import dev.pretti.prtprotect.configs.interfaces.IConfigSetup;
import dev.pretti.prtprotect.configs.setups.DefaultConfigSetup;
import dev.pretti.prtprotect.managers.BlockedManager;
import dev.pretti.prtprotect.models.BlockedResult;
import dev.pretti.prtprotect.utils.LogUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class BlockedConfig implements IBlockedConfig
{
  private final BlockedManager blockedManager;

  public BlockedConfig(BlockedManager blockedManager) {
    this.blockedManager = blockedManager;
  }

  @Override
  public boolean load(IConfigSetup configSetup) {
    blockedManager.clear();
    if(configSetup instanceof DefaultConfigSetup) {
      DefaultConfigSetup        config = (DefaultConfigSetup) configSetup;
      BlockedResult<EntityType> entity = getEntityBlocked(config.getConfig().getStringList("interact-entity-blocked"));
      BlockedResult<Material>   block  = getBlockBlocked(config.getConfig().getStringList("interact-block-blocked"));
      BlockedResult<Material>   item   = getItemBlocked(config.getConfig().getStringList("interact-item-blocked"));
      BlockedResult<Material>   craft  = getCraftBlocked(config.getConfig().getStringList("craft-blocked"));
      blockedManager.load(entity.getList(), block.getList(), item.getList(), craft.getList());
      return !entity.hasError() || !block.hasError() || !item.hasError() || !craft.hasError();
    }
    return false;
  }


  private BlockedResult<EntityType> getEntityBlocked(List<String> values) {
    if(values == null) {
      return new BlockedResult<EntityType>(null, false);
    }
    List<EntityType> result   = new ArrayList<>();
    boolean          hasError = false;
    for(String name : values) {
      try {
        EntityType type = EntityType.valueOf(name.toUpperCase());
        result.add(type);
      } catch(IllegalArgumentException e) {
        hasError = true;
        LogUtils.logError("&7Invalid entity type: &c" + name);
      }
    }
    return new BlockedResult<EntityType>(result.isEmpty() ? null : result, hasError);
  }

  private BlockedResult<Material> getBlockBlocked(List<String> values) {
    if(values == null) {
      return new BlockedResult<Material>(null, false);
    }
    List<Material> result   = new ArrayList<>();
    boolean        hasError = false;
    for(String name : values) {
      try {
        Material type = Material.valueOf(name.toUpperCase());
        result.add(type);
      } catch(IllegalArgumentException e) {
        hasError = true;
        LogUtils.logError("&7Invalid block type: &c" + name);
      }
    }
    return new BlockedResult<Material>(result.isEmpty() ? null : result, hasError);
  }

  private BlockedResult<Material> getItemBlocked(List<String> values) {
    if(values == null) {
      return new BlockedResult<Material>(null, false);
    }
    List<Material> result   = new ArrayList<>();
    boolean        hasError = false;
    for(String name : values) {
      try {
        Material type = Material.valueOf(name.toUpperCase());
        result.add(type);
      } catch(IllegalArgumentException e) {
        hasError = true;
        LogUtils.logError("&7Invalid item type: &c" + name);
      }
    }
    return new BlockedResult<Material>(result.isEmpty() ? null : result, hasError);
  }

  private BlockedResult<Material> getCraftBlocked(List<String> values) {
    if(values == null) {
      return new BlockedResult<Material>(null, false);
    }
    List<Material> result   = new ArrayList<>();
    boolean        hasError = false;
    for(String name : values) {
      try {
        Material type = Material.valueOf(name.toUpperCase());
        result.add(type);
      } catch(IllegalArgumentException e) {
        hasError = true;
        LogUtils.logError("&7Invalid craft type: &c" + name);
      }
    }
    return new BlockedResult<Material>(result.isEmpty() ? null : result, hasError);
  }

}
