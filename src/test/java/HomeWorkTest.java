import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Matchers;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import static org.mockito.Matchers.any;

public class HomeWorkTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172.0.32.12", "172.1.32.11", "172.1.33.11"})
    void byIpRUSTest(String s) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(s);
        if (s.equals(GeoServiceImpl.MOSCOW_IP)) {
            Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, "Lenina", 15), location);
        } else {
            Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, null, 0), location);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.44.183.149", "96.0.32.12", "96.1.32.11", "96.1.33.11"})
    void byIpUSATest(String s) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(s);
        if (s.equals(GeoServiceImpl.NEW_YORK_IP)) {
            Assertions.assertEquals(new Location("New York", Country.USA, " 10th Avenue", 32), location);
        } else {
            Assertions.assertEquals(new Location("New York", Country.USA, null, 0), location);
        }
    }

    @Test
    void localeTest() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String usa = localizationService.locale(Country.USA);
        String rus = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(usa,"Welcome");
        Assertions.assertEquals(rus,"Добро пожаловать");
    }

    @Test
    void test(){
        String expectedResult = "Welcome";
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = new LocalizationServiceImpl();

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Mockito.when(geoService.byIp((String) any())).thenReturn(new Location("", Country.USA, "", 0));

        String result = messageSender.send(new HashMap<String, String>());
        Assertions.assertEquals(expectedResult,result);

    }

    @Test
    void test1(){
        String expectedResult = "Добро пожаловать";
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = new LocalizationServiceImpl();

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Mockito.when(geoService.byIp((String) any())).thenReturn(new Location("", Country.RUSSIA, "", 0));

        String result = messageSender.send(new HashMap<String, String>());
        Assertions.assertEquals(expectedResult,result);

    }


}
