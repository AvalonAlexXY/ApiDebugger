package io.chengguo.apidebugger.engine.http;

import org.apache.http.client.methods.HttpRequestBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/2.
 */
public class ArtHttp {

    private static HashMap<String, HttpRequestBase> mTags = new HashMap<>();

    /**
     * post 请求
     *
     * @return
     */
    public static FormRequestBuilder post() {
        return new FormRequestBuilder();
    }

    /**
     * get 请求
     *
     * @return
     */
    public static GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    /**
     * put 请求
     *
     * @return
     */
    public static PutRequestBuilder put() {
        return new PutRequestBuilder();
    }

    /**
     * patch 请求
     *
     * @return
     */
    public static PatchRequestBuilder patch() {
        return new PatchRequestBuilder();
    }

    /**
     * delete 请求
     */
    public static void delete() {

    }

    public static void cancelAll() {
        for (Map.Entry<String, HttpRequestBase> entry : mTags.entrySet()) {
            entry.getValue().abort();
        }
        mTags.clear();
    }

    public static void addTag(String key, HttpRequestBase request) {
        mTags.put(key, request);
    }
}
