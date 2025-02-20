package com.ramirinter.lologin.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramirinter.lologin.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchLocation() {
        viewModelScope.launch {
            try {
                val newLocation = locationRepository.getCurrentLocation()
                if (newLocation != null) {
                    _location.value = newLocation
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "No se pudo obtener la ubicación"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al obtener la ubicación"
            }
        }
    }
}
