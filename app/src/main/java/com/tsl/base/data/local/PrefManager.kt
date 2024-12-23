package com.tsl.base.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.tsl.base.BaseApp

object PrefManager {
    val _preferences: SharedPreferences =
        BaseApp.getContext().getSharedPreferences("bass_app_pref", Context.MODE_PRIVATE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    private operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: Any
    ): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1L) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * This method writes data in SharedPreference
     *
     * @param key key to write
     * @param value value to be written
     * */
    fun set(key: String, value: Any) {
        _preferences[key] = value
    }

    /**
     * This method reads data from SharedPreference
     *
     * @param key key to fetch data
     * @param defaultValue default value to be returned if there is any error
     * */
    private inline fun <reified T : Any> get(key: String, defaultValue: Any): T {
        return _preferences[key, defaultValue]!!
    }

    fun getStr(key: String, defaultValue: String = ""): String {
        return get(key, defaultValue)
    }

    fun getBool(key: String, defaultValue: Boolean = false): Boolean {
        return get(key, defaultValue)
    }

    fun getInt(key: String, defaultValue: Int = -1): Int {
        return get(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float = 0.0F): Float {
        return get(key, defaultValue)
    }


    /**
     * This method provides a state if the SharedPreference contains the key
     *
     * @param key key to be searched
     * @return [Boolean] state
     * */
    operator fun contains(key: String): Boolean {
        return _preferences.contains(key)
    }

    fun remove(vararg keys: String) {
        val editor = _preferences.edit()
        for (key: String in keys) {
            editor.remove(key)
        }
        editor.apply()
    }

    fun remove(key: String) {
        val editor = _preferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun clearSharedPref() {
        val editor = _preferences.edit()
        editor.clear()
        editor.apply()
    }
}