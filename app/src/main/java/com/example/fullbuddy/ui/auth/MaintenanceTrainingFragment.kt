package com.example.fullbuddy.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fullbuddy.R

class MaintenanceTrainingFragment : Fragment() {

    private lateinit var backArrowTextView: TextView
    private lateinit var startWorkoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maintenance_training, container, false)

        backArrowTextView = view.findViewById(R.id.tv_back_arrow_maintenance)
        startWorkoutButton = view.findViewById(R.id.btn_start_maintenance_workout)

        backArrowTextView.setOnClickListener {
            findNavController().navigateUp()
        }

        startWorkoutButton.setOnClickListener {
            // Переходим на экран упражнений для поддержания формы
            findNavController().navigate(R.id.action_maintenanceTrainingFragment_to_maintenanceExercisesFragment)
        }

        return view
    }
}
