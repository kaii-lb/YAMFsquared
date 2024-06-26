package io.github.kaii_lb.yamfsquared.manager.services

import android.service.quicksettings.TileService
import androidx.preference.PreferenceManager

class QSNewWindowService : TileService() {
    override fun onClick() {
        super.onClick()
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("useAppList", true))
            YAMFManagerProxy.openAppList()
        else YAMFManagerProxy.createWindow()
    }
}