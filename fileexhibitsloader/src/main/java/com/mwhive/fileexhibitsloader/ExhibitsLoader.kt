package com.mwhive.fileexhibitsloader

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import com.mwhive.model.Exhibit
import com.mwhive.model.IExhibitsLoader
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi


class ExhibitsLoader (private val context: Context) : IExhibitsLoader{

    override fun getExhibitList() : List<Exhibit>? {


        val rawJsonString = context.resources.assets.open("stub.json")
                .bufferedReader().use { it.readText() }

        val firstSqrBracket = rawJsonString.indexOf('[', 0, true)
        val jsonArray = rawJsonString.substring(firstSqrBracket, rawJsonString.lastIndex)

        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        val exhibitAdapter = moshi.adapter<Array<Exhibit>>(Array<Exhibit>::class.java)

        return exhibitAdapter.fromJson(jsonArray)!!.toList()
    }

}