package com.kenstarry.harrypotter.core.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.domain.model.Spell
import com.kenstarry.harrypotter.core.domain.use_case.HarryPotterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val useCases: HarryPotterUseCases
) : ViewModel() {

    val harryPotterCharacters = MutableLiveData<Response<List<CharacterModel>>>()
    val selectedCharacter = MutableLiveData<Response<CharacterModel>>()
    val hogwartsStudents = MutableLiveData<Response<List<CharacterModel>>>()
    val hogwartsStaff = MutableLiveData<Response<List<CharacterModel>>>()
    val hogwartsSpells = MutableLiveData<Response<List<Spell>>>()

    fun onEvent(event: CoreEvents) {
        when (event) {

            is CoreEvents.GetCharacters -> {
                viewModelScope.launch {
                    harryPotterCharacters.value = useCases.getCharacters()
                }
            }
            is CoreEvents.GetAllStaff -> {
                viewModelScope.launch {
                    hogwartsStaff.value = useCases.getAllStaff()
                }
            }
            is CoreEvents.GetCharacter -> {
                viewModelScope.launch {
                    selectedCharacter.value = useCases.getCharacter(
                        id = event.id
                    )
                }
            }
            is CoreEvents.GetSpells -> {
                viewModelScope.launch {
                    hogwartsSpells.value = useCases.getSpells()
                }
            }
            is CoreEvents.GetStudents -> {
                viewModelScope.launch {
                    hogwartsStudents.value = useCases.getStudents()
                }
            }
        }
    }
}