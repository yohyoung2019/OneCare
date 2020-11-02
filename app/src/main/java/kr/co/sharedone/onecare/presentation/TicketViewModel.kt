
package kr.co.sharedone.onecare.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kr.co.sharedone.onecare.application.ToastHelper
import kr.co.sharedone.onecare.core.Result
import kr.co.sharedone.onecare.domain.TicketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class TicketViewModel @ViewModelInject constructor(
    private val repository: TicketRepository,
    private val toastHelper: ToastHelper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentTicketName = savedStateHandle.getLiveData<String>("ticketName", "margarita")

    fun setTicket(ticketName: String) {
        currentTicketName.value = ticketName
    }

    val fetchTicketList = currentTicketName.distinctUntilChanged().switchMap { cocktailName ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Result.Loading())
            try {
                repository.getTicketByName(cocktailName).collect {
                    emit(it)
                }
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }

/*    fun saveOrDeleteFavoriteTicket(cocktail: Ticket) {
        viewModelScope.launch {
            if (repository.isTicketFavorite(cocktail)) {
                repository.deleteFavoriteTicket(cocktail)
                toastHelper.sendToast("Ticket deleted from favorites")
            } else {
                repository.saveFavoriteTicket(cocktail)
                toastHelper.sendToast("Ticket saved to favorites")
            }
        }
    }

    fun getFavoriteTickets() =
        liveData<Response<List<Ticket>>>(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Response.Loading())
            try {
                emitSource(repository.getFavoritesTickets().map { Response.Success(it) })
            } catch (e: Exception) {
                emit(Response.Error(e))
            }
        }

    fun deleteFavoriteTicket(cocktail: Ticket) {
        viewModelScope.launch {
            repository.deleteFavoriteTicket(cocktail)
            toastHelper.sendToast("Ticket deleted from favorites")
        }
    }

    suspend fun isTicketFavorite(cocktail: Ticket): Boolean =
        repository.isTicketFavorite(cocktail)*/
}
