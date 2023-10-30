package com.bebyaww.hotnewsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bebyaww.hotnewsapp.adapter.NewsAdapter
import com.bebyaww.hotnewsapp.databinding.FragmentAljazeeraBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AljazeeraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class AljazeeraFragment : Fragment() {
    private var _binding: FragmentAljazeeraBinding? = null
    private val binding get() = _binding as FragmentAljazeeraBinding

    private var _alJazeeraNewsViewModel: NewsViewModel? = null
    private val  alJazeeraNewsViewModel get() = _alJazeeraNewsViewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAljazeeraBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _alJazeeraNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        alJazeeraNewsViewModel.alJazeeraNews()
        alJazeeraNewsViewModel.alJazeeraNews.observe(viewLifecycleOwner){
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "AljazeeraFragment",
                "onViewCreated: ${it.articles}"
            )

            binding.rvAlquran.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility =View.GONE
        }
    }

}