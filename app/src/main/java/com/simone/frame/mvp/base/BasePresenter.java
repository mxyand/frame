package com.simone.frame.mvp.base;




import android.content.Context;

import com.simone.frame.retrofit.ApiStores;
import com.simone.frame.retrofit.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/16.
 * presenter的父类主要用来实现baseView的初始化与销毁工作
 */

public abstract class BasePresenter<T> {
    private final long RETRY_TIMES = 0;//请求失败重试的次数
    public T mvpView;
    protected ApiStores mApiStores;
    private CompositeDisposable mCompositeDisposable;
    protected Context mContext;

    public void attach(T mvpView){
        this.mvpView=mvpView;
        mApiStores= RetrofitFactory.retrofit().create(ApiStores.class);
    }
    public BasePresenter(T t, Context context){
        attach(t);
        mContext=context;
    }

    public void detach(){
        mvpView=null;
        onUnobserver();
    }

    public void addObserver( Observable observable, Observer observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable=new CompositeDisposable();
        }
        observable.retry(RETRY_TIMES)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mCompositeDisposable.add(disposable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //RXjava2清除保存的disposable对象
    public void onUnobserver() {
        if (mCompositeDisposable != null ) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 方便子类快速构建方法
     */
    public abstract void getData();
}
