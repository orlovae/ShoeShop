package ru.aleksandrorlov.shoeshop.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.aleksandrorlov.shoeshop.R
import ru.aleksandrorlov.shoeshop.config.FakeData
import ru.aleksandrorlov.shoeshop.databinding.FragmentShoeListBinding
import ru.aleksandrorlov.shoeshop.model.Shoe
class FragmentShoeList : Fragment() {
    private var _binding : FragmentShoeListBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback : OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("xxx", "FragmentShoeList back pressed invoked")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
        callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ViewModelShoeList =
            ViewModelProvider(this).get(ViewModelShoeList::class.java)

        viewModel.shoes.observe(viewLifecycleOwner,
            Observer { shoes -> showShoeList(shoes)})

        viewModel.errorMessage.observe(viewLifecycleOwner,
            Observer { message -> showToast(message) })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment", "FragmentShoeList onCreateView")
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener{
            addShoe()
        }

        getShoeFromFragmentAddShoe()

        setToolbar()

        return binding.root
    }

    private fun setToolbar() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider{
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

    private fun getShoeFromFragmentAddShoe() : Shoe? {
        var id  = -1
        parentFragmentManager.setFragmentResultListener(FragmentAddShoe.REQUEST_CODE,
            viewLifecycleOwner) {
                _, data -> id = data.getInt(FragmentAddShoe.EXTRA_ID)
        }
        if (id >=0 ) {
            return FakeData.getId(id)
        }
        return null
    }

    private fun addShoe() {
        findNavController().navigate(
            R.id.action_fragmentShoeList_to_fragmentShoeAdd,
            bundleOf()
        )
    }

    private fun showShoeList(shoes: List<Shoe>) {
        val textView = TextView(requireContext())

        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        textView.text = generateTextFromShoeListToTextView(shoes)

        binding.linearLayout.addView(textView)

    }

    private fun showToast(message: String) {

    }

    private fun generateTextFromShoeListToTextView(shoes: List<Shoe>) : String {
        val text = StringBuilder()
        shoes.forEach {
            text.append(generateTextFromPropertyShoeModel(it))
                .append(System.getProperty(LINE))
        }
        return text.toString()
    }

    private fun generateTextFromPropertyShoeModel(shoe: Shoe) : String {
       return  buildString {
            append(getString(R.string.shoe_add_tv_shoe_name))
            append(" - ")
            append((shoe.name))
            append(System.getProperty(LINE))
            append(getString(R.string.shoe_add_tv_company_name))
            append(" - ")
            append(shoe.companyName)
            append(System.getProperty(LINE))
            append(getString(R.string.shoe_add_tv_shoe_size))
            append(" - ")
            append(shoe.size)
            append(System.getProperty(LINE))
            append(getString(R.string.shoe_add_tv_shoe_description))
            append(" - ")
            append(shoe.description)
            append(System.getProperty(LINE))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        Log.d("", "FragmentShoeList onDestroyView")
    }

    companion object {
        const val LINE = "line.separator"
    }
}