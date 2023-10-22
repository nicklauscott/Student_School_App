package com.xtgem.webuild.fstcawka.page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtgem.webuild.fstcawka.Repository
import com.xtgem.webuild.fstcawka.misc.Uncategorized
import com.xtgem.webuild.fstcawka.models.constants.CustomLiveData
import com.xtgem.webuild.fstcawka.models.constants.LoginStatus
import com.xtgem.webuild.fstcawka.models.constants.PreferenceRepository
import com.xtgem.webuild.fstcawka.models.constants.Session
import com.xtgem.webuild.fstcawka.models.constants.State
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.UserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID



class LoginScreenViewModel: ViewModel() {
    private val repository = Repository.get()
    private val preferenceRepository = PreferenceRepository.getInstance()

    val savedPreference = preferenceRepository.loginPreference
    val status = CustomLiveData<LoginStatus>()
    private  var _newSession = Session(UUID.randomUUID(), UUID.randomUUID())

    fun getSessionDetail() = Session(
        sessionToken = _newSession.sessionToken,
        studentID = _newSession.studentID
    )

    private fun saveLoginData(userSession: UserSession, regLogin: Boolean, regId: String,
                              studentId: UUID, password: String, sessionToken: UUID) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            val preferenceRepository = PreferenceRepository.getInstance()
            repository.database.studentDao().insertUserSession(userSession)
            preferenceRepository.setStudentLoginDetails(
                regLogin = regLogin, regId = regId.trim(), studentId = studentId.toString(),
                password = password, sessionToken = sessionToken
            )
            scope.cancel()
        }
    }

    fun verifyStudent(regLogin: Boolean, regId: String, studentId: String, textPassword: String) {
        status.updateValue(LoginStatus(checking = true))
        val scope = CoroutineScope(Dispatchers.IO)
        var student: Student? = null
        scope.launch {
            if (regLogin) {
                student = repository.database.studentDao().getStudentByRegNoNoLiveData(UUID.randomUUID(), regId)
            }
            else {
                var userUUID = UUID.randomUUID()
                try {
                    userUUID = UUID.fromString(studentId)
                }catch (_: Exception) { }
                student = repository.database.studentDao().getStudentNoLiveData(userUUID)
            }
             scope.cancel()
        }
        viewModelScope.launch {
            delay(500L)
                if (student == null) {
                    if (regLogin)
                        status.updateValue(LoginStatus(checking = false, status = false, reason = State.REG))
                    else
                        status.updateValue(LoginStatus(checking = false, status = false, reason = State.ID))
                }else {
                    val isPassword = Uncategorized.verifyPassword(textPassword, student!!.password)
                    if (isPassword) {
                        val generateSessionId = UUID.randomUUID()
                        val userSession = UserSession(
                            id = UUID.randomUUID(),
                            sessionToken = generateSessionId,
                            studentID = student!!.studentId,
                            active = true,
                            creationDate = LocalDateTime.now()
                        )
                        _newSession = Session(sessionToken = generateSessionId, studentID = student!!.studentId)
                        saveLoginData(userSession, regLogin, regId, student!!.studentId, textPassword, generateSessionId)
                        status.updateValue(LoginStatus(checking = true, status = true, userId = student!!.studentId.toString()))
                    }
                    else {
                        status.updateValue(LoginStatus(checking = false, status = false, reason = State.Password))
                    }
                }
        }

    }
}
