package io.github.kaii_lb.yamfsquared.xposed;

import android.view.Surface;
import io.github.kaii_lb.yamfsquared.xposed.IOpenCountListener;

interface IYAMFManager {
    String getVersionName();

    int getVersionCode();

    int getUid();

    void createWindow();

    long getBuildTime();

    String getConfigJson();

    void updateConfig(String newConfig);

    void registerOpenCountListener(IOpenCountListener iOpenCountListener);

    void unregisterOpenCountListener(IOpenCountListener iOpenCountListener);

    void openAppList();

    void currentToWindow();

    void resetAllWindow();
}