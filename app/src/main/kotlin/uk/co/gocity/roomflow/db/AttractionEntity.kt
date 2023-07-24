package uk.co.gocity.roomflow.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attraction")
internal data class AttractionEntity(
    @ColumnInfo("attraction_id")
    @PrimaryKey
    val attractionId: String,
    val name: String
)

