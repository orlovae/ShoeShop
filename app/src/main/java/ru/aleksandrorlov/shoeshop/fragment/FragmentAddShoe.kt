package ru.aleksandrorlov.shoeshop.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import ru.aleksandrorlov.shoeshop.R
import ru.aleksandrorlov.shoeshop.config.FakeData
import ru.aleksandrorlov.shoeshop.databinding.FragmentAddShoeBinding
import ru.aleksandrorlov.shoeshop.model.Shoe

class FragmentAddShoe : Fragment() {
    private var _binding : FragmentAddShoeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ViewModelAddShoe =
            ViewModelProvider(this).get(ViewModelAddShoe::class.java)

        viewModel.shoe.observe(viewLifecycleOwner,
        Observer { shoe -> })

        viewModel.errorMessage.observe(viewLifecycleOwner,
            Observer { message -> showToast(message) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment", "FragmentAddShoe onCreateView")
        _binding = FragmentAddShoeBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener {

            val textInEditTextMap = mapOf<String, String>(
                binding.editTextShoeName.hint.toString() to binding.editTextShoeName.text.toString(),
                binding.editTextCompanyName.hint.toString() to binding.editTextCompanyName.text.toString(),
                binding.editTextShoeSize.hint.toString() to binding.editTextShoeSize.text.toString(),
                binding.editTextShoeDescription.hint.toString() to binding.editTextShoeDescription.text.toString()
            )

            if (chekEmptyEditText(textInEditTextMap)) {
                saveShoeToFakeData(
                    Shoe(
                        getIdNewShoe(),
                        binding.editTextShoeName.text.toString(),
                        binding.editTextCompanyName.text.toString(),
                        binding.editTextShoeSize.text.toString().toInt(),
                        binding.editTextShoeDescription.text.toString()
                    )
                )
                openShoeList(0)
            } else {
                showEmptyEditText(textInEditTextMap)
            }
        }

        binding.buttonCancel.setOnClickListener{
            openShoeList(-1)
        }

        setToolbar()

        return binding.root
    }

    private fun setToolbar() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_app_bar, menu)
                menu.findItem(R.id.logout).setVisible(true)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.logout -> {
                        findNavController().navigate(R.id.fragmentLogin)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showEmptyEditText(mapEditText: Map<String, String>) {
        for ((key, value) in mapEditText){
            if (value.isEmpty()) {
                Toast.makeText(requireContext(), key, Toast.LENGTH_LONG).show()
                break
            }
        }
    }

    private fun chekEmptyEditText(mapEditText: Map<String, String>) : Boolean {
        var checked = true
        for ((key, value) in mapEditText){
            if (value.isEmpty()) {
                checked = false
                break
            }
        }
        return checked
    }

    private fun getIdNewShoe() : Int {
        var idNew = FakeData.getAll().lastIndex
        return idNew++
    }

    private fun saveShoeToFakeData(shoe: Shoe) {
        FakeData.addShoe(shoe)
    }

    private fun openShoeList(id: Int) {
        parentFragmentManager.setFragmentResult(REQUEST_CODE, bundleOf(
            EXTRA_ID to id
        ))
        findNavController().popBackStack()

    }

    private fun showToast(message: String) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("Fragment", "FragmentAddShoe onDestroyView")
    }

    companion object {
        const val REQUEST_CODE = "ID_REQUEST_CODE"
        const val EXTRA_ID = "EXTRA_ID"
    }
}