package com.example.gateservice.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T> {
    public Page<T> search(String filter, String sort, int size, int page, String jwt);
    public T create(T t);
    public T update(T t);
    public T getById(Long id);
    public List<T> getAll();
    public String delete(Long id);
}
