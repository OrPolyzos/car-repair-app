package com.rcodingschool.carrepair.service.base;

import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class ResourceService<T, ID extends Serializable> {

    protected CrudRepository<T, ID> crudRepository;

    public ResourceService(CrudRepository<T, ID> crudRepository) {
        this.crudRepository = crudRepository;
    }

    public T find(ID entityId) {
        return crudRepository.findOne(entityId);
    }

    public Optional<T> findOptional(ID entityId) {
        return Optional.ofNullable(crudRepository.findOne(entityId));
    }

    public T findOrThrow(ID entityId) throws ResourceNotFoundException {
        return findOptional(entityId).orElseThrow(() -> new ResourceNotFoundException(entityId));
    }

    public T insert(T entity) throws ResourceException {
        validateBeforeInsertOrThrow(entity);
        return crudRepository.save(entity);
    }

    protected abstract void validateBeforeInsertOrThrow(T entity) throws ResourceException;

    public T update(T entity) throws ResourceException {
        validateBeforeUpdateOrThrow(entity);
        return crudRepository.save(entity);
    }

    protected abstract void validateBeforeUpdateOrThrow(T entity) throws ResourceException;

    public void deleteById(ID entityId) throws ResourceNotFoundException {
        T actualEntity = findOrThrow(entityId);
        crudRepository.delete(actualEntity);
    }

    protected List<T> mapOptionalToList(Optional<T> optionalEntity) {
        return optionalEntity.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}
