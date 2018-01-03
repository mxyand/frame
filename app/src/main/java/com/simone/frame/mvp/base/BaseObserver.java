package com.simone.frame.mvp.base;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ParseException;
import android.support.v7.app.AlertDialog;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.simone.frame.R;
import com.simone.frame.app.DingLFApplication;
import com.simone.frame.utils.LogUtils;
import com.simone.frame.utils.ToastUtils;
import com.simone.frame.utils.spreference.SharedPreferencesHelper;
import com.simone.frame.utils.spreference.SharedPreferencesTag;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.simone.frame.mvp.base.BaseObserver.ExceptionReason.CONNECT_ERROR;
import static com.simone.frame.mvp.base.BaseObserver.ExceptionReason.CONNECT_TIMEOUT;
import static com.simone.frame.mvp.base.BaseObserver.ExceptionReason.PARSE_ERROR;
import static com.simone.frame.mvp.base.BaseObserver.ExceptionReason.UNKNOWN_ERROR;


/**
 * Created by Administrator on 2017/8/17.
 */

public abstract class BaseObserver<T> implements Observer<HttpResponse<T>> {

    private Context mContext;
    private BaseView mBaseView;
    private Disposable mDisposable;
    private final String SUCCESS_CODE = "0000";
    private final String SUCCESS_CODE2 = "0003";
    private final String TOKENERROR_CODE = "0001";


    /**
     * 默认不开启记载动画
     *
     * @param context
     * @param baseView 传入view对象
     */
    public BaseObserver(Context context, BaseView baseView) {
        mContext = context;
        mBaseView = baseView;
//        mBaseView.showLoading();
    }

    /**
     * 传递参数控制加载动画是否开启
     *
     * @param context
     * @param baseView
     * @param openLoading
     */
    public BaseObserver(Context context, BaseView baseView, boolean openLoading) {
        mContext = context;
        mBaseView = baseView;
        if (openLoading) {
            mBaseView.showLoading();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    /**
     * 控制是否强行中断本次网络连接
     *
     * @param closeConnect
     */
    public void onDispoable(boolean closeConnect) {
        if (closeConnect) {
            if (mDisposable != null) {
                mDisposable.dispose();
            }
        }
    }

    @Override
    public void onNext(HttpResponse<T> tHttpResponse) {
        if (true) {//判断网络
            mBaseView.showContent();
            if (tHttpResponse.getErrorCode().equals(SUCCESS_CODE)) {
          // if (tHttpResponse.getErrorCode().equals(SUCCESS_CODE)||tHttpResponse.getErrorCode().equals(SUCCESS_CODE2)) {
                T t = tHttpResponse.getResults();
                onResponseCodeSuccess(t);
            } else {
                onResponseCodeError(tHttpResponse.getErrorCode(), tHttpResponse.getErrorMsg());
            }
        } else {
            mBaseView.showNoNetwork();
        }

    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(LogUtils.createTag(), "error:" + e.getMessage());

        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }

    }

    @Override
    public void onComplete() {
        LogUtils.i("sst_", "onComplete");
    }

    public abstract void onResponseCodeSuccess(T t);

    /**
     * 请求成功返回码非SuccessCode
     *
     * @param code
     * @param message
     */
    void onResponseCodeError(String code, String message) {
        LogUtils.e("返回码：" + code, "msg信息：" + message);
        //根据统一返回码继续做相应处理
        if (code.equals("0001")) {
            SharedPreferencesHelper helper = SharedPreferencesHelper.getInstance(DingLFApplication.getInstance());
            helper.putBooleanValue(SharedPreferencesTag.LOGIN_BOOLEAN, false);
            helper.putStringValue(SharedPreferencesTag.USER_HEAD_IMG, "");
            helper.putIntValue(SharedPreferencesTag.USER_SCORE, 0);
            helper.putStringValue(SharedPreferencesTag.USER_TOKEN, "");
            helper.putStringValue(SharedPreferencesTag.USER_PHONE, "");
            helper.putStringValue(SharedPreferencesTag.HISTORY_KEY, "");
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("账号过期，请重新登录")
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           // LoginActivity.openAct(mContext);
                        }
                    })
                    .show();
        } else {
            ToastUtils.showShort(mContext, message, 1000);
        }
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        String errorstring;
        switch (reason) {
            case CONNECT_ERROR:
                errorstring = mContext.getResources().getString(R.string.connect_error);
                break;
            case CONNECT_TIMEOUT:
                errorstring = mContext.getResources().getString(R.string.connect_timeout);
                break;
            case BAD_NETWORK:
                errorstring = mContext.getResources().getString(R.string.bad_network);
                break;
            case PARSE_ERROR:
                errorstring = mContext.getResources().getString(R.string.parse_error);
                break;
            case UNKNOWN_ERROR:
            default:
                errorstring = mContext.getResources().getString(R.string.unknown_error);
                break;
        }
        mBaseView.showError(errorstring);
    }


    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
