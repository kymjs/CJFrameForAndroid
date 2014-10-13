package org.kymjs.aframe.plugin.activity;


public class ActivityStack {
    // private static List<ActivityWrapper> atyStack;
    //
    // private ActivityStack() {}
    //
    // private static class ManagerHolder {
    // private static final ActivityStack instance = new ActivityStack();
    // }
    //
    // public static ActivityStack create() {
    // return ManagerHolder.instance;
    // }
    //
    // public static void lunch(CJActivity aty) {
    // if (atyStack == null) {
    // atyStack = new ArrayList<ActivityWrapper>();
    // add(aty);
    // } else {
    // query(aty);
    // }
    // }
    //
    // private static void query(CJActivity aty) {
    // int count = atyStack.size() - 1;
    // for (int i = count; i >= 0; i--) {
    // ActivityWrapper wrapper = atyStack.get(i);
    // if (wrapper.where == ActivityWrapper.FROM_PLUGIN
    // && wrapper.aty.equals(aty)) {
    // switch (wrapper.mode) {
    // case STANDARD: // 标准模式，什么都不做
    // add(aty);
    // break;
    // case SINGLETOP:
    // if (i == count) {
    // // 如果栈顶就是本类，则不再添加
    // } else {
    // add(aty);
    // }
    // break;
    // case SINGLETASK:
    // for (int j = i + 1; j < count; j++) {
    // finish(j, atyStack.get(j));
    // }
    // break;
    // case SINGLEINSTANCE:
    // ActivityWrapper temp = atyStack.get(i);
    // for (int j = i + 1; j < count; j++) {
    // atyStack.set(j - 1, atyStack.get(j));
    // }
    // atyStack.set(count - 1, temp);
    // break;
    // }
    // }
    // }
    // }
    //
    // private static void add(CJActivity aty) {
    // atyStack.add(new ActivityWrapper(aty));
    // }
    //
    // private static void finish(int index, ActivityWrapper wrapper) {
    // wrapper.aty.finish();
    // atyStack.remove(index);
    // }
    //
    // private static class ActivityWrapper {
    // private static final int FROM_PLUGIN = 0; // 设置为int类型，方便以后考虑插件间通信
    // private static final int FROM_APP = 1;
    //
    // int where; // 用来标识当前放入栈的是插件Activity还是APP的Activity
    // Activity aty;
    // LunchMode mode;
    //
    // public ActivityWrapper(CJActivity aty) {
    // if (aty instanceof CJActivity) {
    // where = FROM_PLUGIN;
    // this.mode = ((CJActivity) aty).getLunchMode();
    // } else {
    // where = FROM_APP;
    // this.mode = LunchMode.STANDARD;
    // }
    // this.aty = aty;
    // }
    // }
}
