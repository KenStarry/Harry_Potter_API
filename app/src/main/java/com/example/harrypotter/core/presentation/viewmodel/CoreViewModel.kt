package com.example.harrypotter.core.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.core.domain.model.CharacterModel
import com.example.harrypotter.core.domain.model.CoreEvents
import com.example.harrypotter.core.domain.use_case.HarryPotterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val useCases: HarryPotterUseCases
) : ViewModel() {

    val harryPotterCharacters = MutableLiveData<Response<List<CharacterModel>>>()

    fun onEvent(event: CoreEvents) {
        when (event) {

            is CoreEvents.GetCharacters -> {
                viewModelScope.launch {
                    harryPotterCharacters.value = useCases.getCharacters()
                }
            }
        }
    }
}