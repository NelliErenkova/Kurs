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

class WeightLossExercisesFragment : Fragment() {

    private lateinit var backArrowTextView: TextView
    private lateinit var finishButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weight_loss_exercises, container, false)

        backArrowTextView = view.findViewById(R.id.tv_back_arrow_weight_loss_exercises)
        finishButton = view.findViewById(R.id.btn_finish_weight_loss)

        backArrowTextView.setOnClickListener {
            findNavController().navigateUp()
        }

        finishButton.setOnClickListener {
            findNavController().navigate(R.id.action_weightLossExercisesFragment_to_finishFragment)
        }

        // Инициализация каждого VideoView с его видео
        setupVideoView(view.findViewById(R.id.videoView1), "berpi")
        setupVideoView(view.findViewById(R.id.videoView2), "prisedsprigok")
        setupVideoView(view.findViewById(R.id.videoView3), "prised")
        setupVideoView(view.findViewById(R.id.videoView4), "skalolaz")
        setupVideoView(view.findViewById(R.id.videoView5), "berpi")
        setupVideoView(view.findViewById(R.id.videoView6), "prisedsprigok")

        return view
    }

    private fun setupVideoView(videoView: VideoView, videoName: String) {
        val videoUri = Uri.parse("android.resource://${requireContext().packageName}/raw/$videoName")
        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            videoView.start()
        }

        finishButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("trainingName", "Похудение")
            }
            findNavController().navigate(R.id.action_weightLossExercisesFragment_to_finishFragment, bundle)
        }

    }
}
