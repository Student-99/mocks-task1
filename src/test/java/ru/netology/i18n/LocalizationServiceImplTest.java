package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {
    @ParameterizedTest(name = "textMessage = {0}, country = {1}")
    @CsvSource({
            "Welcome,USA",
            "Добро пожаловать,RUSSIA"
    })
    void localeTest(String expectedTextMessage, Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String textMessage = localizationService.locale(country);
        Assertions.assertEquals(textMessage, expectedTextMessage);
    }
}
