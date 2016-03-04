package com.shineson.jason.gravitysection.view.externalcall;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.shineson.jason.gravitysection.service.floatwinservice.FloatWinService;

public class ExternalCallActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            intentActionView(intent);
        } else if (Intent.ACTION_SEND.equals(action)) {
            intentActionSend(intent);
        }

        finish();
    }

    private void intentActionSend(Intent intent) {
        ClipData clipData = intent.getClipData();
        ClipData.Item item = clipData.getItemAt(0);
        String url = (String) item.getText();

        Intent intentService = new Intent(ExternalCallActivity.this, FloatWinService.class);
        startService(intentService);
    }

    private void intentActionView(Intent intent) {
        Uri uri = intent.getData();
        if(uri != null) {
        }
    }
}