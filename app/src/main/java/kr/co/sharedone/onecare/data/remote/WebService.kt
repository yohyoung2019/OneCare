package kr.co.sharedone.onecare.data.remote

import kr.co.sharedone.onecare.data.model.Ticket
import kr.co.sharedone.onecare.data.model.TicketList
import kr.co.sharedone.onecare.data.model.User
import kr.co.sharedone.onecare.data.model.UserList
import retrofit2.http.*


interface WebService {
    @GET("getTicket.json")
    suspend fun getTicketByName(@Query(value = "s") searchString: String): TicketList?


    @Headers(
        "Content-Type: application/json",
        "Accept-Encoding: gzip, deflate, br",
        "Connection: keep-alive",
        "Accept: */*"
    )
    @POST("bas/getLoginInfo.json")
    suspend fun getUserInfo(@Body param: String): UserList?
}

