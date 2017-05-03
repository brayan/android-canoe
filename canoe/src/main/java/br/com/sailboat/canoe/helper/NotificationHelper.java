package br.com.sailboat.canoe.helper;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper {

    public static void throwNotification(Context context, int id, NotificationCompat.Builder builder) {
        getNotificationManager(context).notify(id, builder.build());
    }

    public static void closeNotifications(Context context, int id) {
        getNotificationManager(context).cancel(id);
    }

    private static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

}
