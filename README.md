# 仿饿了么商品列表页面（用Kotlin实现）
(ps:其实用什么实现不重要，这里只是为了仿一个UI)

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

[SlidingTabLayout](https://github.com/H07000223/FlycoTabLayout)是使用别人造好的轮子，使用它很久了很好用很方便，能满足大多数需求，源码也很清晰需要修改也能方便。

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