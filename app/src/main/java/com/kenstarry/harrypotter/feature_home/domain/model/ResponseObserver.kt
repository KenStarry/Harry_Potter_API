package com.kenstarry.harrypotter.feature_home.domain.model

import androidx.lifecycle.Observer
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import retrofit2.Response

class ResponseObserver(
    val onValueChanged: (value: Response<List<CharacterModel>>) -> Unit
) : Observer<Response<List<CharacterModel>>> {

    override fun onChanged(value: Response<List<CharacterModel>>) {
        //  do something
        onValueChanged(value)
    }
}