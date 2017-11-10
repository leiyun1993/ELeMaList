# 仿饿了么商品列表页面（用Kotlin实现）
## 首先来看一下饿了么商品列表是什么样子

![image](https://github.com/leiyun1993/ELeMaList/raw/master/screenshot/1.gif)

### 1、分析
①首先是标题栏显示大分类、返回键、搜索键----可用Toolbar实现
②展示分类，滑动切换和点击切换，典型的TabLayout
③分类列表切换用ViewPager
④向上滑动时标题栏隐藏，可使用ScrollView监听滑动，也可以使用CoordinatorLayout加AppBarLayout(推荐)
⑤列表用RecyclerView实现，顶部的推荐食品可用不同的ViewType控制
⑥列表向上滑动，列表筛选项悬停；可在固定位置放一个默认隐藏的view，筛选项为一个Item,监听滑动，当滑动一定距离后显示View,
也可以使用RecyclerView的ItemDecoration绘制该View