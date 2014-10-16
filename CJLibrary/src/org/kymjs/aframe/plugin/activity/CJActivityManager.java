package org.kymjs.aframe.plugin.activity;

import java.util.ArrayList;
import java.util.List;

public class CJActivityManager {
    private static List<I_CJActivity> atyStack;

    private CJActivityManager() {}

    private static class ManagerHolder {
        private static final CJActivityManager instance = new CJActivityManager();
    }

    public static CJActivityManager create() {
        return ManagerHolder.instance;
    }

    public I_CJActivity launch(I_CJActivity pluginAty) {
        if (atyStack == null) {
            atyStack = new ArrayList<I_CJActivity>();
            add(pluginAty);
            return null;
        } else {
            return query(pluginAty);
        }
    }

    /**
     * 添加一个插件Activity到返回栈
     * 
     * @param aty
     */
    private void add(I_CJActivity aty) {
        atyStack.add(aty);
    }

    /**
     * 核心方法
     * 
     * @param aty
     * @return
     */
    private I_CJActivity query(I_CJActivity aty) {
        I_CJActivity res = null;
        int count = atyStack.size() - 1;
        for (int i = count; i >= 0; i--) {
            I_CJActivity temp = atyStack.get(i);
            if (temp.getClass().equals(aty.getClass())) {
                switch (temp.getLaunchMode()) {
                case STANDARD:
                    add(aty);
                    break;
                case SINGLETOP:
                    if (i == count) {
                        res = temp;
                    } else {
                        add(aty);
                    }
                    break;
                case SINGLETASK:
                    for (int j = i + 1; j < count; j++) {
                        finish(j, atyStack.get(j));
                    }
                    res = temp;
                    break;
                case SINGLEINSTANCE:
                    for (int j = i + 1; j < count; j++) {
                        atyStack.set(j - 1, atyStack.get(j));
                    }
                    atyStack.set(count - 1, temp);
                    res = temp;
                    break;
                }
                break; // break loop
            } else {
                add(aty);
            }
        }
        return res;
    }

    /**
     * 结束Stack中指定index的Activity
     * 
     * @param index
     *            Stack中指定Activity的下标
     * @param plugin
     *            指定的插件Activity
     */
    private void finish(int index, I_CJActivity plugin) {
        if (plugin instanceof CJActivity) {
            ((CJActivity) plugin).that.finish();
        }
        atyStack.remove(index);
    }

    /**
     * 结束掉指定的插件Activity
     * 
     * @param activity
     */
    public void finish(I_CJActivity activity) {
        if (activity != null) {
            atyStack.remove(activity);
            if (activity instanceof CJActivity) {
                ((CJActivity) activity).that.finish();
            }
            activity = null;
        }
    }

    /**
     * 结束指定的插件Activity
     */
    public void finishActivity(Class<?> cls) {
        for (I_CJActivity activity : atyStack) {
            if (activity.getClass().equals(cls)) {
                finish(activity);
            }
        }
    }
}
