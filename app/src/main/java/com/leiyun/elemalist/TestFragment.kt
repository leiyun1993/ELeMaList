package com.leiyun.elemalist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration


/**
 * A simple [Fragment] subclass.
 */
class TestFragment : Fragment() {

    var position = 0
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt("position") ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view!!.findViewById(R.id.recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = TestAdapter(position == 0)
        val stickyRecyclerHeadersDecoration = StickyRecyclerHeadersDecoration(adapter)
        mRecyclerView.addItemDecoration(stickyRecyclerHeadersDecoration)
        mRecyclerView.adapter = adapter
    }

}// Required empty public constructor
