package com.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.model.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
	
	public Language findByName(String name);


}
