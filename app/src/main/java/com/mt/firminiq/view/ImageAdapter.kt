package com.mt.firminiq.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mt.firminiq.R
import com.mt.firminiq.model.Images

class ImageAdapter(private val OnClickListener: (Images) -> Unit) :
    ListAdapter<Images, ImagesViewHolder>(ImagesDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_image, parent, false)
        return ImagesViewHolder(view, OnClickListener )
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.item = getItem(position)
    }
}

class ImagesViewHolder(private val view: View, private val onClickListener: (Images) -> Unit):
        RecyclerView.ViewHolder(view) {
            var item: Images?= null
                set(value) {
                    value?.let { newValue ->
                        field = newValue
                        view.setOnClickListener {onClickListener (newValue)}
                        view.findViewById<ImageView>(R.id.imageView).setImageResource(newValue.image)
                        view.findViewById<TextView>(R.id.textView).text = "${newValue.text}"
                    }
                }
        }

class ImagesDiffCallback : DiffUtil.ItemCallback<Images>() {

    override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
        return oldItem == newItem
    }
}