package com.leiyun.elemalist

import android.app.Application
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter

/**
 * 类名：App
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2017-11-09 18:13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class App: Application() {

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, layout ->
            layout.setPrimaryColors(R.color.colorPrimary, android.R.color.white)
            MaterialHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreater { context, _ ->
            ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate)
        }
    }
}