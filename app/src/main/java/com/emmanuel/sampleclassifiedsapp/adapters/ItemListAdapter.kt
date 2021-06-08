package com.emmanuel.sampleclassifiedsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emmanuel.sampleclassifiedsapp.R
import com.emmanuel.sampleclassifiedsapp.activities.modal.Data
import com.emmanuel.sampleclassifiedsapp.utils.Utility
import kotlinx.android.synthetic.main.item_list_row.view.*

class ItemListAdapter(
    var context: Context,
    var data: ArrayList<Data>,
    var onClickItem: OnClickItem
) :
    RecyclerView.Adapter<ItemListAdapter.Viewholder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Viewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_row, parent, false)
        return Viewholder(view)

    }

    inner class Viewholder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: Data) = with(itemView) {
            itemName.text = data.name
            val date = Utility.convertStringToDate(data.created_at)
            itemDate.text = Utility.formatDate(date)
            Utility.showImage(data.image_urls_thumbnails[0], itemImage)

            itemView.setOnClickListener {
                onClickItem.onclick(data.uid, data.price, data.image_urls[0], data.name)
            }
        }

    }

    interface OnClickItem {
        fun onclick(itemID: String, price: String, image: String, name: String)
    }

}