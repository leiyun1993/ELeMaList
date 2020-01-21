package com.leiyun.elemalist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jay.widget.StickyHeadersLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_test.*


/**
 * A simple [Fragment] subclass.
 */
class TestFragment : Fragment() {

    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt("position") ?: 0
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = StickyHeadersLinearLayoutManager<TestAdapter>(activity)
        recycler_view.layoutManager = layoutManager
        val adapter = TestAdapter(position == 0)
        recycler_view.adapter = adapter


        refreshLayout.setOnRefreshListener {
            refreshLayout.finishRefresh(1500)  //模拟数据刷新
        }

        refreshLayout.setOnLoadMoreListener {
            refreshLayout.finishLoadMore(1500)//模拟加载更多
        }

    }

}// Required empty public constructor
