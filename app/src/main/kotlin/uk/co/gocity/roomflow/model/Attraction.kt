package uk.co.gocity.roomflow.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attraction(
    val id: String,
    val name: String
) : Parcelable
