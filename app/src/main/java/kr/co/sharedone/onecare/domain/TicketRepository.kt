package kr.co.sharedone.onecare.domain

import androidx.lifecycle.LiveData
import kr.co.sharedone.onecare.core.Result
import kr.co.sharedone.onecare.data.model.Ticket
import kr.co.sharedone.onecare.data.model.TicketEntity
import kr.co.sharedone.onecare.data.local.TicketLocalDataSource
import kr.co.sharedone.onecare.data.remote.TicketRemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class TicketRepository @Inject constructor(
    private val ticketRemoteDataSource: TicketRemoteDataSource,
    private val ticketLocalDataSource: TicketLocalDataSource
) {

    suspend fun getTicketByName(ticketName: String): Flow<Result<List<Ticket>>> =
        callbackFlow {
            offer(getCachedTickets(ticketName))

            ticketRemoteDataSource.getTicketByName(ticketName).collect {
                when (it) {
                    is Result.Success -> {
                        for (ticket in it.data) {
//                            saveTicket(ticket.asTicketEntity())
                        }
                        offer(getCachedTickets(ticketName))
                    }
                    is Result.Error -> {
                        offer(getCachedTickets(ticketName))
                    }
                    is Result.Loading -> TODO()
                }
            }
            awaitClose { cancel() }
        }

    suspend fun saveTicket(ticket: Ticket) {
        TODO("Not yet implemented")
    }

/*

    suspend fun saveMyTicket(ticket: Ticket) {
        ticketLocalDataSource.saveMyTicket(ticket)
    }

    suspend fun isTicketMine(ticket: Ticket): Boolean =
        ticketLocalDataSource.isTicketMine(ticket)

*/

    suspend fun saveTicket(ticket: TicketEntity) {
        ticketLocalDataSource.saveTicket(ticket)
    }

    suspend fun isMyTicket(ticket: Ticket): Boolean {
        TODO("Not yet implemented")
    }

    suspend fun getCachedTickets(ticketName: String): Result<List<Ticket>> {
        return ticketLocalDataSource.getCachedTickets(ticketName)
    }

    fun getMyTickets(): LiveData<List<Ticket>> {
        TODO("Not yet implemented")
    }

    suspend fun deleteMyTicket(ticket: Ticket) {
        TODO("Not yet implemented")
    }
}
