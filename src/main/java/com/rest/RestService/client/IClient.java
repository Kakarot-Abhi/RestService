package com.rest.RestService.client;

import com.rest.RestService.model.Request;

public interface IClient<T1, T2, R> {
    void setMapper(IMapper<T1, T2, R> mapper);

    IMapper<T1, T2, R> getMapper();

    R deleteCall(Request request, IMapper<T1, T2, R> mapper);

    R getCall(Request request, IMapper<T1, T2, R> mapper);

    R postCall(Request request, IMapper<T1, T2, R> mapper);

    R putCall(Request request, IMapper<T1, T2, R> mapper);
}
