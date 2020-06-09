package com.ransoft.permission.ui.main

import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var mainListener: MainListener?= null

    fun onRequestPermissionClick(view: View) {
        mainListener?.onRequest()
    }
}