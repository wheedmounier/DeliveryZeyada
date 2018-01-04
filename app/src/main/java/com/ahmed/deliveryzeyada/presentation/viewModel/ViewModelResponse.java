package com.ahmed.deliveryzeyada.presentation.viewModel;

import android.support.annotation.Nullable;

/**
 * Created by Ahmed Kamal on 04/01/2018.
 */

public class ViewModelResponse<T>
{

    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    private ViewModelResponse(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> ViewModelResponse<T> success(T data) {
        return new ViewModelResponse<>(Status.SUCCESS, data, null);
    }

    public static <T> ViewModelResponse<T> error(Throwable error) {
        return new ViewModelResponse<>(Status.ERROR, null, error);
    }
}
