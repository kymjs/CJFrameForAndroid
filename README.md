## ![logo](https://github.com/kymjs/KJFrameForAndroid/blob/master/KJFrameExample/logo.jpg) CJFrameForAndroid简介
**CJFrameForAndroid** 是一个实现android插件化开发的框架。使用CJFrameForAndroid，apk动态加载不再是难题，更重要的是可以轻松实现插件与APP项目之间的解耦。<br>

## CJFrameForAndroid 相关链接
* QQ群：[257053751](http://shang.qq.com/wpa/qunwpa?idkey=00d92c040e81d87ccd21f8d0fffb10640baaa66da45254c3bd329b6ff7d46fef)(开发者群1)，[201055521](http://jq.qq.com/?_wv=1027&k=MBVdpK)(开发者群2)
* 项目地址：[https://github.com/kymjs/CJFrameForAndroid](https://github.com/kymjs/CJFrameForAndroid)
* 相关框架: [KJFrameForAndroid](https://github.com/kymjs/KJFrameForAndroid)

---
# 名词解释
**APP项目**：指要调用插件apk的那个已经安装到用户手机上的应用。<br>
**插件项目**：指没有被安装且希望借助已经安装到手机上的项目运行的apk。<br>
**插件化**：Activity继承自CJActivity，且与APP项目jar包冲突已经解决的插件项目称为已经被插件化。<br>
**Activity事务**：在CJFrameForAndroid中，一个Activity的生命周期以及交互事件统称为Activity的事务。<br>
**托管所**：指插件中的一个委派/代理Activity，通过这个Activity去处理插件中Activity的全部事务，从而表现为就像插件中的Activity在运行一样。<br>

# 功能支持
目前Activity的动态加载，包括生命周期和交互事件均可正常使用（launchMode还没有办法定义）。<br>
Fragment的动态加载，因为Activity都可以正常加载，Fragment作为Activity的一部分，自然是没有问题的。<br>
插件与APP之间的数据通信已经可用，本质上还是Intent的传递。<br>
动态注册的BroadcastReceiver可正常使用。<br>
最新添加，绑定式、启动式Service均可正常使用。<br>

# 原理描述
CJFrameForAndroid的实现原理是通过类加载器，动态加载存在于SD卡上的apk包中的Activity。通过使用一个托管所，插件Activity全部事务(包括声明周期与交互事件)将交由托管所来处理，间接实现插件的运行。<br>
一句话描述：CJFrameForAndroid中的托管所，复制了插件中的Activity，来替代插件中的Activity与用户交互。<br>

# 框架使用
●使用 CJFrameForAndroid 插件开发框架需要在你项目的AndroidManifest.xml文件中加入托管所的声明。<br>
```xml
<activity android:name="org.kymjs.aframe.plugin.CJProxyActivity" />
```
●让插件应用中的Activity继承CJActivity，并且一切使用this调用的方法都使用that替代。例如this.setContentView();需要改为that.setContentView();<br>
●插件中涉及到的Android权限，须在APP项目清单中具有声明。<br>
●插件Activity跳转时，推荐使用CJActivityUtils类来辅助跳转。若一定要startActivity或startActivityForResult，在跳转过程中的Intent不能自己new，必须使用CJActivityUtils.getPluginIntent();<br>
●在插件和APP两个工程中不能引用相同的jar包。解决办法是：在插件工程的项目中添加一个/cjlibs的文件夹，将需要调用的jar包放到这个文件夹中，并在插件项目目录下的.classpath中加入如下语句，系统会自动处理相关细节
```xml
<classpathentry kind="lib" path="cjlibs"/>
```

# 示例工程运行
●插件工程：如果要让插件工程单独运行，你只需要将插件工程中/cjlibs目录中的jar包复制到/libs目录下即可。（demo工程中可能需要修改一行代码，请见[这里](https://github.com/kymjs/CJFrameForAndroid/blob/master/PluginExample/src/org/kymjs/aframe/plugin/MainActivity.java)第41行注释解释）<br>
			如果作为插件运行，同样需要修改前面所说的第41行待，然后只需要将插件工程插件化编译（此时安装是会报错），最后将/bin目录下的PluginExample.apk复制到手机SD卡根目录即可。<br>
●APP工程：运行APP工程只需要将已插件化的apk插件复制到手机SD卡根目录下即可。（一定要是插件化的工程）<br>

----

# 注意事项
●目前仅支持Activity和Fragment，其他特殊组件暂未测试。<br>
●APP项目和插件项目中，都需要使用到CJFrameForAndroid的jar包。<br>
●在项目中必须加入托管所声明。<br>
●在开发插件的时候，必须继承CJActivity;<br>
●在插件的Activity中，一切使用this的部分必须使用that来替代;<br>
●在插件Activity跳转时，推荐使用CJActivityUtils类来辅助跳转；<br>
●在插件和APP两个工程中不能引用相同的jar包；<br>

## 许可
**本项目采用 Apache Licence 2.0 授权协议:<br>
Apache Licence是著名的非盈利开源组织Apache采用的协议。该协议和BSD类似，同样鼓励代码共享和尊重原作者的著作权，同样允许代码修改，再发布（作为开源或商业软件）[更多...](http://www.oschina.net/question/12_2828)<br>
  Copyright (c) 2014, KJFrameForAndroid Open Source Project, Zhang Tao.
 
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
	   
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.

## 关于作者kymjs
blog：http://my.oschina.net/kymjs/blog<br>
email：kymjs123@gmail.com<br>
forum/bbs: [http://tieba.baidu.com/f?kw=kym%BD%A9%CA%AC&fr=index](http://tieba.baidu.com/f?kw=kym%BD%A9%CA%AC&fr=index)<br>
本框架目前仅仅是一个开发阶段，仅仅是实现了插件Activity的运行（原理上来说，动态注册的广播也可以运行），而contentProvider没办法使用，这些都仍在研究中。<br>
接下来打算实现的是：Activity launchModer的支持，Service的支持。<br>
在未来的某一天，也许会将这个CJFrameForAndroid插件框架与KJFrameForAndroid快捷开发框架合并，组成一个更完善应用开发框架，对自己说：加油！<br>