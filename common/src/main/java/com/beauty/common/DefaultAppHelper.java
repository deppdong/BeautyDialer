package com.beauty.common;

import android.app.Activity;
import android.app.role.RoleManager;
import android.content.Intent;
import android.os.Build;
import android.telecom.TelecomManager;

import static android.content.Context.ROLE_SERVICE;

public class DefaultAppHelper {
    private static DefaultAppHelper sInstance = new DefaultAppHelper();

    DefaultAppHelper() {
    }

    public void requestDefaultDialer(Activity activity) {
        if (activity == null) {
            return;
        }

        String packageName = activity.getPackageName();
        String curDefDialer = TelecomHelper.getInstance().getDefaultDialer();
        if (packageName.equals(curDefDialer)) {
            ToastUtils.showToast("已设置为默认拨号盘");
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            RoleManager roleManager = (RoleManager)activity.getSystemService(ROLE_SERVICE);
            Intent intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER);
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName);
            activity.startActivityForResult(intent, 1);
        } else {
            Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName);
            try {
                activity.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
