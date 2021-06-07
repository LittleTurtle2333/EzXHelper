package com.github.kyuubiran.ezxhelper.init

import android.content.Context
import android.content.res.XModuleResources
import android.content.res.XResources
import com.github.kyuubiran.ezxhelper.init.InitFields.LOG_TAG
import com.github.kyuubiran.ezxhelper.init.InitFields.TOAST_TAG
import com.github.kyuubiran.ezxhelper.init.InitFields.appContext
import com.github.kyuubiran.ezxhelper.init.InitFields.ezXClassLoader
import com.github.kyuubiran.ezxhelper.init.InitFields.hostPackageName
import com.github.kyuubiran.ezxhelper.init.InitFields.modulePath
import com.github.kyuubiran.ezxhelper.init.InitFields.moduleRes
import com.github.kyuubiran.ezxhelper.utils.Log
import de.robv.android.xposed.IXposedHookInitPackageResources
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import de.robv.android.xposed.callbacks.XC_LoadPackage

object EzXHelperInit {
    /**
     * 使用本库必须执行的初始化
     * 应在handleLoadPackage方法内第一个调用
     * @see IXposedHookLoadPackage.handleLoadPackage
     * @see XC_LoadPackage.LoadPackageParam
     */
    fun initHandleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {

        setEzClassLoader(lpparam.classLoader)
        setHostPackageName(lpparam.packageName)
    }

    /**
     * 设置模块路径
     * @see IXposedHookZygoteInit.StartupParam.modulePath
     */
    fun setModulePath(path: String) {
        modulePath = path
    }

    /**
     * 设置模块资源
     * @see IXposedHookInitPackageResources.handleInitPackageResources
     * @see XC_InitPackageResources.InitPackageResourcesParam.res
     */
    fun setXModuleResources(
        path: String,
        xResources: XResources
    ) {
        moduleRes = XModuleResources.createInstance(path, xResources)
    }

    /**
     * 设置本库使用的类加载器
     * 注意：通常情况下 建议使用框架提供的类加载器进行操作
     * 但某些app会修改自身的类加载器 遇到这种情况请自行设置运行时的类加载器
     * @param classLoader 类加载器
     */
    fun setEzClassLoader(classLoader: ClassLoader) {
        ezXClassLoader = classLoader
    }

    /**
     * 设置宿主包名
     */
    fun setHostPackageName(packageName: String) {
        hostPackageName = packageName
    }

    /**
     * 初始化全局ApplicationContext
     */
    fun initAppContext(context: Context) {
        appContext = context
    }

    /**
     * 设置打印日志的标签
     */
    fun setLogTag(tag: String) {
        LOG_TAG = tag
    }

    /**
     * 设置Log.toast的Tag
     * 如果不设置会使用日志TAG
     * @see Log.toast
     */
    fun setToastTag(tag: String) {
        TOAST_TAG = tag
    }

}