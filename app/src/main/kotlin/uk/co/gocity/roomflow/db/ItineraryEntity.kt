package uk.co.gocity.roomflow.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "itinerary")
internal data class ItineraryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itinerary_id")
    val itineraryId: Long = 0,
    val name: String,
    @ColumnInfo(name = "start_date")
    val startDateMillis: Long,
    @ColumnInfo(name = "end_date")
    val endDateMillis: Long
)

@Entity(tableName = "itinerary_attraction", primaryKeys = ["itinerary_id", "attraction_id"])
internal data class ItineraryAttractionEntity(
    @ColumnInfo(name = "itinerary_id")
    val itineraryId: Long,
    @ColumnInfo(name = "attraction_id")
    val attractionId: String,
    @ColumnInfo(name = "visit_date")
    val visitDateMillis: Long
)

internal data class ItineraryAttractionWithAttraction(
    @Embedded
    val itineraryAttraction: ItineraryAttractionEntity,
    @Relation(
        entity = AttractionEntity::class,
        parentColumn = "attraction_id", entityColumn = "attraction_id"
    )
    val attraction: AttractionEntity
)

internal data class ItineraryWithAttractions(
    @Embedded
    val itinerary: ItineraryEntity,
    @Relation(
        entity = ItineraryAttractionEntity::class,
        parentColumn = "itinerary_id",
        "itinerary_id"
    )
    val attractions: List<ItineraryAttractionWithAttraction>
)
