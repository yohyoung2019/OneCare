package kr.co.sharedone.onecare.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kr.co.sharedone.onecare.data.model.UserEntity

@Dao
interface UserDao {

/*    @Query("SELECT * FROM login_user")
    suspend fun getLoggedInUser(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLoggedInUser(loggedInUser: UserEntity)

    @Delete
    suspend fun deleteLoggedInUser(loggedInUser: UserEntity)

    @Query("SELECT * FROM login_user WHERE user_id = :userId")
    suspend fun getUserById(userId: String): UserEntity?*/
}