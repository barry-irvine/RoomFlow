package uk.co.gocity.roomflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.gocity.roomflow.model.Itinerary
import uk.co.gocity.roomflow.model.ItineraryAttraction
import uk.co.gocity.roomflow.usecase.AddAttractionToItinerary
import uk.co.gocity.roomflow.usecase.GetAttractions
import uk.co.gocity.roomflow.usecase.GetItineraryById
import uk.co.gocity.roomflow.usecase.RemoveAttractionFromItinerary
import javax.inject.Inject

@HiltViewModel
class ItineraryViewModel @Inject constructor(
    getItineraryById: GetItineraryById,
    getAttractions: GetAttractions,
    private val addAttractionToItinerary: AddAttractionToItinerary,
    private val removeAttractionFromItinerary: RemoveAttractionFromItinerary
): ViewModel() {

    val itinerary =
        getItineraryById(1) // Normally the number would be passed in via navigation and savedStateHandle

    val attractions = getAttractions()

    fun removeAttraction(itinerary: Itinerary, attractionId: String) {
        viewModelScope.launch {
            removeAttractionFromItinerary(itinerary, attractionId)
        }
    }

    fun addAttraction(itinerary: Itinerary, attraction: ItineraryAttraction) {
        viewModelScope.launch {
            addAttractionToItinerary(itinerary, attraction)
        }
    }

}
