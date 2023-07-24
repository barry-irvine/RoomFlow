package uk.co.gocity.roomflow.repo

import kotlinx.coroutines.flow.Flow
import uk.co.gocity.roomflow.db.ItineraryAttractionEntity
import uk.co.gocity.roomflow.db.ItineraryDao
import uk.co.gocity.roomflow.db.mapper.EntityToItineraryMapper
import uk.co.gocity.roomflow.extensions.mapWhenNotNull
import uk.co.gocity.roomflow.model.Itinerary
import uk.co.gocity.roomflow.model.ItineraryAttraction
import javax.inject.Inject

internal class ItineraryRepositoryImpl @Inject constructor(
    private val dao: ItineraryDao,
    private val mapper: EntityToItineraryMapper
) : ItineraryRepository {
    override fun getItineraryById(id: Long): Flow<Itinerary?> =
        dao.getItineraryWithAttractions(id).mapWhenNotNull { mapper.map(it) }

    override suspend fun removeAttractionFromItinerary(itinerary: Itinerary, attractionId: String) =
        dao.deleteItineraryAttraction(itinerary.id, attractionId)

    override suspend fun addAttractionToItinerary(
        itinerary: Itinerary,
        attraction: ItineraryAttraction
    ) = dao.insertItineraryAttraction(
        ItineraryAttractionEntity(
            requireNotNull(itinerary.id),
            attraction.attraction.id, attraction.visitDateMillis
        )
    )
}
