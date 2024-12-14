package com.example.fullbuddy.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fullbuddy.R

class PlanSelectionFragment : Fragment() {

    private lateinit var backArrowTextView: TextView
    private lateinit var recommendationTextView: TextView
    private lateinit var maintenancePlanTextView: TextView
    private lateinit var weightLossPlanTextView: TextView
    private lateinit var intensivePlanTextView: TextView

    private val args: PlanSelectionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_plan_selection, container, false)

        backArrowTextView = view.findViewById(R.id.tv_back_arrow)
        recommendationTextView = view.findViewById(R.id.tv_recommendation)
        maintenancePlanTextView = view.findViewById(R.id.tv_maintenance_plan)
        weightLossPlanTextView = view.findViewById(R.id.tv_weight_loss_plan)
        intensivePlanTextView = view.findViewById(R.id.tv_intensive_plan)

        val bmi = args.bmi
        val recommendation = when {
            bmi > 18.5 && bmi <= 25 -> "Поддержание формы"
            bmi > 25 -> "Похудение"
            else -> "Интенсивные тренировки"
        }

        recommendationTextView.text = recommendation

        backArrowTextView.setOnClickListener {
            findNavController().navigateUp()
        }

        maintenancePlanTextView.setOnClickListener {
            findNavController().navigate(R.id.action_planSelectionFragment_to_maintenanceTrainingFragment)
        }

        weightLossPlanTextView.setOnClickListener {
            findNavController().navigate(R.id.action_planSelectionFragment_to_weightLossTrainingFragment)
        }

        intensivePlanTextView.setOnClickListener {
            findNavController().navigate(R.id.action_planSelectionFragment_to_intensiveTrainingFragment)
        }

        recommendationTextView.setOnClickListener {
            when (recommendation) {
                "Поддержание формы" -> findNavController().navigate(R.id.action_planSelectionFragment_to_maintenanceTrainingFragment)
                "Похудение" -> findNavController().navigate(R.id.action_planSelectionFragment_to_weightLossTrainingFragment)
                "Интенсивные тренировки" -> findNavController().navigate(R.id.action_planSelectionFragment_to_intensiveTrainingFragment)
            }
        }

        return view
    }
}
