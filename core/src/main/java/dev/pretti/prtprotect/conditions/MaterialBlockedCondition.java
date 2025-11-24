package dev.pretti.prtprotect.conditions;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class MaterialBlockedCondition implements BlockedCondition<Material>
{
  private final EnumSet<Material> blockedData = EnumSet.noneOf(Material.class);

  @Override
  public void load(@Nullable List<Material> types) {
    clear();
    if(types != null) {
      this.blockedData.addAll(types);
    }
  }

  @Override
  public void add(@NotNull Material type) {
    this.blockedData.add(type);
  }

  @Override
  public void remove(@NotNull Material type) {
    this.blockedData.remove(type);
  }

  @Override
  public void clear() {
    this.blockedData.clear();
  }

  @Override
  public boolean isPass(@NotNull Material obj) {
    if(blockedData.isEmpty()) {
      return true;
    }
    return !blockedData.contains(obj);
  }
}
