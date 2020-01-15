# 仿饿了么商品列表页面
(ps:其实用什么实现不重要，这里只是为了仿一个UI,闲来写的，希望给新手参考，老司机请绕道)

### 首先来看一下饿了么商品列表是什么样子

![image](https://github.com/leiyun1993/ELeMaList/raw/master/screenshot/1.gif)

### 1、分析
①首先是标题栏显示大分类、返回键、搜索键----可用Toolbar实现

②展示分类，滑动切换和点击切换，典型的TabLayout

③分类列表切换用ViewPager

④向上滑动时标题栏隐藏，可使用ScrollView监听滑动，也可以使用CoordinatorLayout加AppBarLayout(推荐)

⑤列表用RecyclerView实现，顶部的推荐食品可用不同的ViewType控制

⑥列表向上滑动，列表筛选项悬停；可在固定位置放一个默认隐藏的view，筛选项为一个Item,监听滑动，当滑动一定距离后显示View,

也可以使用RecyclerView的ItemDecoration绘制该View

⑦下拉刷新

### 2、动手实现
①主页面使用CoordinatorLayout+AppBarLayout（ToolBar+SlidingTabLayout）+ViewPager实现,如下是一个很常见的布局

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    ...
    >

    <android.support.design.widget.AppBarLayout
        ...
        >

        <android.support.v7.widget.Toolbar
            ...
            app:layout_scrollFlags="scroll|enterAlways"
            />

        <com.flyco.tablayout.SlidingTabLayout
            ...
            />
    </android.support.design.widget.AppBarLayout>

    <android.support.v4.view.ViewPager
        ...
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />

</android.support.design.widget.CoordinatorLayout>

```

需要滑动隐藏标题栏是靠app:layout_scrollFlags="scroll|enterAlways"，以下为简介，详情请搜索**layout_scrollFlags**

layout_scrollFlags共有5个属性，需要Child View伴随着滚动事件而滚出或滚进屏幕首先加入"scroll"

**enterAlways**:快速返回模式。

**enterAlwaysCollapsed**:enterAlways的附加值,涉及最小高度

**exitUntilCollapsed**:发生向上滚动事件时，Child View向上滚动退出直至最小高度，然后Scrolling View开始滚动。也就是，Child View不会完全退出屏幕。

**snap**:Child View滚动比例的一个吸附效果。

[SlidingTabLayout](https://github.com/H07000223/FlycoTabLayout)是使用别人造好的轮子，使用它很久了很好用很方便，能满足大多数需求，源码也很清晰需要修改也很方便。

ViewPager设置app:layout_behavior="@string/appbar_scrolling_view_behavior"这是官方提供的behavior，我的理解是约定可滚动View和AppBar的折叠关系，有兴趣可以去研究下源码。

然后仅需关联ViewPager和TabLayout即可
```kotlin
mToolBar = findViewById(R.id.toolbar)
mViewPager = findViewById(R.id.viewPager)
mTabLayout = findViewById(R.id.tabLayout)
mToolBar.title = "下午茶"
mToolBar.setNavigationIcon(R.mipmap.ic_back)
setSupportActionBar(toolbar)
mViewPager.adapter = MyViewPagerAdapter(titles, supportFragmentManager)
mTabLayout.setViewPager(mViewPager)
```

②推荐商品和列表

其实这个就很简单了，用过RecyclerView的都知道我们可以用ViewType来使其展示不同的布局

```kotlin
override fun getItemViewType(position: Int): Int {
    return if (position == 0) {
        R.layout.item_recommend //第一个展示推荐商品的布局
    } else {
        R.layout.item_test      //后续展示商品列表项
    }
}
```

③上滑筛选项悬停

这里我们使用ItemDecoration绘制该View来实现，

问：为什么？

答：简单啊！

问：简单吗？

答：简单，因为又有轮子可以用；哈哈，看来我们不生产代码，只是代码的搬运工。

这里的实现借用了[StickyRecyclerHeadersDecoration](https://github.com/timehop/sticky-headers-recyclerview)

这个库的好处是不用改造RecyclerView就可以轻松实现StickyRecyclerHeaders,平时的城市列表，商品分类也可以用到。

首先使你的Adapter实现StickyRecyclerHeadersAdapter<VH extends RecyclerView.ViewHolder>,header的布局即为筛选布局
在实现getHeaderId时将第一个Item的ID返回-1就不会显示，其他商品Item的headerID统一为一个即可，这样在上滑过程中超过position==0筛选即可悬停
```kotlin
class TestAdapter(private var isHome: Boolean) : RecyclerView.Adapter<ViewHolder>(), StickyRecyclerHeadersAdapter<HeaderViewHolder> {


    override fun onBindHeaderViewHolder(holder: HeaderViewHolder?, position: Int) {

    }

    override fun getHeaderId(position: Int): Long = if (position == 0 && isHome) -1 else 1

    override fun onCreateHeaderViewHolder(parent: ViewGroup?): HeaderViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && isHome) R.layout.item_recommend else R.layout.item_test
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int = 30

}
```

④下拉刷新
下拉刷新只是模拟实现，并未实现饿了么的动画，Demo中使用的是最近很火的[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)

### 我们来看一下Demo实现的效果

![image](https://github.com/leiyun1993/ELeMaList/raw/master/screenshot/2.gif)

### 问题分析

实际上头的实现使用ItemDecoration确实比较方便，但是无法实现点击效果。所以达不到筛选的目的，上面的那个粘性头部控件一般只适合那种只做展示的使用，
最后为了实现这个效果我们使用了[sticky-layoutmanager](https://github.com/qiujayen/sticky-layoutmanager)
关于上面这个控件的使用大家可以参考原作者的说明。这东西也是上班的时候突然想到此前的这个Demo的点击事件没有完善，所以特来加上这个实现。
[sticky-layoutmanager](https://github.com/qiujayen/sticky-layoutmanager)是更改的layoutmanager所以我们要确定头实际上就是多一个ViewType,实现如下

```kotlin
override fun isStickyHeader(position: Int): Boolean {
        return if (isHome) {
            position == 1
        } else {
            position == 0
        }
    }

override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
    val view = LayoutInflater.from(parent!!.context).inflate(viewType, parent, false)
    return when (viewType) {
        R.layout.item_recommend -> {
            view.setOnClickListener {
                Toast.makeText(parent.context, "点击", Toast.LENGTH_SHORT).show()
            }
            RecommendViewHolder(view)
        }
        R.layout.item_header -> {
            view.findViewById<TextView>(R.id.type1).setOnClickListener { Toast.makeText(parent.context, "综合排序", Toast.LENGTH_SHORT).show() }
            view.findViewById<TextView>(R.id.type2).setOnClickListener { Toast.makeText(parent.context, "好评优先", Toast.LENGTH_SHORT).show() }
            view.findViewById<TextView>(R.id.type3).setOnClickListener { Toast.makeText(parent.context, "距离最近", Toast.LENGTH_SHORT).show() }
            view.findViewById<TextView>(R.id.type4).setOnClickListener { Toast.makeText(parent.context, "筛选", Toast.LENGTH_SHORT).show() }
            HeaderViewHolder(view)
        }
        else -> {
            view.setOnClickListener {
                Toast.makeText(parent.context, it.tag as String, Toast.LENGTH_SHORT).show()
            }
            ViewHolder(view)
        }
    }
}
```

### 最后我们来看一下增加点击的Demo实现的效果

![image](https://github.com/leiyun1993/ELeMaList/raw/master/screenshot/3.gif)
