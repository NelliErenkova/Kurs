package com.example.fullbuddy.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fullbuddy.R

class FinishFragment : Fragment() {

    private lateinit var returnToMainButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finish, container, false)
        returnToMainButton = view.findViewById(R.id.btn_return_to_main)

        returnToMainButton.setOnClickListener {
            // Переход на экран LoginFragment
            findNavController().navigate(R.id.action_finishFragment_to_loginFragment)
        }

        return view
    }
}
