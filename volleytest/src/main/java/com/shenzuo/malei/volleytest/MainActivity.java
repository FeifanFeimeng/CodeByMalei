package com.shenzuo.malei.volleytest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends Activity {
    private RequestQueue mRequestQueue;
    private ImageView mGirl;
    private Button mSend;
    private NetworkImageView mNIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGirl = (ImageView) findViewById(R.id.iv_girl);
        mSend = (Button) findViewById(R.id.bt_end);
        mNIV = (NetworkImageView) findViewById(R.id.niv_girl);
        mRequestQueue = Volley.newRequestQueue(this);

        ImageLoader imageLoader = new ImageLoader(mRequestQueue, new BitmapCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        mNIV.setDefaultImageResId(R.mipmap.ic_launcher_round);
        mNIV.setErrorImageResId(R.mipmap.ic_launcher);
        mNIV.setImageUrl("http://img5.imgtn.bdimg.com/it/u=4245887956,100740587&fm=23&gp=0.jpg",imageLoader);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLoader();
            }
        });
    }

    //带有cache的ImageLoader
    public class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;
        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }
        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }
        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }


    // ImageLoader的用法
    public void imageLoader()
    {
        ImageLoader imageLoader = new ImageLoader(mRequestQueue, new BitmapCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mGirl, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher);
        imageLoader.get("http://img5.imgtn.bdimg.com/it/u=4245887956,100740587&fm=23&gp=0.jpg",imageListener);
    }
    //以bitmap形式返回数据
    public void send(){
        ImageRequest imageRequest = new ImageRequest("http://img5.imgtn.bdimg.com/it/u=4245887956,100740587&fm=23&gp=0.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                mGirl.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mGirl.setImageResource(R.mipmap.ic_launcher_round);
            }
        });
        mRequestQueue.add(imageRequest);
    }

    //以json格式返回网络数据
    private JsonObjectRequest jsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.weather.com.cn/data/cityinfo/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("malei", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("malei", error.getMessage(), error);
            }
        });
        return jsonObjectRequest;
    }

    //以String格式返回网络数据
    private StringRequest stringRequest() {
        StringRequest stringRequest = new StringRequest("https://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        return stringRequest;
    }
}
