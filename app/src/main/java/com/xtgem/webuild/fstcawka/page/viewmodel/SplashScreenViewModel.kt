package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.models.constants.PreferenceRepository
import com.xtgem.webuild.fstcawka.models.constants.Session
import com.xtgem.webuild.fstcawka.models.entities.UserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.UUID

class SplashScreenViewModel: ViewModel() {
    val repository = Repository.get()
    private val preferenceRepository = PreferenceRepository.getInstance()

    private val sessionToken: UUID = UUID.fromString(preferenceRepository.loginPreference.sessionToken)
    private var userSession: UserSession? = null

    init {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            userSession = repository.database.studentDao().getUserSession(sessionToken)
            scope.cancel()
        }
    }

    fun isSessionValid(): Boolean {
        if (userSession != null) {
            return userSession!!.sessionValidity()
        }
        return false
    }

    fun getSessionDetail() = Session(
        sessionToken = userSession!!.sessionToken,
        studentID = userSession!!.studentID
    )

}