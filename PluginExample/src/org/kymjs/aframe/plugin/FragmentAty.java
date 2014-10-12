package org.kymjs.aframe.plugin;

import org.kymjs.aframe.plugin.activity.CJActivity;
import org.kymjs.aframe.plugin.example.R;
import org.kymjs.aframe.plugin.fragment.TestFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class FragmentAty extends CJActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.aty_fragment);
        changeFragment(R.id.content, new TestFragment());
    }

    protected void changeFragment(int resView, Fragment targetFragment) {
        FragmentTransaction transaction = that.getFragmentManager()
                .beginTransaction();
        transaction.replace(resView, targetFragment, targetFragment.getClass()
                .getName());
        transaction.commit();
    }
}
