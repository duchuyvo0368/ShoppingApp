package com.voduchuy.nikeshopping.ui.view.scroll;

import android.view.ViewGroup;

interface Scrollable {
    void  setScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    void addScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    void removeScrollViewCallbacks(ObservableScrollViewCallbacks listener);
    void clearScrollViewCallbacks();
    void scrollVerticallyTo(int y);
    int getCurrentScrollY();
    void setTouchInterceptionViewGroup(ViewGroup viewGroup);
}