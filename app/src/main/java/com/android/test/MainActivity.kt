package com.android.test

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.adapter.DataAdapter
import com.android.models.DataModel
import com.android.retorfit.Retro
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.etSearch
import kotlinx.android.synthetic.main.activity_main.icCancel
import kotlinx.android.synthetic.main.activity_main.rvList
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item.view.*
import retrofit2.Call
import retrofit2.Response
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: DataAdapter
    private lateinit var allData: List<DataModel>
    private   val filterData: ArrayList<DataModel> = ArrayList()
    private lateinit var layoutManage: RecyclerView.LayoutManager;
    var lightStatus=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Breaking Bad characters")
        layoutManage = GridLayoutManager(this@MainActivity, 2)
        rvList.layoutManager = layoutManage;

        val call=  Retro.calApi.callApi()
        call.enqueue( object :   retrofit2.Callback<List<DataModel>> {
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                txtLoading.text = getString(R.string.failure)
            }

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                if (response.isSuccessful && !response.body().isNullOrEmpty() ){
                    allData = response.body()!!
                    DataModel.setInstance(response.body()!!)
                    txtLoading.visibility = View.GONE
                    rvList.adapter = DataAdapter(this@MainActivity,allData)
                }
                else{
                    txtLoading.text = getString(R.string.failure)
                }
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.getItemId()) {
            R.id.menu_filter -> {
//                Toast.makeText(this@MainActivity, "filter", Toast.LENGTH_SHORT).show()

                val optionOutdoor = arrayOf("All", "Season 1", "Season 2", "Season 3", "Season 4", "Season 5")
                val arrayAdapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_single_choice,
                    optionOutdoor)
                val builder = AlertDialog.Builder(this@MainActivity, R.style.MyAlertDialogStyle)
                builder.setTitle(getString(R.string.filter))
                builder.setSingleChoiceItems(arrayAdapter, lightStatus,
                    DialogInterface.OnClickListener { dialog, pos ->
                        lightStatus = pos;
                        filterList()
                        dialog.dismiss()
                    })
                builder.show()
                true
            }
            R.id.menu_search -> {
//                Toast.makeText(this@MainActivity, "Search", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_down,R.anim.stay)

               /* val view  = supportActionBar?.customView?.findViewById<View>(R.id.menu_search)
                val intent = Intent(this, SearchActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    view!!,
                    getString(R.string.imgTranstition)
                )
                startActivity(intent, options.toBundle())*/
//                llSearch.visibility = View.VISIBLE
//                getSupportActionBar()!!.hide();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun filterList(){
        if (lightStatus ==0){
            rvList.adapter = DataAdapter(this@MainActivity,allData)
        }
        else{
            filterData.clear()
            for (i in 0 until allData.size){
                for (j in 0 until allData.get(i).appearance.size){
                    if (allData.get(i).appearance.get(j) == lightStatus){
                        filterData.add(allData.get(i))
                        break
                    }
                }

            }
                Toast.makeText(this@MainActivity, "Result found "+filterData.size, Toast.LENGTH_SHORT).show()
            adapter = DataAdapter(this@MainActivity,filterData)
            rvList.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}


