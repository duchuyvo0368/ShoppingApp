package com.voduchuy.nikeshopping.ui.view.scroll;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ScrollingView;
import androidx.core.widget.NestedScrollView;

import java.util.List;

public class ObservableScrollView extends NestedScrollView implements Scrollable {
    private int mPrevScrollY;
    private int mScrollY;
    private ObservableScrollViewCallbacks mCallbacks;
    private List<ObservableScrollViewCallbacks> mCallbacksCollection;
    private ScrollState mScrollState;
    private boolean mFirsScroll;
    private boolean mDragging;
    private boolean mIntercepted;
    private MotionEvent mPrevMoveEvent;
    private ViewGroup mTouchInterceptionViewGroup;

    public ObservableScrollView(@NonNull Context context) {
        super(context);
    }

    public ObservableScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setScrollViewCallbacks(ObservableScrollViewCallbacks listener) {

    }

    @Override
    public void addScrollViewCallbacks(ObservableScrollViewCallbacks listener) {

    }

    @Override
    public void removeScrollViewCallbacks(ObservableScrollViewCallbacks listener) {

    }

    @Override
    public void clearScrollViewCallbacks() {

    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        mPrevScrollY = ss.prevScrollY;
        mScrollY = ss.scrollY;
        super.onRestoreInstanceState(state);
    }

    @NonNull
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable suParcelable = super.onSaveInstanceState();
        SavedState ss = new SavedState(suParcelable);
        ss.prevScrollY = mPrevScrollY;
        ss.scrollY = mScrollY;
        return ss;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (hasNoCallbacks()) {
            return;
        }
        mScrollY = t;
        dispatchOnScrollChanged(t, mFirsScroll, mDragging);
        if (mFirsScroll) {
            mFirsScroll = false;
        }
        if (mPrevScrollY < t) {
            mScrollState = ScrollState.UP;
        } else if(t<mPrevScrollY){
            mScrollState=ScrollState.DOWN;
        }
        mPrevScrollY=t;

    }

    private void dispatchOnScrollChanged(int t, boolean mFirsScroll, boolean mDragging) {

    }

    private boolean hasNoCallbacks() {
        return mCallbacks == null && mCallbacksCollection == null;
    }

    @Override
    public void scrollVerticallyTo(int y) {

    }

    @Override
    public int getCurrentScrollY() {
        return 0;
    }

    @Override
    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {

    }

    static class SavedState extends BaseSavedState {
        int prevScrollY;
        int scrollY;


        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            prevScrollY = in.readInt();
            scrollY = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(prevScrollY);
            out.writeInt(scrollY);
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

    }
}