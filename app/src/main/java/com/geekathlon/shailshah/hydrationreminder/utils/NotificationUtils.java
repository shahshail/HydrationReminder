package com.geekathlon.shailshah.hydrationreminder.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.geekathlon.shailshah.hydrationreminder.MainActivity;
import com.geekathlon.shailshah.hydrationreminder.R;
import com.geekathlon.shailshah.hydrationreminder.sync.ReminderTask;
import com.geekathlon.shailshah.hydrationreminder.sync.WaterReminderIntentService;

/**
 * Created by shailshah on 10/9/17.
 */

public class NotificationUtils {

    private static final int WATER_REMINDER_ID = 3417;
    private static final int WATER_REMINDER_NOTIFICATION_ID = 1138;
    private static final int ACTION_DRINK_PENDING_INTENT_ID = 1;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;

    public static void remindUserBecauseCharging(Context context) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(drinkWaterAction(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);


        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
        }
    }

    //Earse all the notification

    public static void clearAllNotification(Context context)
    {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }

    private static PendingIntent contentIntent(Context context)
    {

        Intent startActivityIntent = new Intent(context, MainActivity.class);
         return PendingIntent.getActivity(
                 context,
                 WATER_REMINDER_ID,
                 startActivityIntent,
                 PendingIntent.FLAG_UPDATE_CURRENT
         );
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_local_drink_black_24px);
        return largeIcon;
    }

    //Notification Action Utils

    private static NotificationCompat.Action ignoreReminderAction(Context context)
    {
        Intent ignoreReminderIntent = new Intent(context, WaterReminderIntentService.class);

        ignoreReminderIntent.setAction(ReminderTask.ACTION_DISMISS_NOTIFICATION);

        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
               ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

       NotificationCompat.Action ignoreReminferAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,"No Thanx",
               ignoreReminderPendingIntent);

        return ignoreReminferAction;
    }

    private static NotificationCompat.Action drinkWaterAction(Context context)
    {
        Intent incrementWaterCount = new Intent(context,WaterReminderIntentService.class);

        incrementWaterCount.setAction(ReminderTask.ACTION_INCREMENT_WATER_COUNT);

        PendingIntent drinkWaterPendingIntent = PendingIntent.getService(
                context,
                ACTION_DRINK_PENDING_INTENT_ID,
                incrementWaterCount,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Action drinkWaterAction = new NotificationCompat.Action(R.drawable.ic_local_drink_black_24px,"I did it!",
                drinkWaterPendingIntent);

        return drinkWaterAction;
    }

}
