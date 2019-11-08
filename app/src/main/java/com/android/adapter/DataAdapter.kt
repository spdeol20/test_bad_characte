package com.android.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.models.DataModel
import com.android.test.R
import com.android.test.SearchActivity
import com.android.test.UserDetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item.view.*
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation as makeSceneTransitionAnimation1

class DataAdapter(val con: Context, val data: List<DataModel>?) : RecyclerView.Adapter<DataAdapter.myHolder>() {

    var requestOptions : RequestOptions = RequestOptions()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        return myHolder(LayoutInflater.from(con).inflate(R.layout.item,parent,false))
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        requestOptions.transform(CenterCrop(), RoundedCorners(20))
     holder.itemView.tvName.text = data?.get(position)?.name
        Glide.with(con).load(data?.get(position)?.img)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply (requestOptions)
            .into(holder.itemView.imgUser);
        Log.e("","appearnce"+data?.get(position)?.appearance)

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(view: View?) {
//                Toast.makeText(con,data?.get(position)?.name,Toast.LENGTH_SHORT);

                val imgContainerView = holder.itemView.imgUser
                val intent = Intent(con, UserDetailActivity::class.java)

                intent.putExtra("data",data?.get(position))
                val options = makeSceneTransitionAnimation1( con as Activity, imgContainerView, con.getString(R.string.imgTranstition))
                con.startActivity(intent, options.toBundle())
            }

        })
       /* holder.itemView.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                Toast.makeText(con,data?.get(position)?.name,Toast.LENGTH_SHORT).show();
                }
                MotionEvent.ACTION_UP -> {
                }


                else -> {}
            }
        return@setOnTouchListener true}*/

    }

    class myHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}

