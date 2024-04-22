package com.operation.crudmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.operation.crudmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: EmpAdapter
    private lateinit var viewModel: EmpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // bindingView initialisation
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[EmpViewModel::class.java]


        adapter = EmpAdapter(this, viewModel)
        binding.rvEmployee.adapter = adapter

        // Get Employees
        binding.btnGetEmployee.setOnClickListener {
            viewModel.getEmployeesData()
        }

        viewModel.employeesData.observe(this) {
            adapter.updateList(it)
        }
        var counter = 0
        // Add new Employee
        binding.btnAddEmployee.setOnClickListener {
            val dialog = this.let { BottomSheetDialog(it) }
            val view = layoutInflater.inflate(R.layout.add_employee, null)
            val btnSave = view.findViewById<MaterialButton>(R.id.employee_add)

            btnSave.setOnClickListener {

                val input_name = view.findViewById<TextInputEditText>(R.id.name)
                val input_job = view.findViewById<TextInputEditText>(R.id.job)
                val input_experionce = view.findViewById<TextInputEditText>(R.id.experionce)
                val name = input_name.text.toString()
                val job = input_job.text.toString()
                val ex = input_experionce.text.toString()

                val experionce :Double = ex.toDouble()
                val modelAdedd = EmployeeMD(
                    name = name,
                    job = job,
                    experionce = experionce
                )
                viewModel.addEmployee(modelAdedd)
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()


        }
        viewModel.employeesData.observe(this) {
            adapter.updateList(it)
            binding.rvEmployee.adapter = adapter
        }

    }


}