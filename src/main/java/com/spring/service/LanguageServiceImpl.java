package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.LanguageRepository;
import com.spring.exceptions.DuplicatedNameException;
import com.spring.exceptions.InvalidNameException;
import com.spring.exceptions.LanguageInUseException;
import com.spring.model.Language;
import com.spring.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Service("LanguageService")
@Transactional
public class LanguageServiceImpl implements ILanguageService {
	
	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public List<Language> findAllLanguages() throws Exception {

		List<Language> languages = new ArrayList<Language>();
		languageRepository.findAll().forEach(languages::add);
		
		return languages;
	}

	@Override
	public Language findLanguageById(Long id) throws Exception {
		
		return languageRepository.findOne(id);
	}

	@Override
	public Language findLanguageByName(String name) throws Exception {
		
		return languageRepository.findByName(name);
	}

	@Override
	public Language createLanguage(Language commingLanguage) throws Exception {
		
		String languageName = commingLanguage.getName();
		List<Language> languages = new ArrayList<Language>();
		languageRepository.findAll().forEach(languages::add);
		
		// validation
		
		if(!languageName.matches("[a-zA-Z+#.]+") || languageName.length() > 25) {
			throw new InvalidNameException();
		}
		
		for (Language language:languages) {
			
			// if the language name is already in use
			if (language.getName().equalsIgnoreCase(languageName)) {
				throw new DuplicatedNameException();
			}
		}
		
		return languageRepository.save(commingLanguage);
	}

	@Override
	public void deleteLanguage(Language languageToDelete) throws Exception {
		
		List<User> users = languageToDelete.getUsers();
		
		if (users.size() > 0) {
			throw new LanguageInUseException();
		}
		
		languageRepository.delete(languageToDelete);
	}
	
	@Override
	public Language updateLanguage(String oldName, Language commingLanguage) throws Exception {
		
		String languageName = commingLanguage.getName();
		List<Language> languages = new ArrayList<Language>();
		languageRepository.findAll().forEach(languages::add);
		
		// validation
		
		if(!languageName.matches("[a-zA-Z+#.]+") || languageName.length() > 25) {
			throw new InvalidNameException();
		}
		
		for (Language language:languages) {
			
			// if the language name is already in use
			if (language.getName().equalsIgnoreCase(languageName) && 
				!commingLanguage.getName().equalsIgnoreCase(oldName)) {
				
				throw new DuplicatedNameException();
			}
		}
		
		return languageRepository.save(commingLanguage);
	}
	
}
