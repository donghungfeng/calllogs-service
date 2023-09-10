package com.example.gateservice.service.Impl;

import com.example.gateservice.query.CustomRsqlVisitor;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.service.BaseService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected abstract BaseRepository<T> getRepository();

    @Override
    public List<T> getAll() {
        return this.getRepository().findAll();
    }

    @Override
    public Page<T> search(String filter, String sort, int size, int page){
        Node rootNode = new RSQLParser().parse(filter);
        Specification<T> spec = rootNode.accept(new CustomRsqlVisitor<T>());
        String[] sortList = sort.split(",");
        Sort.Direction direction = sortList[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, direction, sortList[0]);
        return this.getRepository().findAll(spec, pageable);
    }

    @Override
    public T create(T t) {
        return this.getRepository().save(t);
    }

    @Override
    public T update(T t){
        return this.getRepository().save(t);
    }
    @Override
    public T getById(Long id) {
        return this.getRepository().findAllById(id);
    }

    @Override
    public String delete(Long id){
        T t = this.getRepository().findAllById(id);
        this.getRepository().delete(t);
        return "delete success";
    }


}
