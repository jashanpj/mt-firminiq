package com.mt.firminiq.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mt.firminiq.R
import com.mt.firminiq.model.Images

class ImageGridViewModel(application: Application) : AndroidViewModel(application) {
    private val imagesMutList = MutableLiveData<List<Images>>()
    var filterData = MutableLiveData<List<Images>>()

    init {
        // sample data for naming the image.
        val names = listOf<String>(
            "Cat",
            "Lion",
            "Tiger",
            "Horse",
            "Fox",
            "Cat",
            "Lion",
            "Tiger",
            "Horse",
            "Fox",
            "Cat",
            "Lion",
            "Tiger",
            "Horse",
            "Fox",
            "Cat",
            "Lion",
            "Tiger",
            "Horse",
            "Fox"
        )
        val list = ArrayList<Images>()
        for (i in 1..20) {
            list.add(Images(R.drawable.ic_launcher_foreground, " ${names[i-1]} ${i}"))
        }
        imagesMutList.value = list
        filterData.value = list
    }

    fun search(text: String?) {
        val list = ArrayList<Images>()
        text?.let {
            imagesMutList.value?.forEach { dataItem ->
                if (dataItem.text.contains(text, true)
                ) {
                    list.add(dataItem)
                }
            }
            filterData.value = list
        }
    }
}