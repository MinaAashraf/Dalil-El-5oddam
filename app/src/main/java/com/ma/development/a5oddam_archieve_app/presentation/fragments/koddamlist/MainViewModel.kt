package com.ma.development.a5oddam_archieve_app.presentation.fragments.koddamlist

import android.util.Log
import androidx.lifecycle.*
import androidx.room.CoroutinesRoom.Companion.execute
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem
import com.ma.development.a5oddam_archieve_app.domain.usecases.GetAllKhoddamUseCase
import com.ma.development.a5oddam_archieve_app.domain.usecases.GetBirthDayKhoddamUseCase
import com.ma.development.a5oddam_archieve_app.domain.usecases.RefreshDataUseCase
import com.ma.development.a5oddam_archieve_app.domain.usecases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllKhoddamUseCase: GetAllKhoddamUseCase,
    private val searchUseCase: SearchUseCase,
    private val getBirthDayKhoddamUseCase: GetBirthDayKhoddamUseCase,
    refreshDataUseCase: RefreshDataUseCase
) : ViewModel() {

    private val filterType = MutableLiveData(FilterType.ALL)
    private var searchName = ""
    var firstTime = true

    val khoddamList: LiveData<List<Khadem>> = Transformations.switchMap(filterType) {
        return@switchMap when (filterType.value) {
            FilterType.SEARCH -> searchUseCase.execute(searchName)
            else -> getAllKhoddamUseCase.execute()
        }
    }

    val birthDayKhoddam: LiveData<List<Khadem>> = getBirthDayKhoddamUseCase.execute()


    init {
        viewModelScope.launch {
            refreshDataUseCase.execute()
        }
    }

    fun setFilterType(type: FilterType, search:String = "") {
        searchName = search
        filterType.value = type
    }


}