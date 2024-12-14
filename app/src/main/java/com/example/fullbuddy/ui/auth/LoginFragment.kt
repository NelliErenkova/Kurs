package com.example.fullbuddy.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fullbuddy.R
import com.example.fullbuddy.data.UserDatabaseHelper

class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var goToRegisterTextView: TextView
    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        usernameEditText = view.findViewById(R.id.et_login_username)
        passwordEditText = view.findViewById(R.id.et_login_password)
        loginButton = view.findViewById(R.id.btn_login)
        goToRegisterTextView = view.findViewById(R.id.tv_go_to_register)

        dbHelper = UserDatabaseHelper(requireContext())

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userExists = dbHelper.checkUser(username, password)
            if (userExists) {
                Toast.makeText(requireContext(), "Вход успешен!", Toast.LENGTH_SHORT).show()
                // Переходим к экрану подбора персональной тренировки (создадим позже)
                findNavController().navigate(R.id.action_loginFragment_to_personalSetupFragment)
            } else {
                Toast.makeText(requireContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }

        goToRegisterTextView.setOnClickListener {
            // Переходим на экран регистрации
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return view

    }

}
