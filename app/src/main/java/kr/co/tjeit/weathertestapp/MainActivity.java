package kr.co.tjeit.weathertestapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import kr.co.tjeit.weathertestapp.util.ServerUtil;

public class MainActivity extends BaseActivity {

    private android.widget.TextView localTxt;
    private android.widget.TextView skyTxt;
    private android.widget.TextView temperaTxt;
    private android.widget.TextView maxTemperaTxt;
    private android.widget.TextView minTemperaTxt;
    private android.widget.TextView windTxt;
    private android.widget.TextView customTxt;
    private android.widget.Button weatherBtn;
    private android.widget.EditText latEdt;
    private android.widget.EditText lonEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.getCurrentWeatherFromServer(mContext, latEdt.getText().toString(), lonEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            localTxt.setText(json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("station").getString("name"));
                            skyTxt.setText(json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("sky").getString("name"));
                            temperaTxt.setText(String.format(Locale.KOREA, "%.1f ℃", Float.parseFloat(json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("temperature").getString("tc"))));
                            maxTemperaTxt.setText(String.format(Locale.KOREA, "%.1f ℃", Float.parseFloat(json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("temperature").getString("tmax"))));
                            minTemperaTxt.setText(String.format(Locale.KOREA, "%.1f ℃", Float.parseFloat(json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("temperature").getString("tmin"))));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.customTxt = (TextView) findViewById(R.id.customTxt);
        this.windTxt = (TextView) findViewById(R.id.windTxt);
        this.minTemperaTxt = (TextView) findViewById(R.id.minTemperaTxt);
        this.maxTemperaTxt = (TextView) findViewById(R.id.maxTemperaTxt);
        this.temperaTxt = (TextView) findViewById(R.id.temperaTxt);
        this.skyTxt = (TextView) findViewById(R.id.skyTxt);
        this.localTxt = (TextView) findViewById(R.id.localTxt);
        this.weatherBtn = (Button) findViewById(R.id.weatherBtn);
        this.lonEdt = (EditText) findViewById(R.id.lonEdt);
        this.latEdt = (EditText) findViewById(R.id.latEdt);
    }
}
