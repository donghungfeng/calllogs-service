package com.example.gateservice.service.Impl;

import com.example.gateservice.query.CustomRsqlVisitor;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.service.BaseService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected abstract BaseRepository<T> getRepository();

    @Override
    public List<T> getAll() {
        return this.getRepository().findAll();
    }

    @Override
    public List<T> search(String filter){
        Node rootNode = new RSQLParser().parse(filter);
        Specification<T> spec = rootNode.accept(new CustomRsqlVisitor<T>());
        return this.getRepository().findAll(spec);
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
