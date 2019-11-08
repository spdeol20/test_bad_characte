package com.android.test

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.adapter.DataAdapter
import com.android.models.DataModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.etSearch
import kotlinx.android.synthetic.main.activity_search.icCancel
import kotlinx.android.synthetic.main.activity_search.rvList

class SearchActivity : AppCompatActivity() {

    private val searhData: ArrayList<DataModel> = ArrayList()
    private lateinit var allData: List<DataModel>
    private lateinit var layoutManage: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        layoutManage = GridLayoutManager(this@SearchActivity, 2)
        rvList.layoutManager = layoutManage

        Log.e("filter", "search list " + DataModel.getInstance().size)
        allData = DataModel.getInstance()
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                searchList(p0.toString().toLowerCase())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        icCancel.setOnClickListener(View.OnClickListener {
            if (etSearch.text!!.length>0) {
                etSearch.text!!.clear()
            } else {
                finish()
            }
        })


    }

    fun searchList(str: String) {
        searhData.clear()
        if (str.length > 0) {
            for (i in 0 until allData.size) {
                if (allData.get(i).name.toLowerCase().contains(str) || allData.get(i).nickname.toLowerCase().contains(str)) {
                    searhData.add(allData.get(i))
                }

            }
        }

        rvList.adapter = DataAdapter(this@SearchActivity, searhData)
        Log.e("filter", "search list typing " + str)
    }
}
