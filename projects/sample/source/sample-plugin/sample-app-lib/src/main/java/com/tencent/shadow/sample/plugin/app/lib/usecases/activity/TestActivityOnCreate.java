/*
 * Tencent is pleased to support the open source community by making Tencent Shadow available.
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.tencent.shadow.sample.plugin.app.lib.usecases.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.widget.FrameLayout.LayoutParams;
import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.comm.constants.CustomPkgConstants;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.util.AdError;
import com.qq.e.union.adapter.util.PxUtils;
import com.tencent.shadow.sample.plugin.app.lib.R;
import com.tencent.shadow.sample.plugin.app.lib.gallery.cases.entity.UseCase;
import com.tencent.shadow.sample.plugin.app.lib.gallery.util.ToastUtil;
import java.util.Map;

public class TestActivityOnCreate extends Activity {

    private static final String TAG = "TestActivityOnCreate";
    private static final String APP_ID = "1200021041";
    private static boolean initialized = false;
    private RewardVideoAD rewardVideoAD;

    private UnifiedBannerView unifiedBannerView;

    public static class Case extends UseCase{
        @Override
        public String getName() {
            return "生命周期测试";
        }

        @Override
        public String getSummary() {
            return "测试Activity的生命周期方法是否正确回调";
        }

        @Override
        public Class getPageClass() {
            return TestActivityOnCreate.class;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_lifecycle);
        ToastUtil.showToast(this,"onCreate");

        initInner();


        rewardVideoAD = new RewardVideoAD(this, "100315",
                new RewardVideoADListener() {
                    @Override
                    public void onADClick() {
                        Log.i(TAG, "onADClick");
                    }

                    @Override
                    public void onADClose() {

                    }

                    @Override
                    public void onADExpose() {
                        Log.i(TAG, "onADExpose");
                    }

                    @Override
                    public void onADLoad() {
                        Log.i(TAG, "onADLoad");
                        rewardVideoAD.showAD();
                    }

                    @Override
                    public void onADShow() {

                    }

                    @Override
                    public void onError(AdError adError) {
                        if (adError != null) {
                            Log.i(TAG, "onError " + adError.getErrorCode() + ", " + adError
                                    .getErrorMsg());
                        }
                    }

                    @Override
                    public void onReward(Map<String, Object> map) {

                    }

                    @Override
                    public void onVideoCached() {

                    }

                    @Override
                    public void onVideoComplete() {

                    }
                }, true);
        rewardVideoAD.loadAD();

        unifiedBannerView = new UnifiedBannerView(this, "100298", new UnifiedBannerADListener() {
            @Override
            public void onADClicked() {

            }

            @Override
            public void onADCloseOverlay() {

            }

            @Override
            public void onADClosed() {

            }

            @Override
            public void onADExposure() {

            }

            @Override
            public void onADLeftApplication() {

            }

            @Override
            public void onADOpenOverlay() {

            }

            @Override
            public void onADReceive() {
                Log.i(TAG, "onADReceive"
                        + ", ecpm = " + unifiedBannerView.getECPM()
                        + ", ecpmLevel = " + unifiedBannerView.getECPMLevel()
                        + ", adn = " + unifiedBannerView.getAdNetWorkName());
            }

            @Override
            public void onNoAD(AdError adError) {
                Log.i(TAG, "onNoAD");
            }
        });
        LayoutParams layoutParams = new LayoutParams(PxUtils.dpToPx(this, 1080),
                PxUtils.dpToPx(this, 100));
        addContentView(unifiedBannerView, layoutParams);
        unifiedBannerView.loadAD();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ToastUtil.showToast(this,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ToastUtil.showToast(this,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ToastUtil.showToast(this,"onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ToastUtil.showToast(this,"onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ToastUtil.showToast(this,"onRestoreInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ToastUtil.showToast(this,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.showToast(this,"onDestroy");
    }

    public void initInner() {
        if (!initialized) {
            Log.i(TAG, "initInner");
            //GDTADManager.getInstance().initWith(getApplicationContext(), APP_ID);
            initialized = true;
        }
    }
}
