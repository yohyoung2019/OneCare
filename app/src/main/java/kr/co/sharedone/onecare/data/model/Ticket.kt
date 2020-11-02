package kr.co.sharedone.onecare.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize  //이거 빼도 돼나???
data class Ticket(
    @SerializedName("serial_key")
    val serial_key: Int,
    @SerializedName("account")
    val account: String = "",
    @SerializedName("postno")
    val postno: String = "",
    @SerializedName("title")
    val title: String="",
    @SerializedName("content")
    val content: String="",
    @SerializedName("type")
    val type: String="",
    @SerializedName("status")
    val status: String="",
    @SerializedName("cag01")
    val cag01: String="",
    @SerializedName("cag02")
    val cag02: String="",
    @SerializedName("cag03")
    val cag03: String=""
    ): Parcelable

data class TicketList(
    @SerializedName("tickets")
    val ticketList: List<Ticket> = listOf()
)

@Entity(tableName = "ticket")
data class TicketEntity(
    @PrimaryKey
    val serial_key: Int,
    @ColumnInfo(name = "account")
    val account: String = "",
    @ColumnInfo(name = "postno")
    val postno: String="",
    @ColumnInfo(name = "title")
    val title: String="",
    @ColumnInfo(name = "content")
    val content: String="",
    @ColumnInfo(name = "type")
    val type: String="",
    @ColumnInfo(name = "status")
    val status: String="",
    @ColumnInfo(name = "cag01")
    val cag01: String="",
    @ColumnInfo(name = "cag02")
    val cag02: String="",
    @ColumnInfo(name = "cag03")
    val cag03: String=""

    )

fun List<TicketEntity>.asTicketList(): List<Ticket> = this.map {
    Ticket(
            it.serial_key,
            it.account,
            it.postno,
            it.content,
            it.type,
            it.status,
            it.cag01,
            it.cag02,
            it.cag03
    )
}

fun Ticket.asTicketEntity(): TicketEntity =
    TicketEntity(
            this.serial_key,
            this.account,
            this.postno,
            this.content,
            this.type,
            this.status,
            this.cag01,
            this.cag02,
            this.cag03
    )
