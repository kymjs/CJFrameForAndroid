#[![OSL](http://www.kymjs.com/image/logo_s.png)](http://www.kymjs.com/works/)    
CJFrameForAndroid简介
**CJFrameForAndroid** 是一个实现android插件化开发的框架。使用CJFrameForAndroid，apk动态加载不再是难题，更重要的是可以轻松实现插件与APP项目之间的解耦。<br>

---

CJFrame不再更新，尽管我不喜欢360，但仍推荐大家使用360的黑科技[DroidPlugin](https://github.com/Qihoo360/DroidPlugin)  四大组件均可支持插件化。

---

## CJFrameForAndroid 相关链接
* blog：http://blog.kymjs.com<br>
* QQ群：[257053751](http://jq.qq.com/?_wv=1027&k=WoM2Aa)(开发者群1)，[201055521](http://jq.qq.com/?_wv=1027&k=MBVdpK)(开发者群2)

# 原理描述
CJFrameForAndroid的实现原理是通过类加载器，动态加载存在于SD卡上的apk包中的Activity。通过使用一个托管所，插件Activity全部事务(包括声明周期与交互事件)将交由托管所来处理，间接实现插件的运行。更多介绍:[CJFrameForAndroid原理介绍](http://blog.kymjs.com/code/2014/10/15/01/)<br>
一句话概括：CJFrameForAndroid中的托管所，复制了插件中的Activity，来替代插件中的Activity与用户交互。<br>

# 框架使用
1. 需要注意的是，插件中所涉及的权限，都需要在宿主中加以声明。宿主Manifest文件写法请参考：[AndroidManifest.xml](https://github.com/kymjs/CJFrameForAndroid/blob/master/AndroidManifest.xml)  
2. 你只需要在你项目想要启动插件的任意位置(UI线程中)，例如Button的Onclick事件中加入如下代码即可。
```java
/**
 * @param context 上下文对象
 * @param path  插件所在的绝对路径
 */
CJActivityUtils.startPlugin(context,path);
```


## 许可
  Copyright (c) 2014, Zhang Tao.
 
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
	   
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
