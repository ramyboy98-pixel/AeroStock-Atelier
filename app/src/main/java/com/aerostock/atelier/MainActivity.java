package com.aerostock.atelier;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.JavascriptInterface;
import android.graphics.pdf.PdfDocument;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.content.ContentValues;
import android.provider.MediaStore;
import android.net.Uri;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        web = new WebView(this);
        web.setWebViewClient(new WebViewClient());

        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);

        web.addJavascriptInterface(new PdfBridge(), "AndroidPDF");

        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
    }

    public class PdfBridge {

        @JavascriptInterface
        public void saveDailyPdf(String jsonData) {
            try {
                JSONArray arr = new JSONArray(jsonData);

                PdfDocument pdf = new PdfDocument();
                Paint paint = new Paint();
                Paint titlePaint = new Paint();

                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
                PdfDocument.Page page = pdf.startPage(pageInfo);
                Canvas canvas = page.getCanvas();

                titlePaint.setColor(Color.rgb(6, 48, 77));
                titlePaint.setTextSize(20);
                titlePaint.setFakeBoldText(true);

                paint.setColor(Color.BLACK);
                paint.setTextSize(11);

                int y = 40;

                canvas.drawText("AeroStock Atelier - Journal du Jour", 40, y, titlePaint);
                y += 25;

                String date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE).format(new Date());
                canvas.drawText("Date d'exportation : " + date, 40, y, paint);
                y += 30;

                if (arr.length() == 0) {
                    canvas.drawText("Aucun mouvement enregistré aujourd'hui.", 40, y, paint);
                } else {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject o = arr.getJSONObject(i);

                        if (y > 790) {
                            pdf.finishPage(page);
                            pageInfo = new PdfDocument.PageInfo.Builder(595, 842, i + 2).create();
                            page = pdf.startPage(pageInfo);
                            canvas = page.getCanvas();
                            y = 40;
                        }

                        canvas.drawText("Agent : " + o.optString("workerName"), 40, y, paint);
                        y += 16;
                        canvas.drawText("Outil : " + o.optString("toolName"), 40, y, paint);
                        y += 16;
                        canvas.drawText("N° Série : " + o.optString("toolSerial"), 40, y, paint);
                        y += 16;
                        canvas.drawText("Sortie : " + o.optString("checkoutTime"), 40, y, paint);
                        y += 16;
                        canvas.drawText("Retour : " + o.optString("returnTime"), 40, y, paint);
                        y += 24;

                        canvas.drawLine(40, y, 550, y, paint);
                        y += 18;
                    }
                }

                pdf.finishPage(page);

                String fileName = "AeroStock_Journal_" +
                        new SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.FRANCE).format(new Date()) +
                        ".pdf";

                ContentValues values = new ContentValues();
                values.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
                values.put(MediaStore.Downloads.MIME_TYPE, "application/pdf");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    values.put(MediaStore.Downloads.RELATIVE_PATH, "Download");
                }

                Uri uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                OutputStream out = getContentResolver().openOutputStream(uri);

                pdf.writeTo(out);
                out.close();
                pdf.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
