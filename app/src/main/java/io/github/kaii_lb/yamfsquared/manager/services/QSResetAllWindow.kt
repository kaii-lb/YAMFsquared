package io.github.kaii_lb.yamfsquared.manager.services

import android.service.quicksettings.TileService

class QSResetAllWindow: TileService() {
    override fun onClick() {
        YAMFManagerProxy.resetAllWindow()
    }
}