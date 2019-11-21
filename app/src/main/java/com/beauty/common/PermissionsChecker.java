package com.beauty.common;

import android.Manifest;
import android.app.Activity;
import android.util.Log;

import com.beauty.utils.DLog;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.app.ActivityCompat;

public class PermissionsChecker {
    private final static String TAG = "PermissionsChecker";

    public void checkPermissions(Activity activity){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
//            ActivityCompat.requestPermissions(activity,
//                new String[]{Manifest.permission.CAMERA},  REQUEST_CAMERA);
        } else {
            // Camera permission has not been granted yet. Request it directly.
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
        // END_INCLUDE(camera_permission_request)
    }
}
