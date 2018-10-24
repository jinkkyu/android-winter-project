package com.example.dominico966.study0116_exam

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import javax.microedition.khronos.opengles.GL

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_item -> {
                val intent = Intent(this, SelectActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_img_select).setOnClickListener {
            when (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                PackageManager.PERMISSION_DENIED -> {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
                }

                PackageManager.PERMISSION_GRANTED -> {
                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = "image/*"
                    startActivityForResult(intent, IMAGE_REQUEST)
                }
                else -> return@setOnClickListener
            }
        }
    }

    private val IMAGE_REQUEST = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            0 -> {

            }

            IMAGE_REQUEST -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val uri = data?.data
                        val realPath = RealPathUtil.getRealPath(this, uri)

                        (application as GlobalImages).realPath = realPath
                    }
                }
            }
            else -> return
        }
    }

}
