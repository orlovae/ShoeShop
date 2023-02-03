package ru.aleksandrorlov.shoeshop.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import ru.aleksandrorlov.shoeshop.R

import ru.aleksandrorlov.shoeshop.databinding.FragmentManualBinding
import kotlin.math.log

class FragmentManual : Fragment() {
    private var _binding : FragmentManualBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment", "FragmentManual onCreateView")
        _binding = FragmentManualBinding.inflate(inflater, container, false)
        binding.buttonShoes.setOnClickListener{
            openShoeList()
        }
        return binding.root
    }

    private fun openShoeList() {
        findNavController().navigate(
            R.id.action_fragmentManual_to_fragmentShoeList,
            bundleOf()
        )
        clearBackStack()
    }

    private fun clearBackStack() {
        val fragmentManager = requireActivity().supportFragmentManager
        Log.d("???", fragmentManager.backStackEntryCount.toString())
        fragmentManager.clearBackStack("")
        Log.d("???", fragmentManager.backStackEntryCount.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Fragment", "FragmentShoeList onDestroyView")
    }
}