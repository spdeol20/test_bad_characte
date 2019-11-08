package com.android.test

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.adapter.ChipAdapter
import com.android.models.DataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_user_detail.*
import android.util.DisplayMetrics
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.RelativeLayout.LayoutParams as LayoutParams1


class UserDetailActivity : AppCompatActivity() {
    var data: DataModel? = DataModel()
    private lateinit var occlayoutManage: RecyclerView.LayoutManager;
    private lateinit var applayoutManage: RecyclerView.LayoutManager;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        data = intent.getParcelableExtra("data")

        setSupportActionBar(toolbar)
        toolbar?.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_back)
        toolbar?.setNavigationOnClickListener { onBackPressed() }
        Glide.with(this).load(data?.img)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgUser);
        collapsingToolabr?.title = data?.name
        collapsingToolabr?.setExpandedTitleTextAppearance(R.style.AppBarCollapsed)
        collapsingToolabr?.setCollapsedTitleTextAppearance(R.style.AppBarExpanded)
        collapsingToolabr?.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary))

//        occlayoutManage = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        occlayoutManage = GridLayoutManager(this, 3)
        applayoutManage = GridLayoutManager(this, 3)
        occRecycleview.layoutManager = occlayoutManage;
        appRecycleview.layoutManager = applayoutManage;

        occRecycleview.adapter = ChipAdapter(this, data?.occupation, null)
        appRecycleview.adapter = ChipAdapter(this, null, data?.appearance)
        txtNick.text = data?.nickname
        txtStatus.text = data?.status

        imgUser.setOnClickListener {
//            showImage()
        }
    }

    fun showImage() {
        val builder = Dialog(this,android.R.style.Theme_Light)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.getWindow()?.setBackgroundDrawable(
            ColorDrawable(android.graphics.Color.TRANSPARENT)
        )
        builder.setOnDismissListener(DialogInterface.OnDismissListener {
            //nothing;
            builder.dismiss()
        })

        val imageView = ImageView(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.transitionName = getString(R.string.imgTranstition)
        }
        Glide.with(this).load(data?.img)
            .centerCrop()
            .into(imageView);
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height : Int   = displayMetrics.heightPixels
        val width: Int  = displayMetrics.widthPixels
        val parm = RelativeLayout.LayoutParams((width * 0.80).toInt(), (height * 0.80).toInt())
        builder.addContentView(
            imageView, parm
        )
        builder.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition()
        } else {
            finish()
        }
    }
}
