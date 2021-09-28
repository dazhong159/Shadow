package com.tencent.shadow.sample.plugin.app.lib;

import android.os.Bundle;
import com.tencent.shadow.sample.host.lib.ITestInterface;
import com.tencent.shadow.sample.plugin.app.lib.gallery.TestApplication;
import com.tencent.shadow.sample.plugin.app.lib.gallery.util.ToastUtil;

public class Test implements ITestInterface {

    @Override
    public void showToast() {
        ToastUtil.showToast(TestApplication.getInstance(), "hello plugin");
    }


    public static void doAction(Bundle bundle) {
        ToastUtil.showToast(TestApplication.getInstance(), "hello plugin");
    }
}
