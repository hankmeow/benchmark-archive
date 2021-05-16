package com.hankmew.benchmark.archetype.common.web.result;

import javax.annotation.Nonnull;
import java.util.Collection;

public class Result<T> {
    private final boolean success;
    private final Status status;
    private final T data;


    /* 创建 */
    private Result(boolean success, Status status, T data) {
        this.success = success;
        this.status = status;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(true, null, null);
    }

    public static <T> Result<T> success(@Nonnull T data) {
        return new Result<>(true, null, data);
    }

    public static <T> Result<T> failure() {
        return new Result<>(false, null, null);
    }

    public static <T> Result<T> failure(@Nonnull Status status) {
        return new Result<>(false, status, null);
    }

    public static <T> Result<T> failure(@Nonnull T data) {
        return new Result<>(false, null, data);
    }

    public static <T> Result<T> failure(@Nonnull Status status, @Nonnull T data) {
        return new Result<>(false, status, data);
    }


    /* 判断状态 */
    public boolean isSuccess() {
        return this.success;
    }

    public boolean isFailure() {
        return !this.success;
    }

    public boolean statusEquals(@Nonnull Status status) {
        //枚举可以直接用==判断
        return this.status == status;
    }

    public Status status() {
        return this.status;
    }

    /* 判断值 */
    public boolean isEmpty() {
        if (this.data == null) {
            return true;
        }else {
            if (this.data instanceof Collection) {
                return ((Collection<?>) this.data).isEmpty();
            }else {
                return false;
            }
        }
    }
    public boolean notEmpty() {
        return !isEmpty();
    }
    public T get() {
        return this.data;
    }
    public T getDefault(T defaultData) {
        return isEmpty() ? defaultData : this.data;
    }

    public interface Status {
        int getCode();
    }
}
