package com.leiyun.elemalist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.flyco.tablayout.SlidingTabLayout
import kotlinx.android.synthetic.main.activity_main.*

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
        setSupportActionBar(toolbar)
        mViewPager.adapter = MyViewPagerAdapter(titles, supportFragmentManager)
        mTabLayout.setViewPager(mViewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home_serach,menu)
        return true
    }
}

class MyViewPagerAdapter(private val titles: MutableList<String>, fm: FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {
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
