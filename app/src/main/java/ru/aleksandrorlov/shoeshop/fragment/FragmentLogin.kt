package ru.aleksandrorlov.shoeshop.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import ru.aleksandrorlov.shoeshop.R
import ru.aleksandrorlov.shoeshop.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.buttonSingIn.setOnClickListener{
            openWelcome()
        }
        binding.buttonSingUp.setOnClickListener{
            openWelcome()
        }

        return binding.root
    }

    private fun openWelcome() {
        findNavController().navigate(
            R.id.action_fragmentLogin_to_fragmentWelcome,
            bundleOf()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}