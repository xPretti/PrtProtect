package dev.pretti.prtprotect.conditions;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class EntityBlockedCondition implements BlockedCondition<EntityType>
{
  private final EnumSet<EntityType> blockedData = EnumSet.noneOf(EntityType.class);

  @Override
  public void load(@Nullable List<EntityType> types) {
    clear();
    if(types != null) {
      this.blockedData.addAll(types);
    }
  }

  @Override
  public void add(@NotNull EntityType type) {
    this.blockedData.add(type);
  }

  @Override
  public void remove(@NotNull EntityType type) {
    this.blockedData.remove(type);
  }

  @Override
  public void clear() {
    this.blockedData.clear();
  }

  @Override
  public boolean isPass(@NotNull EntityType obj) {
    if(blockedData.isEmpty()) {
      return true;
    }
    return !blockedData.contains(obj);
  }
}
