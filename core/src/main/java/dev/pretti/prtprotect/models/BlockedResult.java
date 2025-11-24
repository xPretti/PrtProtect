package dev.pretti.prtprotect.models;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockedResult<T>
{
  private final List<T> list;
  private final boolean hasError;

  public BlockedResult(@Nullable List<T> list, boolean hasError) {
    this.list     = list;
    this.hasError = hasError;
  }

  public List<T> getList() {
    return list;
  }

  public boolean hasError() {
    return hasError;
  }
}
