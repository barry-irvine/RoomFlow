package uk.co.gocity.roomflow.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ItineraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItineraryAttraction(itineraryAttraction: ItineraryAttractionEntity)

    @Transaction
    @Query("DELETE FROM itinerary_attraction WHERE itinerary_id = :itineraryId AND attraction_id = :attractionId")
    suspend fun deleteItineraryAttraction(itineraryId: Long, attractionId: String)

    @Transaction
    @Query("SELECT * FROM itinerary WHERE itinerary.itinerary_id = :itineraryId")
    fun getItineraryWithAttractions(itineraryId: Long): Flow<ItineraryWithAttractions?>
}
