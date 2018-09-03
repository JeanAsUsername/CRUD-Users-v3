package com.spring.service;

import java.util.List;

import com.spring.model.Language;

public interface ILanguageService {
	
	public List<Language> findAllLanguages() throws Exception;
	
	public Language findLanguageById(Long id) throws Exception;
	
	public Language findLanguageByName(String name) throws Exception;
	
	public Language createLanguage(Language commingLanguage) throws Exception;
	
	public void deleteLanguage(Language languageToDelete) throws Exception;
	
	public Language updateLanguage(String oldName, Language commingLanguage) throws Exception;

}
