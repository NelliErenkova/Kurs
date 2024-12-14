package com.example.fullbuddy.ui.auth

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fullbuddy.R

class MaintenanceExercisesFragment : Fragment() {

    private lateinit var backArrowTextView: TextView
    private lateinit var finishButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maintenance_exercises, container, false)

        backArrowTextView = view.findViewById(R.id.tv_back_arrow_maintenance_exercises)
        finishButton = view.findViewById(R.id.btn_finish_maintenance)

        backArrowTextView.setOnClickListener {
            findNavController().navigateUp()
        }

        finishButton.setOnClickListener {
            findNavController().navigate(R.id.action_maintenanceExercisesFragment_to_finishFragment)
        }

        // Инициализация каждого VideoView с его соответствующим видео
        setupVideoView(view.findViewById(R.id.videoView21), "podemnog")
        setupVideoView(view.findViewById(R.id.videoView22), "skruchivania")
        setupVideoView(view.findViewById(R.id.videoView23), "skalolaz")
        setupVideoView(view.findViewById(R.id.videoView24), "velosiped")
        setupVideoView(view.findViewById(R.id.videoView25), "podemnog")
        setupVideoView(view.findViewById(R.id.videoView26), "skruchivania")

        return view
    }

    private fun setupVideoView(videoView: VideoView, videoName: String) {
        val videoUri = Uri.parse("android.resource://${requireContext().packageName}/raw/$videoName")
        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            videoView.start()
        }
    }
}
