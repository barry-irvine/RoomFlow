package uk.co.gocity.roomflow.db.mapper

import uk.co.gocity.roomflow.db.ItineraryWithAttractions
import uk.co.gocity.roomflow.model.Itinerary
import uk.co.gocity.roomflow.model.ItineraryAttraction
import javax.inject.Inject

internal class EntityToItineraryMapper @Inject constructor(private val attractionMapper: EntityToAttractionMapper) :
    Mapper<ItineraryWithAttractions, Itinerary> {

    override fun map(from: ItineraryWithAttractions): Itinerary =
        Itinerary(id = from.itinerary.itineraryId,
            name = from.itinerary.name,
            startDateMillis = from.itinerary.startDateMillis,
            endDateMillis = from.itinerary.endDateMillis,
            itineraryAttractions = from.attractions.map {
                ItineraryAttraction(
                    attractionMapper.map(it.attraction),
                    it.itineraryAttraction.visitDateMillis
                )
            })
}
