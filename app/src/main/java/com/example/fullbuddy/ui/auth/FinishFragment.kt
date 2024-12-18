package com.example.fullbuddy.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fullbuddy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FinishFragment : Fragment() {

    private lateinit var returnToMainButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finish, container, false)
        returnToMainButton = view.findViewById(R.id.btn_return_to_main)

        returnToMainButton.setOnClickListener {
            val trainingName = arguments?.getString("trainingName") ?: "Нет данных"
            val uid = FirebaseAuth.getInstance().currentUser?.uid

            if (uid != null && trainingName != "Нет данных") {
                val db = FirebaseFirestore.getInstance()
                val userDocRef = db.collection("users").document(uid)

                userDocRef.get().addOnSuccessListener { document ->
                    if (document.exists()) {
                        // Документ есть, обновим массив тренировок
                        val completedTrainings = document.get("completedTrainings") as? MutableList<String> ?: mutableListOf()
                        if (!completedTrainings.contains(trainingName)) {
                            completedTrainings.add(trainingName)
                            userDocRef.update("completedTrainings", completedTrainings).addOnCompleteListener {
                                // Независимо от успеха или ошибки, переходим
                                navigateToAccountFragment(trainingName)
                            }
                        } else {
                            // Если тренировка уже есть в списке
                            navigateToAccountFragment(trainingName)
                        }
                    } else {
                        // Документ не существует, создаём
                        val newData = hashMapOf(
                            "email" to (FirebaseAuth.getInstance().currentUser?.email ?: "Неизвестно"),
                            "completedTrainings" to listOf(trainingName)
                        )
                        userDocRef.set(newData).addOnCompleteListener {
                            // Независимо от успеха или ошибки, переходим
                            navigateToAccountFragment(trainingName)
                        }
                    }
                }.addOnFailureListener {
                    // Ошибка чтения документа, переходим
                    navigateToAccountFragment(trainingName)
                }
            } else {
                // Нет uid или trainingName, просто переходим
                navigateToAccountFragment(trainingName)
            }
        }

        return view
    }

    private fun navigateToAccountFragment(trainingName: String) {
        val bundle = Bundle().apply {
            putString("trainingName", trainingName)
        }
        findNavController().navigate(R.id.action_finishFragment_to_accountFragment, bundle)
    }
}
