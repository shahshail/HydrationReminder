package com.geekathlon.shailshah.hydrationreminder;

import android.content.Context;
import android.os.AsyncTask;

import com.geekathlon.shailshah.hydrationreminder.sync.ReminderTask;

/**
 * Created by shailshah on 10/9/17.
 */

public class WaterReminderFirebaseJobServices extends com.firebase.jobdispatcher.JobService {
    private AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = WaterReminderFirebaseJobServices.this;

                ReminderTask.executeTask(context,ReminderTask.ACTION_CHARGING_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job,false);
            }
        };

        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}
