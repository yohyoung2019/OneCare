package kr.co.sharedone.onecare.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kr.co.sharedone.onecare.core.Result
import kr.co.sharedone.onecare.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserLocalDataSource @Inject constructor(
        private val userDao: UserDao
) {

}

