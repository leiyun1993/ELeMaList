package com.leiyun.elemalist

import android.app.Application
import android.graphics.Color
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
class App : Application() {

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColors(context.resources.getColor(R.color.colorPrimary), Color.WHITE)
            return@setDefaultRefreshHeaderCreator MaterialHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate)
        }
    }
}