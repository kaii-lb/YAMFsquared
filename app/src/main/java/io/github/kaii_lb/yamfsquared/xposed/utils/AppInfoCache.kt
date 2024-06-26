package io.github.kaii_lb.yamfsquared.xposed.utils

import android.content.ComponentName
import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import androidx.wear.widget.RoundedDrawable

object AppInfoCache {
    private val map = mutableMapOf<ComponentName, Pair<Drawable, CharSequence>>()

    fun getIconLabel(info: ActivityInfo): Pair<Drawable, CharSequence> {
        return map.getOrPut(info.componentName) {
            RoundedDrawable().apply {
                isClipEnabled = true
                radius = 100
                drawable = info.loadIcon(Instances.packageManager)
            } to info.loadLabel(Instances.packageManager)
        }
    }
}