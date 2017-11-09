package com.leiyun.elemalist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.flyco.tablayout.SlidingTabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var mToolBar: Toolbar
    private lateinit var mViewPager: ViewPager
    private lateinit var mTabLayout: SlidingTabLayout
    private val titles = mutableListOf("全部", "卤味", "麻辣小吃", "精品蛋糕", "水果", "咖啡","奶茶果汁","面包","牛奶")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mToolBar = findViewById(R.id.toolbar)
        mViewPager = findViewById(R.id.viewPager)
        mTabLayout = findViewById(R.id.tabLayout)
        mToolBar.title = "下午茶"
        mToolBar.setNavigationIcon(R.mipmap.ic_back)
        mViewPager.adapter = MyViewPagerAdapter(titles, supportFragmentManager)
        mTabLayout.setViewPager(mViewPager)
    }
}

class MyViewPagerAdapter(private val titles: MutableList<String>, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val fragment = TestFragment()
        val arguments = Bundle()
        arguments.putInt("position",position)
        fragment.arguments = arguments
        return fragment
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]

}
