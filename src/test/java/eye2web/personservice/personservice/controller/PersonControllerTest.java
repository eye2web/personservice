package eye2web.personservice.personservice.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private PersonsController personsController;

    @Test
    public void shouldConvertToCSVbase64Test() {
        final var compareToCSVBase64 = "aWQ7bmFtZTtiaXJ0aGRheTtwYXJlbnQxX2lkO3BhcmVudDFfbmFtZTtwYXJlbnQyX2lkO3BhcmVudDJfbmFtZTtwYXJ0bmVyX2lkO3BhcnRuZXJfbmFtZTtjaGlsZDFfaWQ7Y2hpbGQxX25hbWU7Y2hpbGQyX2lkO2NoaWxkMl9uYW1lO2NoaWxkM19pZDtjaGlsZDNfbmFtZQoyO1BldHJhIEFuZGVyc29uOzE5NzUwMTAxOzE7QWRhbTsxO0FkYW07MztQZXRlciBBbmRlcnNvbjs0O0t3aWsgQW5kZXJzb247NTtLd2VrIEFuZGVyc29uOzY7S3dhayBBbmRlcnNvbgozO1BldGVyIEFuZGVyc29uOzE5NzAwMTAxOzE7QWRhbTsxO0FkYW07MjtQZXRyYSBBbmRlcnNvbjs0O0t3aWsgQW5kZXJzb247NTtLd2VrIEFuZGVyc29uOzY7S3dhayBBbmRlcnNvbgo=";
        final var csvBase64 = personsController.getPersonListAsBas64CSV().getBody();

        Assertions.assertEquals(compareToCSVBase64, csvBase64);
    }
}
