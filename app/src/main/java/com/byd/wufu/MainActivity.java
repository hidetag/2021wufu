package com.byd.wufu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<String> urls = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    private int currentIndex = 0;
    private String phone = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            saveProgress();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        FloatingActionButton fl = findViewById(R.id.fab_left);
        FloatingActionButton fr = findViewById(R.id.fab_right);
        FloatingActionButton ftl = findViewById(R.id.fab_top_left);
        WebView webView = findViewById(R.id.wb);

        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webView.requestFocusFromTouch();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                final String js = "javascript:document.getElementsByClassName('btn___SkWL1')[0].click();";
                view.evaluateJavascript(js, value -> {
                    final String js1 = "javascript:document.getElementById('J-mobile').value = '" + phone + "';";
                    view.evaluateJavascript(js1, value1 -> {
                    });
                });
            }
        });

        fl.setOnClickListener(v -> {
            if (currentIndex - 1 == 0) {
                currentIndex = 0;
            } else if (currentIndex - 1 < 0) {
                currentIndex = titles.size() - 1;
            } else {
                currentIndex -= 1;
            }

            String title = titles.get(currentIndex);
            String url = urls.get(currentIndex);

            webView.loadUrl(url);
            toolbar.setTitle(title + "（" + (currentIndex + 1) + "/" + titles.size() + "）");
        });

        fr.setOnClickListener(v -> {
            if (currentIndex + 1 >= titles.size()) {
                currentIndex = 0;
            } else {
                currentIndex += 1;
            }

            String title = titles.get(currentIndex);
            String url = urls.get(currentIndex);

            webView.loadUrl(url);
            toolbar.setTitle(title + "（" + (currentIndex + 1) + "/" + titles.size() + "）");
        });

        ftl.setOnClickListener(v -> showDialog());

        SharedPreferences sharedPreferences = getSharedPreferences("progress", Context.MODE_PRIVATE);
        currentIndex = sharedPreferences.getInt("index", 0);
        phone = sharedPreferences.getString("phone", "");

        webView.loadUrl(urls.get(currentIndex));
        toolbar.post(() -> toolbar.setTitle(titles.get(currentIndex) + "（" + (currentIndex + 1) + "/" + titles.size() + "）"));
    }

    private void init() {
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=JING_LING");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=FEI_ZHU");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=YOUKU_TV");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=PIAO_PIAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=TIAN_MAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=KAO_LA");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=CAI_NIAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=XINHUA_SHE");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=BAI_JINGTU");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=KE_CHUANG");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=KEJI_ZHIJIA");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=JIEFANG_RIBAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=DA_WAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=ZIJIN_SHAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=ZHONGGUO_LAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=CAI_LIFANG");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=ZHENG_GUAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=JIANGXI_XINWEN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=YANG_CHENG");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=NAN_DU");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=SANYA_RIBAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=CHUNCHENG_WANBAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=GUIZHOU_DUSHIBAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=HUANG_HE");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=SHANXI_TOUTIAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=XINJING_BAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=JIN_YUN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=CHONG_QING");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=LONGTOU_XINWEN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=FENGKOU_CAIJING");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=QILU_WANBAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=GUOWU_YUAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=REN_SHE");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=YI_BAO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=YUSHI_BAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=EHUI_BAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=SUISHEN_BAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=SHENZHEN_JIAOJING");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=GANFU_TONG");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=ANHUI_SHUIWU");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=WUXI_GONGJIJIN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=SHANGHAI_GONGJIJIN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=TIANFU_TONG");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=QINGDAN_DASHUJU");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=WANSHI_TONG");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=MINZHENG_TONG");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=MEI_TU");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=MEI_YAN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=MANG_GUO");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=KUAI_SHOU");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=WANGYI_YUN");
        urls.add("https://render.alipay.com/p/c/17yq18lq3slc?source=SHU_QI");

        titles.add("天猫精灵");
        titles.add("飞猪");
        titles.add("优酷");
        titles.add("淘票票");
        titles.add("手机天猫");
        titles.add("考拉");
        titles.add("菜鸟");
        titles.add("新华社");
        titles.add("江南百景图");
        titles.add("科创中国");
        titles.add("科技工作者");
        titles.add("上观新闻");
        titles.add("新安晚报");
        titles.add("南京紫金山");
        titles.add("中国蓝新闻");
        titles.add("大河财立方");
        titles.add("正观APP");
        titles.add("江西新闻客");
        titles.add("羊城晚报");
        titles.add("N视频");
        titles.add("三亚日报");
        titles.add("开屏新闻");
        titles.add("天眼新闻");
        titles.add("黄河plu");
        titles.add("陕西头条");
        titles.add("新京报");
        titles.add("津云客户端");
        titles.add("重庆日报");
        titles.add("龙头新闻");
        titles.add("风口财经");
        titles.add("齐鲁晚报");
        titles.add("国家政务服");
        titles.add("电子社保卡");
        titles.add("医保局");
        titles.add("豫事办");
        titles.add("鄂汇办");
        titles.add("随申办");
        titles.add("深圳交警");
        titles.add("赣服通");
        titles.add("安徽税务局");
        titles.add("无锡公积金");
        titles.add("上海公积金");
        titles.add("天府通");
        titles.add("青岛大数据");
        titles.add("皖事通");
        titles.add("闽政通");
        titles.add("美图秀秀");
        titles.add("美颜相机");
        titles.add("芒果TV");
        titles.add("快手");
        titles.add("网易云音乐");
        titles.add("书旗小说");
    }

    private void saveProgress() {
        SharedPreferences sharedPreferences = getSharedPreferences("progress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("index", currentIndex);
        editor.apply();
        Toast.makeText(this, "保存进度成功，下次进入直接还原到当前进度", Toast.LENGTH_SHORT).show();
    }

    private void savePhone(String phone) {
        SharedPreferences sharedPreferences = getSharedPreferences("progress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phone);
        editor.apply();
        Toast.makeText(this, "保存手机号成功，下次进入无需输入手机号", Toast.LENGTH_SHORT).show();
    }

    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showDialog() {
        final EditText edit = new EditText(this);
        edit.setInputType(InputType.TYPE_CLASS_PHONE);
        AlertDialog.Builder editDialog = new AlertDialog.Builder(this);
        editDialog.setTitle("请输入手机号");
        editDialog.setView(edit);
        editDialog.setPositiveButton("保存", (dialog, which) -> {
            String phone = edit.getEditableText().toString();
            MainActivity.this.phone = phone;
            savePhone(phone);
            dialog.dismiss();
        });
        editDialog.create().show();
    }


}