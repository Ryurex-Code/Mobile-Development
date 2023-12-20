package com.puitika.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.puitika.R
import com.puitika.data.remote.response.ClothDetail
import com.puitika.data.remote.response.ClothResponse
import com.puitika.data.remote.response.RegionDetail
import com.puitika.data.remote.response.RegionResponse
import com.puitika.databinding.FragmentHomeBinding
import com.puitika.factory.ViewModelFactory
import com.puitika.ui.detail.cloth_detail.ClothDetailActivity
import com.puitika.ui.detail.region_detail.RegionDetailActivity
import com.puitika.ui.main.event.AddEventFormActivity
import com.puitika.ui.main.main.MainActivity
import com.puitika.ui.profile.ProfileActivity
import com.puitika.utils.Result
import com.puitika.utils.showToast

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var factory: ViewModelFactory
    private val viewModel: HomeViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModelFactory()
        setComponent()
        setAction()
    }

    private fun setComponent() {
        viewModel.getRegions().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.layoutShimmerRegion.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    binding.layoutShimmerRegion.visibility = View.VISIBLE
                    showToast(requireActivity(),result.data)
                }
                is Result.Success -> {
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.layoutShimmerRegion.visibility = View.GONE
                        binding.regionLayout.visibility = View.VISIBLE
                        showRegion(result.data)
                    }, 1000)
                }
            }
        }
        viewModel.getClothes().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.layoutShimmerCloth.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    binding.layoutShimmerCloth.visibility = View.VISIBLE
                    showToast(requireActivity(),result.data)
                }
                is Result.Success -> {
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.layoutShimmerCloth.visibility = View.GONE
                        binding.rvCloth.visibility = View.VISIBLE
                        showTraditionalCloth(result.data)
                    }, 1000)

                }
            }
        }
    }

    private fun setAction() {
        binding.topNavigation.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_account -> {
                    startActivity(Intent(requireContext(), ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }


    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(binding.root.context)
    }

    private fun showRegion(regionList: RegionResponse) {
        val regionAdapter = RegionAdapter(requireContext(), regionList.data)
        binding.rvRegion.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = regionAdapter
        }
        regionAdapter.setOnItemClickListener(object : RegionAdapter.OnItemClickListener {
            override fun onClick(imageView: ImageView, region: RegionDetail) {
                val intent = Intent(requireContext(), RegionDetailActivity::class.java)
                intent.putExtra("EXTRA_REGION", region)
                startActivity(intent)
            }
        })

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                val nextPosition =
                    (binding.rvRegion.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() + 1
                if (nextPosition < regionAdapter.itemCount) {
                    binding.rvRegion.smoothScrollToPosition(nextPosition)
                } else {
                    binding.rvRegion.smoothScrollToPosition(0)
                }
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(runnable, 3000)
    }

    private fun showTraditionalCloth(clothes: ClothResponse) {
        val clothAdapter = ClothesAdapter(requireContext(), clothes.data)
        val spanCount = 2
        binding.rvCloth.apply {
            layoutManager =
                StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
            adapter = clothAdapter
        }
        clothAdapter.setOnItemClickListener(object : ClothesAdapter.OnItemClickListener {
            override fun onClick(ivCloth: ImageView, cloth: ClothDetail) {
                val intent = Intent(requireContext(), ClothDetailActivity::class.java)
                intent.putExtra("EXTRA_CLOTH", cloth)
                startActivity(intent)
            }
        })
    }
}


