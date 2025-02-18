package com.example.seisankanriapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _employeeCode = MutableStateFlow("")
    val employeeCode: StateFlow<String> = _employeeCode

    private val _lotNumber = MutableStateFlow("")
    val lotNumber: StateFlow<String> = _lotNumber

    fun setEmployeeCode(code: String) {
        _employeeCode.value = code
    }

    fun setLotNumber(lot: String) {
        _lotNumber.value = lot
    }
}