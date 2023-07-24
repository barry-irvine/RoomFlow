package uk.co.gocity.roomflow.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface AttractionDao {

    @Query("SELECT * FROM attraction")
    fun getAttractions(): Flow<List<AttractionEntity>>
}
