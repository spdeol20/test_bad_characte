package com.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.test.R
import kotlinx.android.synthetic.main.chip_item.view.*

class ChipAdapter(val con: Context, val data: List<String>?, val appe: List<Int>?) : RecyclerView.Adapter<DataAdapter.myHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.myHolder {
        return DataAdapter.myHolder(LayoutInflater.from(con).inflate(R.layout.chip_item, parent, false))
    }

    override fun getItemCount(): Int {
        if (data == null) {
            return appe!!.size
        }
        return data!!.size
    }

    override fun onBindViewHolder(holder: DataAdapter.myHolder, position: Int) {
      if (data == null) {
          holder.itemView.icChipName.text ="Season "+ appe?.get(position).toString()
      }else{
          holder.itemView.icChipName.text = data.get(position)
      }
    }


    class myHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}



