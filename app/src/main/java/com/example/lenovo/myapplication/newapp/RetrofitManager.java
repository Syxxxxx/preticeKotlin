package com.example.lenovo.myapplication.newapp;

import android.content.Context;
import android.util.Log;

import com.example.lenovo.myapplication.newapp.BaseStatus.BaseHttpResponse;
import com.example.lenovo.myapplication.newapp.BaseStatus.BaseObserver;
import com.example.lenovo.myapplication.newapp.BaseStatus.ErrorBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final long DEFAULT_TIMEOUT = 60L;
    private Retrofit retrofit = null;
    //请求头信息
    private final String HEADER_CONNECTION = "keep-alive";
    /**
     * 获取单例
     */
    private static RetrofitManager mInstance;
    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }
    /**
     * 添加请求头需要携带的参数
     */
    public class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request requestBuilder = request.newBuilder()
                    .addHeader("Connection", HEADER_CONNECTION)
                    .addHeader("token", "token-value")
                    .method(request.method(), request.body())
                    .build();
            return chain.proceed(requestBuilder);
        }
    }
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    OkHttpClient mClient = new OkHttpClient.Builder()
                            //添加公共查询参数
                            //.addInterceptor(new CommonQueryParamsInterceptor())
                            //.addInterceptor(new MutiBaseUrlInterceptor())
                            //添加header
                            .addInterceptor(new HeaderInterceptor())
                            .addInterceptor(new LoggingInterceptor())//添加请求拦截(可以在此处打印请求信息和响应信息)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            //添加https证书,如果有srca.cer的证书，则可以通过sslSocketFactory()配置
                            //.sslSocketFactory(getSSLSocketFactory(context, "srca.cer"))
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://hd215.api.yesapi.cn/")//基础URL 建议以 / 结尾
                            .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
                            .client(mClient)
                            .build();
                }
            }
        }
        return retrofit;
    }
//    /**
//     * 建立请求
//     */
//    public <T> DisposableObserver<BaseHttpResponse<T>> doRequest(Observable<BaseHttpResponse<T>> observable, final BaseObserver<T> observerListener) {
//
//        return observable
//                .compose(RxSchedulers.<BaseHttpResponse<T>>io_main())
//                .subscribeWith(new DisposableObserver<BaseHttpResponse<T>>() {
//
//                    @Override
//                    public void onNext(BaseHttpResponse<T> result) {
//                        if (result.getStatus()== 200) {
//                            observerListener.onSuccess(result.getData());
//                        } else {
//                            ErrorBean errorBean = new ErrorBean();
//                            errorBean.setCode(result.getStatus() + "");
//                            errorBean.setMsg(result.getMessage());
//                            observerListener.onBusinessError(errorBean);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        observerListener.onError(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        observerListener.onComplete();
//                    }
//                });
//
//    }
    /**
     * 实现https请求
     */
    private static SSLSocketFactory getSSLSocketFactory(Context context, String name) {


        if (context == null) {
            throw new NullPointerException("context == null");
        }

        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        InputStream inputStream = null;
        Certificate certificate;

        try {
            inputStream = context.getResources().getAssets().open(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            certificateFactory = CertificateFactory.getInstance("X.509");
            certificate = certificateFactory.generateCertificate(inputStream);

            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry(name, certificate);

            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {

        }
        return null;
    }

    public PostService getRequestService() {
        return getRetrofit().create(PostService.class);
    }

    /**
     * log打印:参考：http://blog.csdn.net/csdn_lqr/article/details/61420753
     */
    public class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            String method = request.method();
            JSONObject jsonObject = new JSONObject();
            if ("POST".equals(method) || "PUT".equals(method)) {
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    if (body != null) {
                        for (int i = 0; i < body.size(); i++) {
                            try {
                                jsonObject.put(body.name(i), body.encodedValue(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Log.e("request", String.format("发送请求 %s on %s  %nRequestParams:%s%nMethod:%s",
                            request.url(), chain.connection(), jsonObject.toString(), request.method()));
                } else {
                    Buffer buffer = new Buffer();
                    RequestBody requestBody = request.body();
                    if (requestBody != null) {
                        request.body().writeTo(buffer);
                        String body = buffer.readUtf8();
                        Log.e("request", String.format("发送请求 %s on %s  %nRequestParams:%s%nMethod:%s",
                                request.url(), chain.connection(), body, request.method()));
                    }
                }
            } else {
                Log.e("request", String.format("发送请求 %s on %s%nMethod:%s",
                        request.url(), chain.connection(), request.method()));
            }
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            Log.e("request",
                    String.format("Retrofit接收响应: %s %n返回json:【%s】 %n耗时：%.1fms",
                            response.request().url(),
                            responseBody.string(),
                            (t2 - t1) / 1e6d
                    ));
            return response;
        }

    }



}
