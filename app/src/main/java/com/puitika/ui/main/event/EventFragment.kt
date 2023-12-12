package com.puitika.ui.main.event

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puitika.R
import com.puitika.databinding.FragmentEventBinding
import com.puitika.data.dummy.eventList
import androidx.appcompat.widget.Toolbar
import com.puitika.data.dummy.DetailEvent

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

        toolbar = view.findViewById(R.id.toolbar)
        setupToolbar()

        recyclerView = binding.recyclerViewEvents
        recyclerView.layoutManager = LinearLayoutManager(context)

        eventAdapter = EventAdapter(requireContext(), eventList.data)
        recyclerView.adapter = eventAdapter

        eventAdapter.setOnItemClickListener(object : EventAdapter.OnItemClickListener {
            override fun onClick(clickedView: View, event: DetailEvent) {
                navigateToDetailEvent(event)
            }
        })

        // Set the options menu for this fragment
        setHasOptionsMenu(true)

        return view
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_notif -> {
                    startActivity(Intent(requireContext(), NotifActivity::class.java))
                    true
                }
                R.id.menu_addevent -> {
                    startActivity(Intent(requireContext(), EventFormActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToDetailEvent(detailEvent: DetailEvent) {
        val intent = Intent(requireContext(), EventDetailActivity::class.java)
        intent.putExtra("Id", detailEvent.id)
        startActivity(intent)
    }

}
