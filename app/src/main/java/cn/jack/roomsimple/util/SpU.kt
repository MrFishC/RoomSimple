package cn.jack.roomsimple.util

import android.content.Context
import android.content.SharedPreferences

/**
 * @创建者 Jack
 * @创建时间 2023/7/20
 * @描述 未使用
 */
object SpU {

    private const val MODE = Context.MODE_PRIVATE

    private const val SHARED_NAME = "SharedPreferences_U"

    fun put(context: Context, key: String, defaultAny: Any) {
        val sp = context.getSharedPreferences(SHARED_NAME, MODE)
        val editor: SharedPreferences.Editor = sp.edit()

        when (defaultAny) {
            is String -> {
                editor.putString(key, defaultAny)
            }

            is Int -> {
                editor.putInt(key, defaultAny)
            }

            is Long -> {
                editor.putLong(key, defaultAny)
            }

            is Float -> {
                editor.putFloat(key, defaultAny)
            }

            is Boolean -> {
                editor.putBoolean(key, defaultAny)
            }
        }
        editor.apply()
    }

    fun get(context: Context, key: String, defaultAny: Any): Any {
        val sp = context.getSharedPreferences(SHARED_NAME, MODE)

        return (when (defaultAny) {
            is String -> {
                sp.getString(key, defaultAny)
            }

            is Int -> {
                sp.getInt(key, defaultAny)
            }

            is Long -> {
                sp.getLong(key, defaultAny)
            }

            is Float -> {
                sp.getFloat(key, defaultAny)
            }

            is Boolean -> {
                sp.getBoolean(key, defaultAny)
            }

            else -> {
                defaultAny
            }
        }) as Any
    }

}