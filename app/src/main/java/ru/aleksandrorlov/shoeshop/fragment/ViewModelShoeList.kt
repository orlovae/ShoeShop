package ru.aleksandrorlov.shoeshop.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aleksandrorlov.shoeshop.config.FakeData
import ru.aleksandrorlov.shoeshop.model.Shoe

class ViewModelShoeList : ViewModel()  {
    private val shoesMutableData = MutableLiveData<List<Shoe>>()
    private val errorMessageMutableLiveData = MutableLiveData<String>()

    val shoes: LiveData<List<Shoe>> = shoesMutableData
    val errorMessage: LiveData<String> = errorMessageMutableLiveData

    init {
        loadShoes()
    }

    private fun loadShoes() {
        shoesMutableData.value = FakeData.getAll()
    }

    override fun onCleared() {
        super.onCleared()
    }
}