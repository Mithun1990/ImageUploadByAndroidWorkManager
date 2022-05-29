package com.naim.imageuploadbyandroidworkmanager.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.naim.imageuploadbyandroidworkmanager.databinding.ActivityMainBinding
import com.naim.imageuploadbyandroidworkmanager.viewmodel.ImageUploadViewModel
import com.naim.imageuploadbyandroidworkmanager.workers.factory.ImageUploadWorkerFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ImageUploadViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var imageUploadWorkerFactory: ImageUploadWorkerFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.uploadImage()
        }

        binding.button3.setOnClickListener {
            viewModel.executePeriodicWorker()
        }

        binding.button2.setOnClickListener {
            viewModel.stopPeriodicWorkRequest()
        }
    }
}