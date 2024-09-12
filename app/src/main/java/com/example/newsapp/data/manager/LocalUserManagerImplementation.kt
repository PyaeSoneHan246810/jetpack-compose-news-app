package com.example.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class LocalUserManagerImplementation(
    private val context: Context
): LocalUserManager {
    private val dataStore = context.dataStore
    override suspend fun saveAppEntry() {
        dataStore.edit {  pref ->
            pref[APP_ENTRY_KEY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {  pref ->
                pref[APP_ENTRY_KEY] ?: false
            }
    }

    companion object {
        val APP_ENTRY_KEY = booleanPreferencesKey(
            name = Constants.APP_ENTRY
        )
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = Constants.USER_SETTINGS
)