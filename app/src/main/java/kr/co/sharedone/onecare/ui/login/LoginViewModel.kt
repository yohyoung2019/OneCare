package kr.co.sharedone.onecare.ui.login

import android.util.Patterns
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kr.co.sharedone.onecare.domain.LoginRepository
import kr.co.sharedone.onecare.core.Result


import kr.co.sharedone.onecare.R
import kr.co.sharedone.onecare.application.ToastHelper
import kr.co.sharedone.onecare.domain.TicketRepository
/**
 * ViewModel에 @iewModelInject constructor(... 를 하면
 * 다른 Activity 나 Fragment 에서 "val loginViewModel by viewModels<LoginViewModel>()" 을 통해 뷰모델을 사용할 수 있다.
 * */
@ExperimentalCoroutinesApi
class LoginViewModel @ViewModelInject constructor(
    private val loginRepository: LoginRepository,
    private val toastHelper: ToastHelper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(userId: String, password: String) {
        viewModelScope.launch {
            try {
                val result = loginRepository.login(userId, password)
                _loginResult.value = LoginResult(success = UserProfile(userId = ""))
            } catch (e: Exception) {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserIdValid(username)) {
            _loginForm.value = LoginFormState(userIdError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserIdValid(userId: String): Boolean {
        return userId.isNotBlank()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 1
    }
}


data class UserProfile(
    val userId: String
    //... other data fields that may be accessible to the UI
)

data class LoginFormState(
    val userIdError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)

data class LoginResult(
    val success: UserProfile? = null,
    val error: Int? = null
)

