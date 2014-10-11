## ![logo](https://github.com/kymjs/KJFrameForAndroid/blob/master/KJFrameExample/logo.jpg) CJFrameForAndroid简介
=================

**CJFrameForAndroid** 是一个实现android插件化开发的框架。使用CJFrameForAndroid，apk动态加载不再是难题。<br>

## CJFrameForAndroid 相关链接
* QQ群：[257053751](http://shang.qq.com/wpa/qunwpa?idkey=00d92c040e81d87ccd21f8d0fffb10640baaa66da45254c3bd329b6ff7d46fef)(开发者群1)，[201055521](http://jq.qq.com/?_wv=1027&k=MBVdpK)(开发者群2)
* 项目地址：[https://github.com/kymjs/CJFrameForAndroid](https://github.com/kymjs/CJFrameForAndroid)
* 相关框架: [KJFrameForAndroid](https://github.com/kymjs/KJFrameForAndroid)

---
# 名词解释

# 原理描述
CJFrameForAndroid的实现原理是通过类加载器，动态加载存在于SD卡上的apk包中的Activity。通过使用一个托管所，插件Activity全部事务(包括声明周期与交互事件)将交由托管所来处理。<br>

# 框架使用

----

# 注意事项
>使用 CJFrameForAndroid 插件开发框架需要在你项目的AndroidManifest.xml文件中加入以下Activity声明：*
```xml
<activity android:name="org.kymjs.aframe.plugin.CJProxyActivity" />
```