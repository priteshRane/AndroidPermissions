package com.ransoft.permission.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ransoft.permission.R
import com.ransoft.permission.databinding.ActivityMainBinding
import com.ransoft.permission.util.toast

const val PERMISSION_REQUEST_SEND_SMS = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        requestPermission()

        binding.btnRequestPermission.setOnClickListener {
            toast("Asking for permission again")
            requestPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_SEND_SMS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    toast("Permission is granted")
                } else {
                    toast("Permission is denied")
                }
                return
            }

            else -> {
                toast("Ignore all other request")
            }
        }
    }

    fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SEND_SMS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    toast("Permission is already granted, Perform action.")
                }
                shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS) -> {
                    toast("In an educational UI")
                }
                else -> {
                    requestPermissions(
                        arrayOf(Manifest.permission.SEND_SMS),
                        PERMISSION_REQUEST_SEND_SMS
                    )
                }
            }
        }
    }
}
