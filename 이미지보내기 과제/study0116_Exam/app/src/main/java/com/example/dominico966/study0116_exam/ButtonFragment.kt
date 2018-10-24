package com.example.dominico966.study0116_exam

import android.app.Activity
import android.app.Fragment
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

/**
 * Created by dominico966 on 18. 1. 16.
 */
class ButtonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_button,container,false)

        view.findViewById<Button>(R.id.button).setOnClickListener{
            val global = activity.application as GlobalImages

            val bitmap = BitmapFactory.decodeFile(global.realPath)
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos)

            val compressed = baos.toByteArray()
            OkHttpLibInsertPartTask(activity,compressed).start()
        }

        return view
    }

    class OkHttpLibInsertPartTask : Thread {
        private var realPath: String? = null
        private var context: Activity? = null
        private var bytes: ByteArray?= null

        constructor(context: Activity, realPath: String) {
            this.context = context
            this.realPath = realPath
        }

        constructor(context: Activity, bytes: ByteArray) {
            this.context = context
            this.bytes = bytes
        }

        override fun run() {
            super.run()

            val url = "http:172.16.97.82:8080/myWebServer/dbInsertPart.jsp"

            val name = context!!.findViewById<EditText>(R.id.editText_name)

            // example POST
            val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("fname", "${name.text}")

            realPath?.let {
                requestBody.addFormDataPart(
                        "sfile",
                        "${name.text}",
                        RequestBody.create(MultipartBody.FORM, File(realPath))
                )
            }

            bytes?.let {
                requestBody.addFormDataPart(
                        "sfile",
                        "${name.text}",
                        RequestBody.create(MultipartBody.FORM, bytes)
                )
            }

            val request: Request = Request.Builder()
                    .url(url)
                    .post(requestBody.build())
                    .build()

            val client = OkHttpClient()

            // for response
            val callback = object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    context!!.runOnUiThread {
                        Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call?, response: Response?) {
                    context!!.runOnUiThread {
                        Toast.makeText(context, "성공", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            client.newCall(request).enqueue(callback)
        }

    }
}