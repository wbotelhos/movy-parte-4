package br.com.wbotelhos.movy.repository.common;

import java.util.Collection;

import br.com.wbotelhos.movy.model.common.AbstractEntity;

public interface GenericRepository<T extends AbstractEntity> {

	Collection<T> loadAll();

	T loadById(Long id);

	void remove(T entity);

	T save(T entity);

}