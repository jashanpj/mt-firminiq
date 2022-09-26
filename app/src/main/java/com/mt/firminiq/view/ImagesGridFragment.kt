package com.mt.firminiq.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mt.firminiq.R
import com.mt.firminiq.databinding.FragmentImagesGridBinding
import com.mt.firminiq.utils.ImagesID
import com.mt.firminiq.utils.ImagesName
import com.mt.firminiq.viewModel.ImageGridViewModel


/**
 * Images grid fragment as the default destination in the navigation.
 * Since the requirement doesn't speak about any Splash screen, it is avoided.
 */
class ImagesGridFragment : Fragment() {
    private val viewModel by viewModels<ImageGridViewModel>()

    private var _binding: FragmentImagesGridBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentImagesGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var columnsCount = 3
        val isTablet = context?.resources?.getBoolean(R.bool.isTablet)
        if(isTablet == true){
            columnsCount = 7
        }
        _binding?.recyclerView.apply {
            this?.layoutManager =  GridLayoutManager(context, columnsCount)//LinearLayoutManager(this?.context)
            this?.adapter = ImageAdapter{ images ->
                val bundle = bundleOf(ImagesID to images.image, ImagesName to images.text)
                findNavController().navigate(R.id.action_ImagesGridFragment_to_SecondFragment, bundle)
            }
        }

        _binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return false
            }
        })

        viewModel.filterData.observe(viewLifecycleOwner) { it ->
            (_binding?.recyclerView?.adapter as ImageAdapter).submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}