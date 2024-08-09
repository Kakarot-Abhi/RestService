package com.rest.RestService.service;

public interface INodeService {
    void save();
    void getAll();
    void getByID(String id);
    void delete(String id);
    void update(String id);
}
