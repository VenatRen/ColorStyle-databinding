package com.example.colorview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random
import com.example.colorview.databinding.ColorViewBinding


class ColorAdapter(val context: Context, val items:MutableList<Color>):RecyclerView.Adapter<ColorAdapter.ColorAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapterHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.color_view, null)
        val binding = ColorViewBinding.inflate(LayoutInflater.from(context),parent, false)
        val vh = ColorAdapterHolder(binding)
        return vh
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ColorAdapterHolder, position: Int) {

        holder.binding.color = items[position]
        holder.binding.root.setOnClickListener { _->
                        items.removeAt(position)
            notifyDataSetChanged()
            }
//        holder.text.text = items[position].color
//        holder.view.setBackgroundColor(items[position].number)
//        holder.view.setOnClickListener { _->
//            items.removeAt(position)
//            notifyDataSetChanged()
//        }
    }

    inner class ColorAdapterHolder (val binding: ColorViewBinding ):RecyclerView.ViewHolder(binding.root){
//        val text = view.findViewById<TextView>(R.id.colorText)
//        val view = view.findViewById<View>(R.id.view)
    }


}



data class Color (val number:Int, val color:String = number.toString())


class ColorViewModel():ViewModel(){
    val itemsList = MutableList(25){
        Color(Random().nextInt())
    }
}


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val vm = ViewModelProviders.of(this).get(ColorViewModel::class.java)


        val adapterViewColor = ColorAdapter(this, vm.itemsList)
        colorView.adapter = adapterViewColor
        colorView.layoutManager = LinearLayoutManager(this)

    }
}
