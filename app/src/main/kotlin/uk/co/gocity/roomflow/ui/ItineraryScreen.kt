package uk.co.gocity.roomflow.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.co.gocity.roomflow.model.Attraction
import uk.co.gocity.roomflow.model.Itinerary
import uk.co.gocity.roomflow.model.ItineraryAttraction
import uk.co.gocity.roomflow.ui.viewmodel.ItineraryViewModel

@Composable
fun ItineraryScreen(model: ItineraryViewModel = hiltViewModel()) {
    var savedItinerary by rememberSaveable { mutableStateOf<Itinerary?>(null) }

    val itinerary by model.itinerary.collectAsStateWithLifecycle(savedItinerary)
    val attractions by model.attractions.collectAsStateWithLifecycle(emptyList())
    itinerary?.let {
        savedItinerary = it
        ItineraryScreen(it, attractions, model::addAttraction, model::removeAttraction)
    }
}

@Composable
fun ItineraryScreen(
    itinerary: Itinerary,
    attractions: List<Attraction>,
    addAttractionToItinerary: (Itinerary, ItineraryAttraction) -> Unit,
    removeAttractionFromItinerary: (Itinerary, String) -> Unit,
) {
    var selectedAttraction by rememberSaveable { mutableStateOf<Attraction?>(null) }


    Column(
        Modifier
            .padding(16.dp)
    ) {
        Text(itinerary.name, style = MaterialTheme.typography.headlineMedium)
        Text(
            itinerary.rangeString(),
            Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            "Choose attractions",
            Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        LazyColumn {
            items(attractions, key = { it.id }) { attraction ->
                val isAdded =
                    itinerary.itineraryAttractions.any { it.attraction.id == attraction.id }
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        attraction.name,
                        Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    IconButton(onClick = {
                        if (isAdded) {
                            removeAttractionFromItinerary(itinerary, attraction.id)
                        } else {
                            selectedAttraction = attraction
                        }
                    }) {
                        Icon(
                            imageVector = if (!isAdded) Icons.Filled.Add else Icons.Filled.Check,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
    selectedAttraction?.let {
        BottomSheetDatePicker(itinerary, it, addAttractionToItinerary) {
            selectedAttraction = null
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDatePicker(
    itinerary: Itinerary,
    selectedAttraction: Attraction,
    addAttractionToItinerary: (Itinerary, ItineraryAttraction) -> Unit,
    dismissSheet: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = dismissSheet,
        sheetState = bottomSheetState,
        windowInsets = WindowInsets.navigationBars,
        dragHandle = null
    ) {
        val pickerState = rememberDatePickerState(
            initialSelectedDateMillis = itinerary.startDateMillis,
            yearRange = itinerary.yearRange
        )
        Column(
            Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add to itinerary",
                Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium,
            )
            DatePicker(
                title = null,
                headline = null,
                state = pickerState,
                dateValidator = { selectedDateMillis -> selectedDateMillis in itinerary.dateRange },
                showModeToggle = false
            )
            Button(onClick = {
                addAttractionToItinerary(
                    itinerary,
                    ItineraryAttraction(selectedAttraction, pickerState.selectedDateMillis!!)
                )
                dismissSheet()
            }, Modifier.padding(bottom = 16.dp)) {
                Text("Select date")
            }
        }
    }
}
