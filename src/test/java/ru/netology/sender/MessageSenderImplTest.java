package ru.netology.sender;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.mockito.Matchers.any;

public class MessageSenderImplTest {
    @ParameterizedTest(name = "textMessage = {0}, city = {1}, country = {2}, street = {3}, builing = {3}")
    @CsvSource({
            "Welcome,,USA,,0",
            "Добро пожаловать,,RUSSIA,,0",
    })
    void CheckingSendingOfALocalizedMessage(String testMessage, String city, Country country, String street,
                                            int builing) {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Mockito.when(geoService.byIp((String) any())).thenReturn(new Location(city, country, street, builing));

        String result = messageSender.send(new HashMap<String, String>());
        Assertions.assertEquals(testMessage, result);
    }
}
