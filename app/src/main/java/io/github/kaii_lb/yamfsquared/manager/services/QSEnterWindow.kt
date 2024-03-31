package io.github.kaii_lb.yamfsquared.manager.services

import android.service.quicksettings.TileService

class QSEnterWindow: TileService() {
    override fun onClick() {
        super.onClick()
        YAMFManagerProxy.currentToWindow()
    }
}