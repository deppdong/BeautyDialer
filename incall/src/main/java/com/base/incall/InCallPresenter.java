package com.base.incall;

public final class InCallPresenter {

    private static InCallPresenter sInstance = new InCallPresenter();

    private boolean mCanAddCall = true;

    public static InCallPresenter getInstance() {
        return sInstance;
    }

    protected void setUp(){

    }

    protected  void tearDown(){

    }

    public void onBringToForeground(boolean flag) {

    }

    public void onCanAddCallChanged(boolean canAddCall){
        mCanAddCall = canAddCall;
    }
}
