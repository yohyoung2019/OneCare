package kr.co.sharedone.onecare.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kr.co.sharedone.onecare.data.model.TicketEntity

@Dao
interface TicketDao {

    @Query("SELECT * FROM ticket")
    suspend fun getAllTickets(): List<TicketEntity>

    @Query("SELECT * FROM ticket")
    fun getAllTicketsWithChanges(): LiveData<List<TicketEntity>>

    @Query("SELECT * FROM ticket WHERE title || content LIKE '%' || :searchString || '%'")
    suspend fun getTickets(searchString: String): List<TicketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTicket(ticket: TicketEntity)

    @Delete
    suspend fun deleteTicket(ticket: TicketEntity)

    @Query("SELECT * FROM ticket WHERE serial_key = :serialKey")
    suspend fun getTicketById(serialKey: Int): TicketEntity?
}