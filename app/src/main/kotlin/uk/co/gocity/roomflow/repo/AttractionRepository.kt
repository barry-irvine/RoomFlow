package uk.co.gocity.roomflow.repo

import kotlinx.coroutines.flow.Flow
import uk.co.gocity.roomflow.model.Attraction

interface AttractionRepository {

    fun getAttractions(): Flow<List<Attraction>>

}
