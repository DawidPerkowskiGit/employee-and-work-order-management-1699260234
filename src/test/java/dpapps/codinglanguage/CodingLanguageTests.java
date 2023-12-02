package dpapps.codinglanguage;

import dpapps.model.CodingLanguage;
import dpapps.model.repository.service.CodingLanguageService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CodingLanguageTests {

    private final CodingLanguageService service;

    @Autowired
    public CodingLanguageTests(CodingLanguageService service) {
        this.service = service;
    }


    private boolean shouldAddCodingLanguage(String name) {
        CodingLanguage codingLanguage = new CodingLanguage();
        codingLanguage.setName(name);

        return service.add(codingLanguage);
    }

    @Disabled
    @Test
    public void shouldAddAllCodingLanguages() {
        boolean result = true;

        List<String> languageList = new ArrayList<>();

        languageList.add("JavaScript");
        languageList.add("HTML/CSS");
        languageList.add("Python");
        languageList.add("SQL");
        languageList.add("TypeScript");
        languageList.add("Bash/Shell");
        languageList.add("Java");
        languageList.add("C#");
        languageList.add("C++");
        languageList.add("C");
        languageList.add("PHP");
        languageList.add("PowerShell");
        languageList.add("Go");
        languageList.add("Rust");
        languageList.add("Lua");
        languageList.add("Dart");
        languageList.add("Assembly");
        languageList.add("Swift");
        languageList.add("R");
        languageList.add("Visual Basic (.Net)");
        languageList.add("MATLAB");
        languageList.add("VBA");
        languageList.add("Groovy");
        languageList.add("Delphi");
        languageList.add("Scala");
        languageList.add("Perl");

        for (String name : languageList
        ) {
            result = result & shouldAddCodingLanguage(name);
        }

        assertTrue(result);
    }
}
