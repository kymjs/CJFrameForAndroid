package com.lody.plugin;

import java.util.Collection;
import java.util.Iterator;

public abstract class EasyFor<E> {

    public EasyFor(Collection<E> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            onNewElement(iterator.next());
        }
    }

    public abstract void onNewElement(E element);
}
