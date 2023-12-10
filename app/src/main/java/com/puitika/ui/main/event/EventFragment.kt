package com.puitika.ui.main.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puitika.R
import com.puitika.data.dummy.eventList // Import the eventList from dummyevent
import com.puitika.ui.main.event.EventAdapter // Import the EventAdapter

class EventFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(context)

        eventAdapter = EventAdapter(requireContext(), eventList.data)
        recyclerView.adapter = eventAdapter

        return view
    }
}
