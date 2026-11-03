package app.vopgram;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView web;

    // Закрывает верхний слой интерфейса (меню, профиль, чат). Возвращает "1", если что-то закрыто.
    private static final String BACK_JS =
        "(function(){"
        + "var s=document.getElementById('sheetWrap'); if(s&&s.classList.contains('open')){s.click();return 1;}"
        + "var d=document.getElementById('drawerWrap'); if(d&&d.classList.contains('open')){d.click();return 1;}"
        + "var o=document.querySelectorAll('.overlay.open'); if(o.length){"
        + "var top=o[o.length-1], b=top.querySelector('[id$=Back]'); if(b){b.click();return 1;}}"
        + "return 0;})()";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Vopgram); // после сплэша переключаем на обычную тему
        super.onCreate(savedInstanceState);

        Window w = getWindow();
        w.setStatusBarColor(Color.TRANSPARENT);
        w.setNavigationBarColor(Color.TRANSPARENT);
        w.getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        web = new WebView(this);
        web.setBackgroundColor(Color.WHITE);
        setContentView(web);

        WebSettings st = web.getSettings();
        st.setJavaScriptEnabled(true);
        st.setDomStorageEnabled(true);      // localStorage: аккаунты и чаты
        st.setAllowFileAccess(true);
        st.setMediaPlaybackRequiresUserGesture(false);
        st.setSupportZoom(false);
        st.setBuiltInZoomControls(false);
        st.setTextZoom(100);

        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        web.evaluateJavascript(BACK_JS, value -> {
            if (!"1".equals(value)) finish();
        });
    }

    @Override protected void onPause()  { super.onPause();  web.onPause(); }
    @Override protected void onResume() { super.onResume(); web.onResume(); }
}
