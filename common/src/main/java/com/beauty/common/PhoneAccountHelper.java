package com.beauty.common;

public class PhoneAccountHelper {

    private static PhoneAccountHelper sInstance = new PhoneAccountHelper();

    public static PhoneAccountHelper getInstance(){
        return sInstance;
    }

    public PhoneAccountHelper(){
    }
}
