package com.tencent.shadow.sample.plugin.app.lib;

import android.os.Bundle;
import android.util.Log;
import com.qq.e.comm.managers.GDTADManager;
import com.tencent.shadow.sample.host.lib.ITestInterface;
import com.tencent.shadow.sample.plugin.app.lib.gallery.TestApplication;
import com.tencent.shadow.sample.plugin.app.lib.gallery.util.ToastUtil;
import java.lang.reflect.Field;

public class Test implements ITestInterface {

    @Override
    public void showToast() {
        ToastUtil.showToast(TestApplication.getInstance(), "hello plugin");
    }


    public static void doAction(Bundle bundle) {
        try {
            Log.e("Test",  "doAction");
            GDTADManager gdtadManager = GDTADManager.getInstance();
            Field field = gdtadManager.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(gdtadManager, TestApplication.getInstance());
        } catch (Exception e) {
            Log.e("Test", e + "");
        }
    }
}
