package kr.co.sharedone.onecare.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kr.co.sharedone.onecare.core.Result
import kr.co.sharedone.onecare.data.local.TicketLocalDataSource
import kr.co.sharedone.onecare.data.local.UserLocalDataSource
import kr.co.sharedone.onecare.data.model.User
import kr.co.sharedone.onecare.data.remote.LoginRemoteDataSource
import kr.co.sharedone.onecare.data.remote.TicketRemoteDataSource
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LoginRepository @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
//        loginRemoteDataSource.logout()
    }

    suspend fun login(userId: String, password: String): Result<List<User>> {
        // handle login
        val param = "[{ds_json:[{userId:\"${userId}\", password :\"${password}\"}]}]"
        val result = loginRemoteDataSource.getUserInfo(param)

        if (result is Result.Success) {
            setLoggedInUser(result.data[0])
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: User) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}