package kr.co.sharedone.onecare.data.remote

import kr.co.sharedone.onecare.core.Result
import kr.co.sharedone.onecare.data.model.Ticket
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TicketRemoteDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getTicketByName(searchString: String): Flow<Result<List<Ticket>>> =
            callbackFlow {
                offer(
                    Result.Success(
                                webService.getTicketByName(searchString)?.ticketList ?: listOf()
                        )
                )
                awaitClose { close() }
            }
}

