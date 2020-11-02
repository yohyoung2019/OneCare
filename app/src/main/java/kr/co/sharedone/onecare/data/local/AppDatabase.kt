package kr.co.sharedone.onecare.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.co.sharedone.onecare.data.model.TicketEntity
import kr.co.sharedone.onecare.data.model.UserEntity


@Database(entities = [TicketEntity::class,UserEntity::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
    abstract fun userDao():UserDao
}