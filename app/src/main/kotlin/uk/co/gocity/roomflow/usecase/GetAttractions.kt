package uk.co.gocity.roomflow.usecase

import uk.co.gocity.roomflow.repo.AttractionRepository
import javax.inject.Inject

class GetAttractions @Inject constructor(
    private val repo: AttractionRepository,
) {
    operator fun invoke() = repo.getAttractions()
}

