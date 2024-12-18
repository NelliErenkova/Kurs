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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AccountFragment : Fragment() {

    private lateinit var tvUserEmail: TextView
    private lateinit var tvLastTraining: TextView
    private lateinit var tvAllTrainings: TextView
    private lateinit var btnLogout: Button

    private var trainingName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trainingName = it.getString("trainingName")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        tvUserEmail = view.findViewById(R.id.tv_user_email)
        tvLastTraining = view.findViewById(R.id.tv_last_training)
        tvAllTrainings = view.findViewById(R.id.tv_all_trainings)
        btnLogout = view.findViewById(R.id.btn_logout)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userEmail = FirebaseAuth.getInstance().currentUser?.email
        tvUserEmail.text = userEmail ?: "Неизвестно"

        // Последняя пройденная тренировка
        tvLastTraining.text = trainingName ?: "Нет данных"

        // Загрузка всех пройденных тренировок пользователя
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            val db = FirebaseFirestore.getInstance()
            val userDocRef = db.collection("users").document(uid)
            userDocRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    val completedTrainings = document.get("completedTrainings") as? List<String>
                    if (completedTrainings != null && completedTrainings.isNotEmpty()) {
                        tvAllTrainings.text = "Пройденные тренировки: " + completedTrainings.joinToString(", ")
                    } else {
                        tvAllTrainings.text = "Пройденные тренировки: нет"
                    }
                } else {
                    tvAllTrainings.text = "Пройденные тренировки: нет"
                }
            }.addOnFailureListener {
                tvAllTrainings.text = "Ошибка загрузки данных"
            }
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }
    }
}
