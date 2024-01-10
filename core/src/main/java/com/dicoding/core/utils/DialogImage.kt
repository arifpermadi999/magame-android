package com.dicoding.core.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.dicoding.core.R

class DialogImage (context: Context,inflater: LayoutInflater,urlImage: String){
    private var dialog : AlertDialog

    init{
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val viewtemplelayout: View = inflater.inflate(R.layout.dialog_image, null)
        builder.setView(viewtemplelayout)
        dialog = builder.create()
        val image : ImageView = viewtemplelayout.findViewById(R.id.image)
        val btnClose : ImageView = viewtemplelayout.findViewById(R.id.btn_close)
        Glide.with(context).load(urlImage).placeholder(circularProgressDrawable).into(image)
        btnClose.setOnClickListener{
            dialog.dismiss()
        }
    }
    fun show(){
        dialog.show()
    }
}