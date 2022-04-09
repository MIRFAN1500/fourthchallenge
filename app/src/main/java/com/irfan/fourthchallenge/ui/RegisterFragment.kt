package com.irfan.fourthchallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.irfan.fourthchallenge.R
import com.irfan.fourthchallenge.data.source.local.AppLocalData
import com.irfan.fourthchallenge.databinding.FragmentRegisterBinding
import com.irfan.fourthchallenge.model.RegisteredUser

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEdt.text.toString().trim()
            val password = binding.passwordEdt.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEdt.text.toString().trim()
            val email = binding.emailEdt.text.toString().trim()

            if (password != confirmPassword) {
                Snackbar.make(it, "The confirm password doesn't match", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val registeredUser = RegisteredUser(username,email,password)
            AppLocalData.setRegisteredUser(requireContext(), registeredUser)

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}