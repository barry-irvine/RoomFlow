package uk.co.gocity.roomflow.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import uk.co.gocity.roomflow.extensions.asLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Parcelize
data class Itinerary(
    val id: Long = 0,
    val name: String,
    val startDateMillis: Long,
    val endDateMillis: Long,
    var itineraryAttractions: List<ItineraryAttraction> = emptyList()
) : Parcelable {

    @IgnoredOnParcel
    val dateRange: LongRange = startDateMillis..endDateMillis

    @IgnoredOnParcel
    val yearRange: IntRange =
        startDateMillis.asLocalDateTime().year..endDateMillis.asLocalDateTime().year

    @SuppressLint("NewApi")
    fun rangeString(): String {
        val startDay = startDateMillis.asLocalDateTime().dayOfMonth
        val startMonth = startDateMillis.asLocalDateTime().month
        val endDay = endDateMillis.asLocalDateTime().dayOfMonth
        val endMonth = endDateMillis.asLocalDateTime().month

        return if (startMonth == endMonth) {
            "$startDay – $endDay ${startMonth.getDisplayName(TextStyle.SHORT, Locale.getDefault())}"
        } else {
            "$startDay ${
                startMonth.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            } – $endDay ${
                endMonth.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            }"
        }
    }

}

@Parcelize
data class ItineraryAttraction(
    val attraction: Attraction,
    val visitDateMillis: Long
) : Parcelable
