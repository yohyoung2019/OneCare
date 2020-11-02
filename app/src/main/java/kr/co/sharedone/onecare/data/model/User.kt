package kr.co.sharedone.onecare.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class User(
        val user_id: String,
        val password: String,
        val username: String,
        val user_group: String
)
data class UserList(
        val userList: List<User> = listOf()
)

@Entity(tableName = "login_user")
data class UserEntity(
        @PrimaryKey
        @ColumnInfo(name = "user_id")
        val userId: String = "",
        @ColumnInfo(name = "password")
        val password: String = "",
        @ColumnInfo(name = "username")
        val userName: String = "",
        @ColumnInfo(name = "user_group")
        val userGroup: String = ""

)
