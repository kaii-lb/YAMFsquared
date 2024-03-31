package io.github.kaii_lb.yamfsquared.manager.ui

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.widget.PopupMenu
import androidx.core.net.toUri
import androidx.preference.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.kaii_lb.yamfsquared.manager.bases.BaseActivity
import io.github.kaii_lb.yamfsquared.common.gson
import io.github.kaii_lb.yamfsquared.databinding.ActivitySettingsBinding
import io.github.kaii_lb.yamfsquared.manager.services.YAMFManagerProxy
import io.github.kaii_lb.yamfsquared.common.model.Config as YAMFConfig

class SettingsActivity :
    BaseActivity<ActivitySettingsBinding>(ActivitySettingsBinding::class.java) {
    companion object {
        val flags = listOf(
            "VIRTUAL_DISPLAY_FLAG_PUBLIC",                          // 1 << 0
            "VIRTUAL_DISPLAY_FLAG_PRESENTATION",                    // 1 << 1
            "VIRTUAL_DISPLAY_FLAG_SECURE",                          // 1 << 2
            "VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY",                // 1 << 3
            "VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR",                     // 1 << 4
            "VIRTUAL_DISPLAY_FLAG_CAN_SHOW_WITH_INSECURE_KEYGUARD", // 1 << 5
            "VIRTUAL_DISPLAY_FLAG_SUPPORTS_TOUCH",                  // 1 << 6
            "VIRTUAL_DISPLAY_FLAG_ROTATES_WITH_CONTENT",            // 1 << 7
            "VIRTUAL_DISPLAY_FLAG_DESTROY_CONTENT_ON_REMOVAL",      // 1 << 8
            "VIRTUAL_DISPLAY_FLAG_SHOULD_SHOW_SYSTEM_DECORATIONS",  // 1 << 9
            "VIRTUAL_DISPLAY_FLAG_TRUSTED",                         // 1 << 10
            "VIRTUAL_DISPLAY_FLAG_OWN_DISPLAY_GROUP",               // 1 << 11
            "VIRTUAL_DISPLAY_FLAG_ALWAYS_UNLOCKED",                 // 1 << 12
            "VIRTUAL_DISPLAY_FLAG_TOUCH_FEEDBACK_DISABLED",         // 1 << 13
        )
    }

    lateinit var config: YAMFConfig
    private val preference: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(this) }

    override fun initData() {
        super.initData()
        config = gson.fromJson(YAMFManagerProxy.configJson, YAMFConfig::class.java)
        baseBinding.etDensityDpi.setText(config.densityDpi.toString())
        baseBinding.btnFlags.text = config.flags.toString()
        baseBinding.sColoerd.isChecked = config.coloredController
        baseBinding.sBackHome.isChecked = config.recentsBackHome
        baseBinding.sShowIMEinWindow.isChecked = config.showImeInWindow
        baseBinding.etSizeH.setText(config.defaultWindowHeight.toString())
        baseBinding.etSizeW.setText(config.defaultWindowWidth.toString())
        baseBinding.sHookLauncherHookRecents.isChecked = config.hookLauncher.hookRecents
        baseBinding.sHookLauncherHookTaskbar.isChecked = config.hookLauncher.hookTaskbar
        baseBinding.sHookLauncherHookPopup.isChecked = config.hookLauncher.hookPopup
        baseBinding.sHookLauncherHookTransientTaskbar.isChecked = config.hookLauncher.hookTransientTaskbar
        baseBinding.sUseAppList.isChecked = preference.getBoolean("useAppList", true)
        baseBinding.sForceShowIME.isChecked = config.showForceShowIME

        baseBinding.btnSurface.text = when (config.surfaceView) {
            0 -> {
                "Texture View"
            }
            1 -> {
                "Surface View"
            }
            else -> {
                Log.d("YAMFSQUARED", "surfaceView: " + config.surfaceView.toString())
                "Unavailable"
            }
        }

        baseBinding.btnWindowsfy.text = when (config.windowfy) {
            0 -> {
                "Move Task"
            }
            1 -> {
                "Start Activity"
            }
            2 -> {
                "Hybrid"
            }
            else -> {
                Log.d("YAMFSQUARED", "windowfy: " + config.windowfy.toString())
                "Unavailable"
            }
        }

        baseBinding.btnFlags.setOnClickListener {
            val checks = BooleanArray(flags.size) { i ->
                config.flags and (1 shl i) != 0
            }
            MaterialAlertDialogBuilder(this)
                .setMultiChoiceItems(flags.toTypedArray(), checks) { _, i, c ->
                    checks[i] = c
                    baseBinding.btnFlags.text = checks.foldIndexed(0) { i, f, b ->
                        if (b)
                            f + (1 shl i)
                        else
                            f
                    }.toString()
                }
                .setPositiveButton("about") { _, _ ->
                    startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = "https://cs.android.com/android/platform/superproject/+/master:frameworks/base/core/java/android/hardware/display/DisplayManager.java".toUri()
                    })
                }
                .show()
        }
        baseBinding.btnWindowsfy.setOnClickListener {
            PopupMenu(this, baseBinding.btnWindowsfy).apply {
                listOf("Move Task", "Start Activity", "Hybrid").forEach { i ->
                    menu.add(i).setOnMenuItemClickListener {
                        baseBinding.btnWindowsfy.text = i
                        true
                    }
                }
            }.show()
        }
        baseBinding.btnSurface.setOnClickListener {
            PopupMenu(this, baseBinding.btnSurface).apply {
                listOf("Texture View", "Surface View").forEach { i ->
                    menu.add(i).setOnMenuItemClickListener {
                        baseBinding.btnSurface.text = i
                        true
                    }
                }
            }.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        config.densityDpi = baseBinding.etDensityDpi.text.toString().toIntOrNull() ?: config.densityDpi
        config.flags = baseBinding.btnFlags.text.toString().toIntOrNull() ?: config.flags
        config.surfaceView = when (val surface = baseBinding.btnSurface.text.toString()) {
            "Texture View" -> {
                0
            }
            "Surface View" -> {
                1
            }
            else -> {
                Log.d("YAMFSQUARED", "surface value: $surface")
                0
            }
        }

        config.windowfy = when (val window = baseBinding.btnWindowsfy.text.toString()) {
            "Move Task" -> {
                0
            }
            "Start Activity" -> {
                1
            }
            "Hybrid" -> {
                2
            }
            else -> {
                Log.d("YAMFSQUARED", "window value: $window")
                0
            }
        }
        config.coloredController = baseBinding.sColoerd.isChecked
        config.recentsBackHome = baseBinding.sBackHome.isChecked
        config.showImeInWindow = baseBinding.sShowIMEinWindow.isChecked
        config.defaultWindowHeight = baseBinding.etSizeH.text.toString().toIntOrNull() ?: config.defaultWindowHeight
        config.defaultWindowWidth = baseBinding.etSizeW.text.toString().toIntOrNull() ?: config.defaultWindowWidth
        config.hookLauncher.hookRecents = baseBinding.sHookLauncherHookRecents.isChecked
        config.hookLauncher.hookTaskbar = baseBinding.sHookLauncherHookTaskbar.isChecked
        config.hookLauncher.hookPopup = baseBinding.sHookLauncherHookPopup.isChecked
        config.hookLauncher.hookTransientTaskbar = baseBinding.sHookLauncherHookTransientTaskbar.isChecked
        config.showForceShowIME = baseBinding.sForceShowIME.isChecked
        preference.edit().putBoolean("useAppList", baseBinding.sUseAppList.isChecked).apply()
        YAMFManagerProxy.updateConfig(gson.toJson(config))

    }
}