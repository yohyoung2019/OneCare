package kr.co.sharedone.onecare.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kr.co.sharedone.onecare.core.Result
import kr.co.sharedone.onecare.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TicketLocalDataSource @Inject constructor(
        private val ticketDao: TicketDao
) {
    fun getTickets(): LiveData<List<Ticket>> {
        return ticketDao.getAllTicketsWithChanges().map { it.asTicketList() }
    }

    suspend fun deleteTicket(ticket: Ticket) {
        return ticketDao.deleteTicket(ticket.asTicketEntity())
    }

    suspend fun saveTicket(ticket: TicketEntity) {
        ticketDao.saveTicket(ticket)
    }

    suspend fun getCachedTickets(searchString: String): Result<List<Ticket>> {
        return Result.Success(ticketDao.getTickets(searchString).asTicketList())
    }

    /*suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean {
        return ticketDao.getCocktailById(cocktail.cocktailId) != null
    }*/
}

