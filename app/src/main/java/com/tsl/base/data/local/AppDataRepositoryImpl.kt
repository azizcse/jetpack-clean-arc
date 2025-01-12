package com.tsl.base.data.local

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tsl.base.domain.AppDataRepository
import com.tsl.base.ui.navgraph.Navigation
import com.tsl.base.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataRepositoryImpl @Inject constructor(
    private val application: Application
) : AppDataRepository {
    override suspend fun <T> setValue(key: String, value: T) {
        application.dataStore.edit { preferences ->
            when (value) {
                is String -> preferences[stringPreferencesKey(key)] = value
                is Int -> preferences[intPreferencesKey(key)] = value
                is Boolean -> preferences[booleanPreferencesKey(key)] = value
                is Float -> preferences[floatPreferencesKey(key)] = value
                is Long -> preferences[longPreferencesKey(key)] = value
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    override fun <T> getValue(key: String, defaultValue: T): Flow<T> {
        return application.dataStore.data.map { preferences ->
            when (defaultValue) {
                is String -> preferences[stringPreferencesKey(key)] ?: defaultValue
                is Int -> preferences[intPreferencesKey(key)] ?: defaultValue
                is Boolean -> preferences[booleanPreferencesKey(key)] ?: defaultValue
                is Float -> preferences[floatPreferencesKey(key)] ?: defaultValue
                is Long -> preferences[longPreferencesKey(key)] ?: defaultValue
                else -> throw IllegalArgumentException("Unsupported type")
            } as T
        }
    }

    override fun getNavState(): Flow<String> {
        return application.dataStore.data.map { pref ->
            pref[PreferenceKeys.APP_ENTRY] ?: Navigation.OnboardingNav.nav
        }
    }
}

private val readOnlyProperty = preferencesDataStore(name = "USER_SETTINGS")

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferenceKeys {
    val APP_ENTRY = stringPreferencesKey(Constants.APP_ENTRY)
}