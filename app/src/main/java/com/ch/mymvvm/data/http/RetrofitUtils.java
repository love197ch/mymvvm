package com.ch.mymvvm.data.http;

import com.ch.mymvvm.data.http.fastjson.FastJsonConverterFactory;

import com.ch.mymvvm.utils.StaticVariable;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public class RetrofitUtils {
    private static volatile Retrofit mRetrofit;
    private HashMap<Class, Retrofit> mServiceHashMap = new HashMap<>();
    private ConcurrentHashMap<Class, Object> cachedApis = new ConcurrentHashMap<>();

    private static Retrofit getRetrofit() {
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = getUnsafeOkHttpClient();
        builder.connectTimeout(10, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(10, TimeUnit.SECONDS);//写操作超时时间
        builder.readTimeout(10, TimeUnit.SECONDS);//读操作超时时间
        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("paltform", "android")
                .addHeaderParams("userToken", "1234343434dfdfd3434")
                .addHeaderParams("userId", "123445")
                .build();
        builder.addInterceptor(commonInterceptor);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(StaticVariable.URL)
                .addConverterFactory(FastJsonConverterFactory.create())//重点是这句话
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return mRetrofit;
    }

    public static Retrofit getInstance() {
        //在基础URL不变情况下可以使用单例模式
        try {
            if (mRetrofit == null) {
                synchronized (RetrofitUtils.class) {
                    mRetrofit = getRetrofit();
                }
            }
        } catch (Exception e) {
            //当基础url格式错误时会报错
            e.printStackTrace();
        }
        return mRetrofit;
    }

    private RetrofitUtils() {
        if (mRetrofit != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static synchronized Retrofit changeUrl() {
        try {
            mRetrofit = getRetrofit();
        } catch (Exception e) {
            //当基础url格式错误时会报错
            return null;
        }
        return mRetrofit;
    }

    //不校验ssl
    private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
