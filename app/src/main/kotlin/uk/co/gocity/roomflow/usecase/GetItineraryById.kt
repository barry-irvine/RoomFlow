package uk.co.gocity.roomflow.usecase

import uk.co.gocity.roomflow.repo.ItineraryRepository
import javax.inject.Inject

class GetItineraryById @Inject constructor(
    private val repo: ItineraryRepository,
) {
    operator fun invoke(id: Long) =
        repo.getItineraryById(id)
}
