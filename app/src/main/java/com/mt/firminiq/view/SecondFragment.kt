package com.mt.firminiq.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.mt.firminiq.databinding.FragmentSecondBinding
import com.mt.firminiq.utils.ImagesID
import com.mt.firminiq.utils.ImagesName


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            _binding?.imageViewDetail?.setImageResource(it.getInt(ImagesID))
            (activity as AppCompatActivity).supportActionBar?.title = it.getString(ImagesName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}