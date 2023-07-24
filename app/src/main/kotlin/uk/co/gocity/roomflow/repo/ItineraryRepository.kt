package uk.co.gocity.roomflow.repo

import kotlinx.coroutines.flow.Flow
import uk.co.gocity.roomflow.model.Itinerary
import uk.co.gocity.roomflow.model.ItineraryAttraction

interface ItineraryRepository {

    fun getItineraryById(id: Long): Flow<Itinerary?>

    suspend fun removeAttractionFromItinerary(
        itinerary: Itinerary,
        attractionId: String
    )

    suspend fun addAttractionToItinerary(
        itinerary: Itinerary,
        attraction: ItineraryAttraction
    )

}
