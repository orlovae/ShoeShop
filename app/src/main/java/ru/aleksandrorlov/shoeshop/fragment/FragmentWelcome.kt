package ru.aleksandrorlov.shoeshop.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.aleksandrorlov.shoeshop.R
import ru.aleksandrorlov.shoeshop.databinding.FragmentWelcomeBinding

class FragmentWelcome : Fragment() {
    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment", "FragmentWelcome onCreateView")
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding.buttonManual.setOnClickListener{
            openManual()
        }
        return binding.root
    }

    private fun openManual() {
        findNavController().navigate(
            R.id.action_fragmentWelcome_to_fragmentManual,
            bundleOf()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Fragment", "FragmentWelcome onDestroyView")
    }
}