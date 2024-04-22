package com.operation.crudmvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmpViewModel():ViewModel() {
    val employeesData = MutableLiveData<MutableList<EmployeeMD>>()

    val items = mutableListOf(
        EmployeeMD(name = "Ayoub", job = "Mobile Developer" , experionce = 3.0),
        EmployeeMD(name = "Youssef", job = "Physique" , experionce = 3.0),
        EmployeeMD(name = "Zakaria", job = "Commercial" , experionce = 3.0),
        EmployeeMD(name = "Abdo", job = "Developer" , experionce = 3.0),
        EmployeeMD(name = "Ilyas", job = "Web Developer" , experionce = 3.0),
    )

    // les Operation de Crud insertion _ modification _ suppression _ read
    fun getEmployeesData(){
        employeesData.postValue(items)
    }

    fun deleteEmployee(position : Int){
        items.removeAt(position)
        employeesData.postValue(items)
    }

    fun addEmployee(employeeaded : EmployeeMD){
        items.add(employeeaded)
        employeesData.postValue(items)
    }
    fun updateEmployee(newemployee: EmployeeMD , position: Int){
        items[position] = newemployee
        employeesData.postValue(items)
    }
}









