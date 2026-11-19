package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.natsuThemeDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "natsu_manual_prefs"
)

class ThemePreferencesRepository(private val context: Context) {

    private val darkModeKey = booleanPreferencesKey("is_dark_mode_jdm")

    // Defaults to true: Oscuro industrial JDM
    val isDarkModeFlow: Flow<Boolean> = context.natsuThemeDataStore.data.map { prefs ->
        prefs[darkModeKey] ?: true
    }

    suspend fun setDarkMode(isDark: Boolean) {
        context.natsuThemeDataStore.edit { prefs ->
            prefs[darkModeKey] = isDark
        }
    }
}
