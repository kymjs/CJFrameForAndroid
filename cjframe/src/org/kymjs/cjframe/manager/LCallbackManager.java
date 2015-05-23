/*
 * Copyright (c) 2014, 张涛, lody.
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
package org.kymjs.cjframe.manager;

import java.util.ArrayList;
import java.util.Iterator;

import org.kymjs.cjframe.control.I_CJActivity;

import android.os.Bundle;
import android.view.KeyEvent;


/**
 * Created by lody on 2015/3/31.
 */
public class LCallbackManager {
    private static final ArrayList<I_CJActivity> pluginsMapForPath = new ArrayList<I_CJActivity>();

    public static void addActivityCallback(I_CJActivity callback) {
        pluginsMapForPath.add(callback);
    }

    public static void removeActivityCallback(I_CJActivity callback) {
        pluginsMapForPath.remove(callback);
    }

    public static void callAllOnCreate(final Bundle args) {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnCreate(args);
        }
    }

    public static void callAllOnStart() {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnStart();
        }
    }

    public static void callAllOnStop() {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnStop();
        }
    }

    public static void callAllOnResume() {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnResume();
        }
    }

    public static void callAllOnRestart() {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnRestart();
        }
    }

    public static void callAllOnBackPressed() {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnBackPressed();
        }
    }

    public static void callAllOnDestroy() {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnDestroy();
        }
    }

    public static void callAllOnSaveInstanceState(final Bundle out) {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnSaveInstanceState(out);
        }
    }

    public static void callAllOnRestoreInstanceState(
            final Bundle savedInstanceState) {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnRestoreInstanceState(savedInstanceState);
        }
    }

    public static void callAllOnKeyDown(final int keyCode, final KeyEvent event) {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnKeyDown(keyCode, event);
        }
    }

    public static void callAllOnPause() {
        Iterator<I_CJActivity> iterator = pluginsMapForPath.iterator();
        while (iterator.hasNext()) {
            iterator.next().callOnPause();
        }
    }
}