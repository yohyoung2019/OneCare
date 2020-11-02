package kr.co.sharedone.onecare.data.remote

import kr.co.sharedone.onecare.core.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kr.co.sharedone.onecare.data.model.User
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LoginRemoteDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getUserInfo(param: String): Result<List<User>> {
        val ret = webService.getUserInfo(param)?.userList ?: listOf()
        return Result.Success(ret)
    }

}



