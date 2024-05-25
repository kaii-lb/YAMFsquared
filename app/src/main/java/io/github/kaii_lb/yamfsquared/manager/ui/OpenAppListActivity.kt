package io.github.kaii_lb.yamfsquared.manager.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import io.github.kaii_lb.yamfsquared.manager.services.YAMFManagerProxy

class OpenAppListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()

        Log.d("YAMFSQUAREDWOO", "long pressed")

        YAMFManagerProxy.openAppList();

    }
}