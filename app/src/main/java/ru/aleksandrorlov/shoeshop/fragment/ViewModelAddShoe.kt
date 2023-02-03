package ru.aleksandrorlov.shoeshop.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aleksandrorlov.shoeshop.model.Shoe

class ViewModelAddShoe : ViewModel()  {
    private val shoeMutableData = MutableLiveData<Shoe>()
    private val errorMessageMutableLiveData = MutableLiveData<String>()

    val shoe: LiveData<Shoe> = shoeMutableData
    val errorMessage: LiveData<String> = errorMessageMutableLiveData


}