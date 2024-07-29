package com.b1nary;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class myAccessibilityService extends AccessibilityService {
    private void showToast(String message, Boolean isLong) {
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast.makeText(this, message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //sadece click alıyor
        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            showToast(event.getText().toString(), false);
        }
    }

    @Override
    public void onInterrupt() {
        Log.d("AccessibilityService", "Interrupt");
    }

    @Override
    public void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.notificationTimeout = 100;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        this.setServiceInfo(info);
    }
}
