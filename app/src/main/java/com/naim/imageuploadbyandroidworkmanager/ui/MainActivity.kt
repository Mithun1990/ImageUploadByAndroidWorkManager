package com.naim.imageuploadbyandroidworkmanager.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.naim.imageuploadbyandroidworkmanager.R
import com.naim.imageuploadbyandroidworkmanager.viewmodel.ImageUploadViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ImageUploadViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.uploadImage()
    }
}