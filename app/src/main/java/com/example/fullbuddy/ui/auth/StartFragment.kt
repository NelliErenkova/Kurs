package com.example.fullbuddy.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fullbuddy.R
import com.google.firebase.auth.FirebaseAuth

class StartFragment : Fragment(R.layout.fragment_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Проверяем, авторизован ли пользователь
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // Пользователь авторизован, переходим в PersonalSetupFragment
            findNavController().navigate(R.id.action_startFragment_to_personalSetupFragment)
        } else {
            // Пользователь не авторизован, переходим в LoginFragment
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
    }
}
