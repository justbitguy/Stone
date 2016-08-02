package com.just.stone.service;

import android.app.Service;
import android.app.usage.UsageEvents;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;

import com.just.stone.model.eventbus.OnNotifyService;
import com.just.stone.util.LogUtil;

import de.greenrobot.event.EventBus;

/**
 * Created by Zac on 2016/8/2.
 */
public class LocalService extends Service {

    private final IBinder mBinder = new LocalBinder();

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(StoneAccessibilityService.getCallBackAction(this));
//        this.registerReceiver(mBroadCastReceiver, intentFilter);

        // The service is being created
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        return mBinder;
    }

    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        EventBus.getDefault().unregister(this);
    }

    public void onEventAsync(OnNotifyService event){
        LogUtil.d("service", "receive event: OnNotifyService");
    }

//    private BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals(StoneAccessibilityService.getCallBackAction(context))){
//                LogUtil.d("access", "receive broadcast");
//                if (intent.getIntExtra("result", 1) == 1) {
//                    SystemClock.sleep(2000);
////                    mPage2.continueKill();
//                    // TODO: 2016/8/2
//                }
//            }
//        }
//    };
}
