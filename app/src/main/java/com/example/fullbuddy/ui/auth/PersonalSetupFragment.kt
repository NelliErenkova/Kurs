package com.example.fullbuddy.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fullbuddy.R

class PersonalSetupFragment : Fragment() {

    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var selectPlanButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_personal_setup, container, false)

        weightEditText = view.findViewById(R.id.et_weight)
        heightEditText = view.findViewById(R.id.et_height)
        selectPlanButton = view.findViewById(R.id.btn_select_plan)

        selectPlanButton.setOnClickListener {
            val weightStr = weightEditText.text.toString().trim()
            val heightStr = heightEditText.text.toString().trim()

            // Проверяем, что поля не пустые
            if (weightStr.isEmpty() || heightStr.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Преобразуем введенные значения в числа
            val weight = weightStr.toFloatOrNull()
            val height = heightStr.toFloatOrNull()

            // Проверяем корректность данных
            if (weight == null || height == null || height <= 0) {
                Toast.makeText(requireContext(), "Введите корректные данные", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Рассчитываем BMI: вес / (рост * рост)
            val bmi = weight / (height * height)

            // Передаем рассчитанное значение BMI через SafeArgs
            val action = PersonalSetupFragmentDirections.actionPersonalSetupFragmentToPlanSelectionFragment(bmi)
            findNavController().navigate(action)
        }

        return view
    }
}
