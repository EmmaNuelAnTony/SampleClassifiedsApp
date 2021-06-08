package com.emmanuel.sampleclassifiedsapp.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.emmanuel.sampleclassifiedsapp.BaseActivity
import com.emmanuel.sampleclassifiedsapp.R
import com.emmanuel.sampleclassifiedsapp.activities.modal.Data
import com.emmanuel.sampleclassifiedsapp.activities.modal.ItemListModal
import com.emmanuel.sampleclassifiedsapp.adapters.ItemListAdapter
import com.emmanuel.sampleclassifiedsapp.network.ApiUrls
import com.emmanuel.sampleclassifiedsapp.utils.Constants
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : BaseActivity(), View.OnClickListener, ItemListAdapter.OnClickItem {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        getItems()
        clickListeners()
    }

    override fun getResponse(apiType: String, respopnse: JsonObject) {

        if (apiType.equals(Constants.BASE_URL, true)) {
            val gson = Gson()
            val itemList = gson.fromJson(respopnse, ItemListModal::class.java)
            println("DATA :" + itemList.data)
            setAdapter(itemList.data)
        }
    }

    private fun clickListeners() {
        backBtn.setOnClickListener(this)
    }

    private fun setAdapter(data: ArrayList<Data>) {

        if (data.size == 0) {
            llNoData.visibility = View.VISIBLE
            rvItemList.visibility = View.GONE
        } else {
            llNoData.visibility = View.GONE
            rvItemList.visibility = View.VISIBLE
        }

        rvItemList.layoutManager = LinearLayoutManager(this)
        rvItemList.adapter = ItemListAdapter(this@ListActivity, data, this)

    }

    private fun getItems() {
        hitGetApiWithToken(
            Constants.BASE_URL,
            true,
            ApiUrls.BASE_URL

        )

    }

    override fun onClick(v: View?) {
        if (v == backBtn) {
            finishActivity()
        }
    }

    override fun onclick(itemID: String, price: String, image: String,name:String) {
        val bundle = Bundle()
        bundle.putString(Constants.UID, itemID)
        bundle.putString(Constants.PRICE, price)
        bundle.putString(Constants.IMAGE, image)
        bundle.putString(Constants.NAME, name)
        navigateToNextScreen(this@ListActivity, ItemDetailView::class.java, bundle, false)
    }
}