package com.puitika.ui.main.event

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.puitika.R
import com.puitika.databinding.FragmentEventBinding
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.puitika.data.remote.response.EventDetail
import com.puitika.data.remote.response.EventResponse
import com.puitika.factory.ViewModelFactory
import com.puitika.utils.Result

class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recyclerivewevents
        recyclerView.layoutManager = LinearLayoutManager(context)

        eventAdapter = EventAdapter(requireContext(), eventList.data)
        recyclerView.adapter = eventAdapter

        eventAdapter.setOnItemClickListener(object : EventAdapter.OnItemClickListener {
            override fun onClick(clickedView: View, event: DetailEvent) {
                navigateToDetailEvent(event)
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAction()
    }

    private fun setAction() {
        binding.topNavigation.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_addevent -> {
                    startActivity(Intent(requireContext(), AddEventFormActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    private fun navigateToDetailEvent(detailEvent: DetailEvent) {
        val intent = Intent(requireContext(), EventDetailActivity::class.java)
        intent.putExtra("EXTRA_EVENT", detailEvent)
        startActivity(intent)
    }

}