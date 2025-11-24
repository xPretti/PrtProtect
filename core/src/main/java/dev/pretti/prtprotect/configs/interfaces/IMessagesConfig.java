package dev.pretti.prtprotect.configs.interfaces;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IMessagesConfig extends IConfig
{
  List<String> getHelp();

  String getNoPermission();

  String getReload();

  String getReloadError();

  @Nullable
  String getInteractBlockBlocked();

  @Nullable
  String getInteractEntityBlocked();

  @Nullable
  String getInteractItemBlocked();

  @Nullable
  String getCraftBlocked();
}
