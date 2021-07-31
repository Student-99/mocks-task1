package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {
    @ParameterizedTest(name = "IP = {0}, city = {1}, country = {2}, street = {3}, builing = {3}")
    @CsvSource(value = {
            "172.0.32.11,Moscow,RUSSIA,Lenina,15",
            "172.0.32.12,Moscow,RUSSIA,null,0",
            "172.1.32.12,Moscow,RUSSIA,null,0",
            "172.0.33.12,Moscow,RUSSIA,null,0",
            "172.1.33.12,Moscow,RUSSIA,null,0",
            "96.44.183.149,New York,USA,10th Avenue,32",
            "96.0.32.12,New York,USA,null,0",
            "96.1.32.12,New York,USA,null,0",
            "96.0.33.12,New York,USA,null,0",
            "96.1.33.12,New York,USA,null,0"
    }, nullValues = {"null"})
    void byIpTest(String Ip, String city, Country country, String street, int builing) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(Ip);
        Assertions.assertEquals(new Location(city, country, street, builing), location);
    }
}
