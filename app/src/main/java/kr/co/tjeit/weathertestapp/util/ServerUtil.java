package kr.co.tjeit.weathertestapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KJ_Studio on 2015-12-24.
 */
public class ServerUtil {

    private static final String TAG = ServerUtil.class.getSimpleName();

    private final static String BASE_URL = "http://tdd.team/"; // 라이브서버
//    private final static String BASE_URL = "http://share-tdd.com/"; // 개발서버

    //    JSON 처리 부분 인터페이스.
    public interface JsonResponseHandler {
        void onResponse(JSONObject json);
    }


    // 사용자 관련 함수 모음

    // 회원 가입

//    1. 원하는 기능을 제공하는 API 주소 알아내자.
//    2. 해당 기능을 사용하기위해 우리가 제공해야하는 데이터도 알아내자.
//    3. 해당 주소/데이터를 기반으로 메쏘드 생성.

    public static void getCurrentWeatherFromServer(final Context context, String lat, String lon, final JsonResponseHandler handler) {
//        기능에 따라 매번 주소를 다르게 적어줘야함.
        String url = "http://apis.skplanetx.com/weather/current/minutely?version=1&lat=" + lat + "&lon=" + lon + "";

//        기능을 사용하기 위해 필요한 데이터를 담는 부분.

        Map<String, String> data = new HashMap<String, String>();
//        data.put("version", "1");
//        data.put("lat","37.467696");
//        data.put("lon", "126.689472");

        AsyncHttpRequest.get(context, url,  data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }


}
