package io.github.kaii_lb.yamfsquared.xposed.utils

import android.annotation.SuppressLint
import android.widget.Toast

@SuppressLint("StaticFieldLeak")
object TipUtil {
    fun showToast(msg: String) {
        Toast.makeText(Instances.systemContext, "[YAMF] $msg", Toast.LENGTH_LONG).show()
    }
}