package com.kenstarry.harrypotter.feature_detail.domain.model

import androidx.lifecycle.Observer
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import retrofit2.Response

class CharacterObserver(
    val onValueChanged: (value: Response<CharacterModel>) -> Unit
) : Observer<Response<CharacterModel>> {

    override fun onChanged(value: Response<CharacterModel>) {
        //  do something
        onValueChanged(value)
    }
}