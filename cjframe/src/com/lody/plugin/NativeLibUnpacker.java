package com.lody.plugin;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import android.util.Log;

import com.lody.plugin.api.LFileTools;

/**
 * Created by lody on 2015/4/4.
 * 
 * 根据CPU型号解压apk内的相应的.so文件
 */
public class NativeLibUnpacker {

    public static final String TAG = NativeLibUnpacker.class.getSimpleName();
    public static final String DEF_ARCH_1 = "armeabi";
    public static final String DEF_ARCH_2 = "armeabi-v7a";
    public static String ARCH = System.getProperty("os.arch");

    public static void unPackSOFromApk(String apkPath, String toPath) {

        Log.i(TAG, "CPU is " + ARCH);

        try {
            ZipFile apk = new ZipFile(new File(apkPath));
            boolean hasLib = extractLibFile(apk, new File(toPath));
            if (hasLib) {
                Log.i(TAG, "The plugin is contains .so files.");
            } else {
                Log.i(TAG, "The plugin isn't contain any .so files.");
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    private static boolean extractLibFile(ZipFile zip, File to)
            throws ZipException, IOException {

        Map<String, List<ZipEntry>> archLibEntries = new HashMap<String, List<ZipEntry>>();
        for (Enumeration<? extends ZipEntry> e = zip.entries(); e
                .hasMoreElements();) {
            ZipEntry entry = e.nextElement();
            String name = entry.getName();
            // Log.i(TAG,"found file :" + name);
            if (name.startsWith("/")) {
                name = name.substring(1);
            }
            if (name.startsWith("lib/")) {
                if (entry.isDirectory()) {
                    continue;
                }
                int sp = name.indexOf('/', 4);
                String en2add;
                if (sp > 0) {
                    String osArch = name.substring(4, sp);
                    en2add = osArch.toLowerCase();
                } else {
                    en2add = DEF_ARCH_1;
                }
                List<ZipEntry> zipEntries = archLibEntries.get(en2add);
                if (zipEntries == null) {
                    zipEntries = new LinkedList<ZipEntry>();
                    archLibEntries.put(en2add, zipEntries);
                }
                zipEntries.add(entry);
            }
        }
        /*
         * new EasyFor<List<ZipEntry>>(archLibEntries.values()) {
         * 
         * @Override public void onNewElement(List<ZipEntry> element) { new
         * EasyFor<ZipEntry>(element){
         * 
         * @Override public void onNewElement(ZipEntry element) {
         * Log.i(TAG,element.getName()); } }; } };
         */

        List<ZipEntry> libEntries = archLibEntries.get(ARCH.toLowerCase());
        if (libEntries == null) {
            libEntries = archLibEntries.get(DEF_ARCH_1);
            if (libEntries == null) {
                libEntries = archLibEntries.get(DEF_ARCH_2);
            }
        }
        boolean hasLib = false;// 是否包含so
        if (libEntries != null) {
            hasLib = true;
            if (!to.exists()) {
                to.mkdirs();
            }
            for (ZipEntry libEntry : libEntries) {
                String name = libEntry.getName();
                String pureName = name.substring(name.lastIndexOf('/') + 1);
                File target = new File(to, pureName);
                LFileTools.writeToFile(zip.getInputStream(libEntry), target);
            }
        }

        return hasLib;
    }
}
