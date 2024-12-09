package com.arlib.task.ui.screens.home


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlib.task.domain.models.Drug
import com.arlib.task.domain.usecase.GetDrugsUseCase
import com.arlib.task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugsViewModel @Inject constructor(
    private val getDrugsUseCase: GetDrugsUseCase
) : ViewModel() {

    var drug: Drug? = Drug(name = "name")

    init {
        getDrugs()
    }

    private val _drugs =
        MutableStateFlow<Resource<List<Drug>>>(Resource.Loading)
    val drugsFlow: Flow<Resource<List<Drug>>> = _drugs

    private fun getDrugs() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val drugs = getDrugsUseCase()
                _drugs.value = Resource.Success(drugs.first())
                Log.d("test", (_drugs.value as Resource.Success<List<Drug>>).data.toString())
            } catch (e: Exception) {
                _drugs.value = Resource.Error(e)
            }
        }
    }

}