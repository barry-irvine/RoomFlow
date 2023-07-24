package uk.co.gocity.roomflow.repo

import kotlinx.coroutines.flow.Flow
import uk.co.gocity.roomflow.db.AttractionDao
import uk.co.gocity.roomflow.db.mapper.EntityToAttractionMapper
import uk.co.gocity.roomflow.extensions.mapList
import uk.co.gocity.roomflow.model.Attraction
import javax.inject.Inject

internal class AttractionRepositoryImpl @Inject constructor(
    private val dao: AttractionDao,
    private val mapper: EntityToAttractionMapper
) : AttractionRepository {
    override fun getAttractions(): Flow<List<Attraction>> =
        dao.getAttractions().mapList {
            mapper.map(it)
        }
}
