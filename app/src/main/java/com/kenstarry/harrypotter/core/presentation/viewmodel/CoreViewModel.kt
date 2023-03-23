package com.kenstarry.harrypotter.core.presentation.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.harrypotter.core.domain.model.BottomSheetEvents
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.domain.model.CoreEvents
import com.kenstarry.harrypotter.core.domain.model.Spell
import com.kenstarry.harrypotter.core.domain.use_case.HarryPotterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val useCases: HarryPotterUseCases
) : ViewModel() {

    val harryPotterCharacters = MutableLiveData<Response<List<CharacterModel>>>()
    val charactersInHouse = MutableLiveData<Response<List<CharacterModel>>>()
    val selectedCharacter = MutableLiveData<Response<CharacterModel>>()
    val hogwartsStudents = MutableLiveData<Response<List<CharacterModel>>>()
    val hogwartsStaff = MutableLiveData<Response<List<CharacterModel>>>()
    val hogwartsSpells = MutableLiveData<Response<List<Spell>>>()

    //  Bottomsheet
    private val _bottomSheetContent = mutableStateOf("")
    val bottomSheetContent: State<String> = _bottomSheetContent

    private val _bottomSheetData = mutableStateOf<Any?>(null)
    var bottomSheetData: State<Any?> = _bottomSheetData

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

            is CoreEvents.GetCharactersInHouse -> {
                viewModelScope.launch {
                    charactersInHouse.value = useCases.getCharactersInHouse(
                        houseName = event.houseName
                    )
                }
            }

            is CoreEvents.SearchForCharacters -> {
                viewModelScope.launch {
                    //  search for characters in the list of characters
                    val allCharacters = event.allCharacters.toMutableList()
                    val filteredList = mutableListOf<CharacterModel>()

                    allCharacters.forEach { character ->
                        //  compare the character with the query to see if they exist
                        if (character.name.lowercase(Locale.ROOT).contains(event.query) ||
                                character.house.lowercase(Locale.ROOT).contains(event.query)) {
                            filteredList.add(character)
                        }
                    }

                    event.filteredCharacters(filteredList)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun onBottomSheetEvent(event: BottomSheetEvents<*>) {
        when (event) {
            is BottomSheetEvents.OpenBottomSheet<*> -> {

                event.scope.launch {
                    event.state.animateTo(ModalBottomSheetValue.Expanded)
                }

                viewModelScope.launch {
                    _bottomSheetContent.value = event.bottomSheetType
                    _bottomSheetData.value = event.bottomSheetData
                }

            }

            is BottomSheetEvents.CloseBottomSheet -> {
                event.scope.launch {
                    event.state.animateTo(ModalBottomSheetValue.Hidden)
                }
            }
        }
    }
}