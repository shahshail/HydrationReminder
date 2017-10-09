package com.geekathlon.shailshah.hydrationreminder.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by shailshah on 10/8/17.
 */

public class WaterReminderIntentService extends IntentService {
    public WaterReminderIntentService() {
        super("WaterReminderIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String action = intent.getAction();
        ReminderTask.executeTask(this,action);
    }
}
