package uk.co.gocity.roomflow.usecase

import uk.co.gocity.roomflow.model.Itinerary
import uk.co.gocity.roomflow.model.ItineraryAttraction
import uk.co.gocity.roomflow.repo.ItineraryRepository
import javax.inject.Inject

class AddAttractionToItinerary @Inject constructor(
    private val repo: ItineraryRepository,
) {
    suspend operator fun invoke(itinerary: Itinerary, attraction: ItineraryAttraction) =
        repo.addAttractionToItinerary(itinerary, attraction)
}

