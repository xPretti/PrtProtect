package dev.pretti.prtprotect.managers;

import dev.pretti.prtprotect.conditions.EntityBlockedCondition;
import dev.pretti.prtprotect.conditions.MaterialBlockedCondition;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockedManager
{
  private final EntityBlockedCondition   entityBlockedCondition = new EntityBlockedCondition();
  private final MaterialBlockedCondition blockBlockedCondition  = new MaterialBlockedCondition();
  private final MaterialBlockedCondition itemBlockedCondition   = new MaterialBlockedCondition();
  private final MaterialBlockedCondition craftBlockedCondition  = new MaterialBlockedCondition();

  /**
   * Verifica se um tipo de entidade está bloqueado
   */
  public boolean isPassEntity(EntityType type) {
    return entityBlockedCondition.isPass(type);
  }

  /**
   * Verifica se um tipo de material está bloqueado
   */
  public boolean isPassBlock(Material type) {
    return blockBlockedCondition.isPass(type);
  }

  /**
   * Verifica se um tipo de item está bloqueado
   */
  public boolean isPassItem(Material type) {
    return itemBlockedCondition.isPass(type);
  }

  /**
   * Verifica se um tipo de material está bloqueado
   */
  public boolean isPassCraft(Material type) {
    return craftBlockedCondition.isPass(type);
  }

  /**
   * Limpa as condições bloqueadas
   */
  public void clear() {
    entityBlockedCondition.clear();
    blockBlockedCondition.clear();
    itemBlockedCondition.clear();
    craftBlockedCondition.clear();
  }

  /**
   * Carrega as condições bloqueadas
   * @param entity
   * @param blocks
   * @param items
   * @param craft
   */
  public void load(@Nullable List<EntityType> entity, @Nullable List<Material> blocks, @Nullable List<Material> items, @Nullable List<Material> craft) {
    entityBlockedCondition.load(entity);
    blockBlockedCondition.load(blocks);
    itemBlockedCondition.load(items);
    craftBlockedCondition.load(craft);
  }
}
