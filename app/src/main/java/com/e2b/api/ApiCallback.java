package com.e2b.api;

import android.content.Context;
import android.util.Log;

import com.e2b.R;
import com.e2b.model.response.BaseResponse;
import com.e2b.model.response.Error;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
This Class is to get Api response
 */

public abstract class ApiCallback<T> implements Callback<BaseResponse<T>> {
    private String TAG = ApiCallback.class.getSimpleName();
    private Context context;

    public ApiCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
        if (response.body() != null) {
            Log.i(TAG, "RES : " + "Success");
            if (((BaseResponse) response.body()).getSt() == 1) {
                onSucess(response.body().getResult());
            } else {
                if (response.body().getError() != null) {
                    Error error = response.body().getError();
                    if (error != null) {
                        onError(error);
                    }
                }
            }
        } else {
            Log.i(TAG, "ERROR : " + "Success");
            Error error = new Error();
            error.setMsg(context.getString(R.string.error_msg));
            onError(error);
        }
    }

    @Override
    public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
        Log.i(TAG, "ERROR FAIL : " + t.getMessage());
        Error error = new Error();
        if (t != null && t.getMessage() != null) {
            if(t.getMessage().toLowerCase().contains("failed to connect")){
                error.setMsg(context.getString(R.string.no_internet));
            }else{
                error.setMsg(t.getMessage());
            }
        } else {
            error.setMsg(context.getString(R.string.error_msg));
        }
        onError(error);
    }


    public abstract void onSucess(T t);

    public abstract void onError(Error msg);

}
