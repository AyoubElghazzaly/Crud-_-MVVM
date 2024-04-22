package com.operation.crudmvvm


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.copy


class EmpAdapter( private var context: Context , var viewModel : EmpViewModel) :
    RecyclerView.Adapter<EmpAdapter.MyViewHplder>() {


    var list:MutableList<EmployeeMD> = mutableListOf()

    inner class MyViewHplder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val char_emp: TextView = itemView.findViewById(R.id.char_item)
        val name_emp: TextView = itemView.findViewById(R.id.name_item)
        val job_emp: TextView = itemView.findViewById(R.id.job_item)
        val experience_emp: TextView = itemView.findViewById(R.id.ansexperience_item)

        init {

            itemView.setOnLongClickListener {
                val myposition = adapterPosition
                val newEmp = list[myposition]
                val newExperionce : Double = newEmp.experionce + 1.0

                newEmp.experionce = newExperionce

                viewModel.updateEmployee(newEmp , myposition)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): MyViewHplder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_employee,
            parent, false
        )


        return MyViewHplder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHplder, position: Int) {
        val currentItem = list[position]
        holder.name_emp.text = currentItem.name
        holder.char_emp.text = currentItem.name[0].toString().uppercase()
        holder.job_emp.text = currentItem.job
        holder.experience_emp.text = "${currentItem.experionce} ans"
        holder.itemView.setOnClickListener {

            viewModel.deleteEmployee(position)

        }


    }


    override fun getItemCount(): Int {
        return list.size
    }
    fun updateList(newEmployeesList : MutableList<EmployeeMD>){
        val itemDiffCallBack = ItemDiffCallBack(list,newEmployeesList)
        val diffResult = DiffUtil.calculateDiff(itemDiffCallBack)

        list = newEmployeesList.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

}