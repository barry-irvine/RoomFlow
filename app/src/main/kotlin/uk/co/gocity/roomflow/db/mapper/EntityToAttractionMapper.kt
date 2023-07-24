package uk.co.gocity.roomflow.db.mapper

import uk.co.gocity.roomflow.db.AttractionEntity
import uk.co.gocity.roomflow.model.Attraction
import javax.inject.Inject

internal class EntityToAttractionMapper @Inject constructor() :
    Mapper<AttractionEntity, Attraction> {

    override fun map(from: AttractionEntity): Attraction =
        Attraction(from.attractionId, from.name)
}
