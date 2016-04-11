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
        String url = "";

        Uri imageUri = null;
        ClipData clipData = null;
        imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        clipData = intent.getClipData();

        if (imageUri != null) {
            //url = imageUri.toString();
            return;
        } else if (clipData != null) {
            ClipData.Item item = clipData.getItemAt(0);
            url = (String) item.getText();
        } else {
            return;
        }

        Intent intentService = new Intent(ExternalCallActivity.this, FloatWinService.class);
        intentService.putExtra("url", url);
        startService(intentService);
    }

    private void intentActionView(Intent intent) {
        Uri uri = intent.getData();
        if (uri == null) {
            return;
        }
        Intent intentService = new Intent(ExternalCallActivity.this, FloatWinService.class);
        intentService.putExtra("url", uri.toString());
        startService(intentService);
    }
}
