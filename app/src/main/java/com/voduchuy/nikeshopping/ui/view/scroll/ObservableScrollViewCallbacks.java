package com.voduchuy.nikeshopping.ui.view.scroll;

public interface ObservableScrollViewCallbacks {

    void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging);

    void onDownMotionEvent();

    void onUpOrCancelMotionEvent(ScrollState scrollState);
}
