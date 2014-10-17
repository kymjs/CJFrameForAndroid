/*
 * Copyright (c) 2014, CJFrameForAndroid 张涛 (kymjs123@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kymjs.aframe.plugin.activity;

import java.util.LinkedList;
import java.util.List;

/**
 * 插件Activity的返回栈，用于管理插件Activity的launchMode<br>
 * 
 * <b>创建时间</b> 2014-10-15 <br>
 * 
 * @author kymjs(kymjs123@gmail.com)
 * @version 1.0
 */
public class CJBackStack {
    // 用链表来模拟一个Activity返回栈
    private static List<I_CJActivity> atyStack;

    private CJBackStack() {}

    private static class StackHolder {
        private static final CJBackStack instance = new CJBackStack();
    }

    public static CJBackStack create() {
        return StackHolder.instance;
    }

    /**
     * 根据launchMode去启动一个插件Activity
     * 
     * @param pluginAty
     *            要启动的插件Activity
     * @return 返回空表示成功添加一个插件Activity到返回栈栈顶而没有改变栈中其他元素
     */
    public I_CJActivity launch(I_CJActivity pluginAty) {
        if (atyStack == null) {
            atyStack = new LinkedList<I_CJActivity>();
            add(pluginAty);
            return null;
        } else {
            return query(pluginAty);
        }
    }

    private void add(I_CJActivity pluginAty) {
        atyStack.add(pluginAty);
    }

    /**
     * 首先将aty(插件)引用添加到链表尾,因为不管是何种launchmode,最终都是在栈顶.<br>
     * 从链表倒数第二个(未添加前的最后一个)开始遍历,若已有该aty的引用,则开始处理launchmode的情况。<br>
     */
    private I_CJActivity query(I_CJActivity pluginAty) {
        atyStack.add(pluginAty);
        I_CJActivity inStackPluginAty = null;
        for (int i = atyStack.size() - 2; i >= 0; i--) {
            if (atyStack.get(i).getClass()
                    .equals(pluginAty.getClass())) {
                switch (pluginAty.getLaunchMode()) {
                case STANDARD:// 什么也不做
                    break;
                case SINGLETOP:// 栈顶唯一
                    if (i == atyStack.size() - 2) {
                        remove(i);
                    }
                    break;
                case SINGLETASK: // 栈唯一
                    // 这里由于每次remove(),atyStack.size()会随之减小,故省略for语句第三段
                    for (int j = i; j < atyStack.size() - 1;) {
                        remove(j);
                    }
                    break;
                case SINGLEINSTANCE:// 应用唯一
                    remove(i);
                    break;
                }
            }
        }
        return inStackPluginAty;
    }

    private void remove(int index) {
        I_CJActivity aty = atyStack.get(index);
        atyStack.remove(index);
        if (aty instanceof CJActivity) {
            ((CJActivity) aty).finish();
        }
    }

    /**
     * 从返回栈栈顶开始移除第一个出现的参数aty<br>
     * 此处用对象比较而不是clazz比较，防止在返回栈中有两个Activity而删错<br>
     * 减2的原因：不能是栈顶的那个aty
     * 
     * @param aty
     */
    public void finish(I_CJActivity aty) {
        for (int i = atyStack.size() - 2; i >= 0; i--) {
            if (aty.equals(atyStack.get(i))) {
                remove(i);
            }
        }
    }
}
