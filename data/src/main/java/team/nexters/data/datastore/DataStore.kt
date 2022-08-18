package team.nexters.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val SemonemoPreferencesDataStore = "semonemo_preferences"

val Context.dataStore by preferencesDataStore(
    name = SemonemoPreferencesDataStore
)

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext appContext: Context
) {
    private val datastore = appContext.dataStore

    private val sessionKey = stringPreferencesKey("sessionKey")
    private val uidKey = intPreferencesKey("uidKey")

    suspend fun setSession(session: String) {
        datastore.edit { preferences ->
            preferences[sessionKey] = session
        }
    }

    val session = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[sessionKey] ?: ""
        }

    suspend fun setUid(uid: Int) {
        datastore.edit { preferences ->
            preferences[uidKey] = uid
        }
    }

    val uid = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[uidKey] ?: 0
        }
}


