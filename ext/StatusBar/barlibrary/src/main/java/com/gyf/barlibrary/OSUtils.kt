package com.gyf.barlibrary

import android.text.TextUtils
import java.util.Locale

/**
 * 手机系统判断
 * Created by geyifeng on 2017/4/18.
 */
object OSUtils {
    private const val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private const val KEY_EMUI_VERSION_NAME = "ro.build.version.emui"
    private const val KEY_DISPLAY = "ro.build.display.id"

    val isMIUI: Boolean
        /**
         * 判断是否为miui
         * Is miui boolean.
         *
         * @return the boolean
         */
        get() {
            val property = getSystemProperty(KEY_MIUI_VERSION_NAME, "")
            return !TextUtils.isEmpty(property)
        }

    val isMIUI6Later: Boolean
        /**
         * 判断miui版本是否大于等于6
         * Is miui 6 later boolean.
         *
         * @return the boolean
         */
        get() {
            val version = mIUIVersion
            return if ((!version.isEmpty() && version.substring(1).toInt() >= 6)) {
                true
            } else false
        }

    val mIUIVersion: String
        /**
         * 获得miui的版本
         * Gets miui version.
         *
         * @return the miui version
         */
        get() = if (isMIUI) getSystemProperty(KEY_MIUI_VERSION_NAME, "") else ""

    val isEMUI: Boolean
        /**
         * 判断是否为emui
         * Is emui boolean.
         *
         * @return the boolean
         */
        get() {
            val property = getSystemProperty(KEY_EMUI_VERSION_NAME, "")
            return !TextUtils.isEmpty(property)
        }

    val eMUIVersion: String
        /**
         * 得到emui的版本
         * Gets emui version.
         *
         * @return the emui version
         */
        get() = if (isEMUI) getSystemProperty(KEY_EMUI_VERSION_NAME, "") else ""

    val isEMUI3_1: Boolean
        /**
         * 判断是否为emui3.1版本
         * Is emui 3 1 boolean.
         *
         * @return the boolean
         */
        get() {
            val property = eMUIVersion
            if ("EmotionUI 3" == property || property.contains("EmotionUI_3.1")) {
                return true
            }
            return false
        }

    val isFlymeOS: Boolean
        /**
         * 判断是否为flymeOS
         * Is flyme os boolean.
         *
         * @return the boolean
         */
        get() = flymeOSFlag.lowercase(Locale.getDefault()).contains("flyme")

    val isFlymeOS4Later: Boolean
        /**
         * 判断flymeOS的版本是否大于等于4
         * Is flyme os 4 later boolean.
         *
         * @return the boolean
         */
        get() {
            val version = flymeOSVersion
            val num: Int
            if (!version.isEmpty()) {
                num = if (version.lowercase(Locale.getDefault()).contains("os")) {
                    version.substring(9, 10).toInt()
                } else {
                    version.substring(6, 7).toInt()
                }
                if (num >= 4) {
                    return true
                }
            }
            return false
        }

    val isFlymeOS5: Boolean
        /**
         * 判断flymeOS的版本是否等于5
         * Is flyme os 5 boolean.
         *
         * @return the boolean
         */
        get() {
            val version = flymeOSVersion
            val num: Int
            if (!version.isEmpty()) {
                num = if (version.lowercase(Locale.getDefault()).contains("os")) {
                    version.substring(9, 10).toInt()
                } else {
                    version.substring(6, 7).toInt()
                }
                if (num == 5) {
                    return true
                }
            }
            return false
        }


    val flymeOSVersion: String
        /**
         * 得到flymeOS的版本
         * Gets flyme os version.
         *
         * @return the flyme os version
         */
        get() = if (isFlymeOS) getSystemProperty(KEY_DISPLAY, "") else ""

    private val flymeOSFlag: String
        get() = getSystemProperty(KEY_DISPLAY, "")

    private fun getSystemProperty(key: String, defaultValue: String): String {
        try {
            val clz = Class.forName("android.os.SystemProperties")
            val get = clz.getMethod("get", String::class.java, String::class.java)
            return get.invoke(clz, key, defaultValue) as String
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return defaultValue
    }
}
