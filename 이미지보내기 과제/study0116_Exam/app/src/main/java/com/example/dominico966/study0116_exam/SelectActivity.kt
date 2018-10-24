package com.example.dominico966.study0116_exam

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import okhttp3.*
import org.json.JSONArray
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

class SelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        OkHttpLibSelectPartTask().start()
    }

    inner class OkHttpLibSelectPartTask : Thread() {

        override fun run() {
            super.run()

            val url = "http:172.16.97.82:8080/myWebServer/dbSelectBlob.jsp"
            val request: Request = Request.Builder()
                    .url(url)
                    .get()
                    .build()

            val client = OkHttpClient()

            // for response
            val callback = object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                }

                override fun onResponse(call: Call?, response: Response?) {
                    val data = response?.body()?.string()?.trim()
                    val listResult = findViewById<ListView>(R.id.listView_result)
                    val adapter = ImageListAdapter()

                    val jsonArr = JSONArray(data)
                    (0 until jsonArr.length())
                            .map { jsonArr.getJSONObject(it) }
                            .forEach {
                                val name = it.getString("filename")
                                Log.d("test name", name)
                                val blobData = it.getString("part_data")
                                val decodedData = Base64.decode(blobData, 0)
                                val decodedBitmap = BitmapFactory.decodeByteArray(decodedData, 0, decodedData.size)

                                // Image Compress
                                val byteArrayOutputStream = ByteArrayOutputStream()
                                decodedBitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)

                                val compressedArray = byteArrayOutputStream.toByteArray()
                                val compressedBitmap = BitmapFactory.decodeByteArray(compressedArray, 0, compressedArray.size)

                                adapter.addItem(name, compressedBitmap)
                            }

                    this@SelectActivity.runOnUiThread {
                        listResult.adapter = adapter
                    }
                }

            }

            client.newCall(request).enqueue(callback)
        }

    }

    inner class ImageListAdapter : BaseAdapter() {
        private val nameVector = Vector<String>()
        private val imageMap = HashMap<String, Bitmap>()

        fun addItem(name: String, bitmap: Bitmap) {
            nameVector.add(name)
            imageMap.put(name, bitmap)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = convertView
            when (view) {
                null -> view = this@SelectActivity.layoutInflater.inflate(R.layout.list_item, parent, false)
            }

            val imageView = view!!.findViewById<ImageView>(R.id.imageView_result_img)
            val textView = view.findViewById<TextView>(R.id.textView_result_name)

            val name = nameVector[position]
            val img = imageMap[name]
            textView.text = name
            imageView.setImageBitmap(img)

            return view
        }

        override fun getItem(position: Int): Bitmap? {
            return imageMap[nameVector[position]]
        }

        override fun getItemId(position: Int): Long {
            return nameVector[position].hashCode().toLong()
        }

        override fun getCount(): Int {
            return nameVector.size
        }

    }

}
