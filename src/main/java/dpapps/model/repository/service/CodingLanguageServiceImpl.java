package dpapps.model.repository.service;

import dpapps.model.CodingLanguage;
import dpapps.model.Project;
import dpapps.model.repository.CodingLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingLanguageServiceImpl implements CodingLanguageService{

    private final CodingLanguageRepository codingLanguageRepository;


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
        return this.codingLanguageRepository.findByName(name);
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
            System.out.println("Could not new Coding Language to the database");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CodingLanguage> findAll() {
        return this.codingLanguageRepository.findAll();
    }
}
