package com.rest.RestService.client;

import com.rest.RestService.model.Request;

public abstract class AbstractIClient<T1, T2, R>{
    IMapper<T1, T2, R> mapper;

    protected AbstractIClient(IMapper<T1, T2, R> mapper){
        this.mapper = mapper;
    }

    public R delete(Request request) {
        return delete(request, mapper);
    }

    public R get(Request request) {
        return get(request, mapper);
    }

    public R post(Request request) {
        return post(request, mapper);
    }

    public R put(Request request) {
        return put(request, mapper);
    }

    protected abstract R delete(Request request, IMapper<T1, T2, R> mapper);

    protected abstract R get(Request request, IMapper<T1, T2, R> mapper);

    protected abstract R post(Request request, IMapper<T1, T2, R> mapper);

    protected abstract R put(Request request, IMapper<T1, T2, R> mapper);
}
