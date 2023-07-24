package uk.co.gocity.roomflow.usecase

import uk.co.gocity.roomflow.model.Itinerary
import uk.co.gocity.roomflow.repo.ItineraryRepository
import javax.inject.Inject

class RemoveAttractionFromItinerary @Inject constructor(
    private val repo: ItineraryRepository,
) {
    suspend operator fun invoke(itinerary: Itinerary, attractionId: String) =
        repo.removeAttractionFromItinerary(itinerary, attractionId)
}

