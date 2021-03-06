package com.ideal.jalen.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ideal.jalen.R;
import com.ideal.jalen.animator.activity.AnimatorActivity;
import com.ideal.jalen.attrsstyle.activity.AttrsStyleActivity;
import com.ideal.jalen.bitmap.activity.BitmapActivity;
import com.ideal.jalen.canvas.activity.CanvasActivity;
import com.ideal.jalen.constant.IPCConstant;
import com.ideal.jalen.entity.appstatus.AppStatusConstant;
import com.ideal.jalen.ipc.aidl.AIDLService;
import com.ideal.jalen.ipc.aidl.AIDLServiceConnection;
import com.ideal.jalen.ipc.messenger.MessengerService;
import com.ideal.jalen.ipc.messenger.MessengerServiceConnection;
import com.ideal.jalen.material.activity.MaterialActivity;
import com.ideal.jalen.matrix.activity.MatrixActivity;
import com.ideal.jalen.myactivity.MyActivity;
import com.ideal.jalen.progress.activity.ProgressBarActivity;
import com.ideal.jalen.splash.SplashActivity;
import com.ideal.jalen.test.Test1Acitivity;
import com.ideal.jalen.thread.handlerThread.HandlerThreadActivity;
import com.ideal.jalen.utils.DialogUtil;
import com.ideal.jalen.view.activity.ViewActivity;

/**
 * author: Jalen
 * date: 2017/6/22. 16:18
 * describe: 主页面
 */
public class MainActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int action = intent.getIntExtra(AppStatusConstant.KEY_HOME_ACTION, AppStatusConstant.ACTION_BACK_TO_HOME);
        switch (action) {
            case AppStatusConstant.ACTION_RESTART_APP:
                restartApp();
                break;
            default:
                break;
        }
    }

    @Override
    protected void restartApp() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    public void onClickMatrixMain(View view) {
        startActivity(MatrixActivity.class);
    }

    public void onClickAnimatorMain(View view) {
        startActivity(AnimatorActivity.class);
    }

    public void onClickMaterial(View view) {
        startActivity(MaterialActivity.class);
    }

    public void onClickCanvas(View view) {
        startActivity(CanvasActivity.class);
    }

    public void onClickAttrsStyle(View view) {
        startActivity(AttrsStyleActivity.class);
    }

    public void onClickProgressBar(View view) {
        startActivity(ProgressBarActivity.class);
    }

    public void onClickActivity(View view) {
        startActivity(MyActivity.class);
    }

    public void onClickView(View view) {
        startActivity(ViewActivity.class);
    }

    public void onClickBitmapCompress(View view) {
        startActivity(BitmapActivity.class);
    }

    public void onClickInterProcessCommunication(View view) {
        DialogUtil.getInstance().showDialog(MainActivity.this, getString(R.string.ipc), getResources().getStringArray(R.array.ipc_type), new DialogUtil.ReturnClickPosition() {
            @Override
            public void clickPosition(int position) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case IPCConstant.INTENT:
                        Intent intent = new Intent();
                        ComponentName cn = new ComponentName("com.ideal.jalen",
                                "com.ideal.jalen.ipc.intent.IntentActivity1");
                        intent.setComponent(cn);
                        startActivity(intent);

//                        startActivity(new Intent(getActivity(), IntentActivity1.class));
                        break;
                    case IPCConstant.MESSENGER:
                        Intent myIntent = new Intent(getActivity(), MessengerService.class);
                        MessengerServiceConnection messengerServiceConnection = new MessengerServiceConnection();
                        bindService(myIntent, messengerServiceConnection, BIND_AUTO_CREATE);
                        break;
                    case IPCConstant.AIDL:
                        Intent intentAIDL = new Intent(getActivity(), AIDLService.class);
                        AIDLServiceConnection aidlServiceConnection = new AIDLServiceConnection();
                        bindService(intentAIDL, aidlServiceConnection, BIND_AUTO_CREATE);
                        break;
                    case IPCConstant.CONTENT_PROVIDER:
                        break;
                    case IPCConstant.SOCKET:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void onClickThread(View view) {
        DialogUtil.getInstance().showDialog(getActivity(), getString(R.string.thread),//
                getResources().getStringArray(R.array.thread_type), new DialogUtil.ReturnClickPosition() {
                    @Override
                    public void clickPosition(int position) {
                        switch (position) {
                            case ThreadType.HANDLER_THREAD:
                                startActivity(HandlerThreadActivity.class);
                                break;
                            default:
                                break;
                        }
                    }
                }
        );
    }

    public void onClickTest(View view) {
        startActivity(Test1Acitivity.class);
    }

    private void startActivity(Class<?> className) {
        startActivity(new Intent(getActivity(), className));
    }

    private static class ThreadType {
        /**
         * HandlerThread
         */
        public static final int HANDLER_THREAD = 0;
    }
}
