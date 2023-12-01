package dpapps.model.repository.service;

import dpapps.exception.CodingLanguageNotFoundException;
import dpapps.model.CodingLanguage;
import dpapps.model.Project;
import dpapps.model.repository.CodingLanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingLanguageServiceImpl implements CodingLanguageService{

    private final CodingLanguageRepository codingLanguageRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public CodingLanguageServiceImpl(CodingLanguageRepository codingLanguageRepository) {
        this.codingLanguageRepository = codingLanguageRepository;
    }

    @Override
    public CodingLanguage findById(Long id) {
        return this.codingLanguageRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return this.codingLanguageRepository.existsById(id);
    }

    @Override
    public CodingLanguage findByName(String name) {
        try {
            return codingLanguageRepository.findByName(name).orElseThrow(() -> new CodingLanguageNotFoundException());
        }
        catch (CodingLanguageNotFoundException e) {
            logger.warn("Could not find Coding Language with name '" + name + "' in the database.");
        }
        return new CodingLanguage();
    }

    @Override
    public boolean existsByName(String name) {
        return this.codingLanguageRepository.existsByName(name);
    }

    @Override
    public boolean add(CodingLanguage codingLanguage) {
        try {
            this.codingLanguageRepository.save(codingLanguage);
            return true;
        }
        catch (Exception e) {
            logger.warn("Could not save new Coding Language in the database");
        }
        return false;
    }

    @Override
    public List<CodingLanguage> findAll() {
        return this.codingLanguageRepository.findAll();
    }
}
