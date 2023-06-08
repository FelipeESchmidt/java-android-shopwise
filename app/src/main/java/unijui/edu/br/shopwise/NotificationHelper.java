package unijui.edu.br.shopwise;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class NotificationHelper {
    private static final String CHANNEL_ID = "my_app_channel";
    private static final String CHANNEL_NAME = "My App Notifications";
    private static final String CHANNEL_DESCRIPTION = "Notifications for My App";
    private static final int PERMISSION_REQUEST_CODE = 123;

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static Notification createNotification(Context context, String title, String text) {
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setAutoCancel(true);
        }

        return builder.build();
    }

    public static void sendNotification(Context context, Notification notification) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
            return;
        }

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        int notificationId = (int) System.currentTimeMillis();
        Notification notification = createNotification(context);
        notificationManager.notify(notificationId, notification);
    }
}
