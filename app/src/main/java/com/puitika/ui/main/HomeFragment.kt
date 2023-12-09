package com.puitika.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.puitika.R
import com.puitika.data.dummy.dummyTraditionalCloths
import com.puitika.data.dummy.regionList
import com.puitika.databinding.FragmentHomeBinding
import com.puitika.ui.profile.ProfileActivity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topNavigation.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_account -> {
                    startActivity(Intent(requireContext(), ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val regionAdapter = RegionAdapter(requireContext(), regionList.data)
        binding.rvRegion.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = regionAdapter
        }

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                val nextPosition = (binding.rvRegion.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() + 1
                if (nextPosition < regionAdapter.itemCount) {
                    binding.rvRegion.smoothScrollToPosition(nextPosition)
                } else {
                    binding.rvRegion.smoothScrollToPosition(0)
                }
                handler.postDelayed(this, 3000) // Adjust the delay as needed (in milliseconds)
            }
        }

        handler.postDelayed(runnable, 3000) // Initial delay before the auto-scroll starts

        val clothAdapter = ClothAdapter(requireContext(), dummyTraditionalCloths.data)
        val spanCount = 2
        binding.rvCloth.apply {
            layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
            adapter = clothAdapter
        }
    }

}


