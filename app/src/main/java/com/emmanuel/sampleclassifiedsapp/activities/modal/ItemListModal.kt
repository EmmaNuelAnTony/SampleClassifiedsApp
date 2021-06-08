package com.emmanuel.sampleclassifiedsapp.activities.modal

import com.google.gson.annotations.SerializedName

data class ItemListModal(

    @SerializedName("results") val data: ArrayList<Data>
)

data class Data(

    @SerializedName("created_at") val created_at: String,
    @SerializedName("price") val price: String,
    @SerializedName("name") val name: String,
    @SerializedName("uid") val uid: String,
    @SerializedName("image_ids") val image_ids: ArrayList<String>,
    @SerializedName("image_urls") val image_urls: ArrayList<String>,
    @SerializedName("image_urls_thumbnails") val image_urls_thumbnails: ArrayList<String>
)
