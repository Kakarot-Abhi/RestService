package com.rest.RestService.client;

public interface IMapper<T1, T2, R> {
    R toData(T1 t1, T2 t2);
    R toError(T1 t1, T2 t2);
    boolean isError(T1 t1, T2 t2);
}
