package com.xtgem.webuild.fstcawka.models.constants

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.xtgem.webuild.fstcawka.models.entities.LoginPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class PreferenceRepository(
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) {

    var loginPreference: LoginPreference = LoginPreference()

    init {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            dataStore.data.collect { data ->
                val preference = data.asMap()
                if (preference.isNotEmpty()) {
                    var regLoginQuestion = true
                    try {
                        regLoginQuestion = preference[REG_LOGIN] as Boolean
                    }catch (_: Exception) {}
                    loginPreference = LoginPreference(
                        regLogin = regLoginQuestion,
                        regId = preference[REG_ID] as String,
                        studentId = preference[STUDENT_ID] as String,
                        password = preference[PASSWORD] as String,
                        sessionToken = preference[SESSION_TOKEN] as String
                    )
                }
            }
        }
    }

    suspend fun setStudentLoginDetails(
        regLogin: Boolean, regId: Int, studentId: String,
        password: String, sessionToken: UUID) {
        dataStore.edit {
            it[REG_LOGIN] = regLogin
            it[REG_ID] = regId.toString()
            it[STUDENT_ID] = studentId
            it[PASSWORD] = password
            it[SESSION_TOKEN] = sessionToken.toString()
        }
    }

    companion object {
        private val REG_LOGIN = booleanPreferencesKey("reg_login")
        private val REG_ID = stringPreferencesKey("reg_id")
        private val STUDENT_ID = stringPreferencesKey("student_id")
        private val PASSWORD = stringPreferencesKey("password")
        private val SESSION_TOKEN = stringPreferencesKey("session_token")
        private var INSTANCE: PreferenceRepository? = null

        fun initialize(context: Context) {
            val dataStore = PreferenceDataStoreFactory.create {
                context.preferencesDataStoreFile("studentLoginData")
            }
            if (INSTANCE == null) INSTANCE =
                PreferenceRepository(dataStore)
        }

        fun getInstance(): PreferenceRepository{
            return INSTANCE ?:
            throw IllegalStateException("PreferenceRepository must be initialized")
        }
    }

}