package dev.pretti.prtprotect.conditions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface BlockedCondition<T>
{
  void load(@Nullable List<T> types);

  void add(@NotNull T type);

  void remove(@NotNull T type);

  void clear();

  boolean isPass(@NotNull T type);
}
