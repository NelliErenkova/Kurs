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

class RegisterFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var goToLoginTextView: TextView
    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        usernameEditText = view.findViewById(R.id.et_register_username)
        passwordEditText = view.findViewById(R.id.et_register_password)
        confirmPasswordEditText = view.findViewById(R.id.et_register_confirm_password)
        registerButton = view.findViewById(R.id.btn_register)
        goToLoginTextView = view.findViewById(R.id.tv_go_to_login)

        dbHelper = UserDatabaseHelper(requireContext())

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = dbHelper.addUser(username, password)
            if (result) {
                Toast.makeText(requireContext(), "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                // Переходим на экран входа
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show()
            }
        }

        goToLoginTextView.setOnClickListener {
            // Переходим на экран входа
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }
}
