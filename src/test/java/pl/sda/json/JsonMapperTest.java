package pl.sda.json;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by RENT on 2017-03-02.
 */
public class JsonMapperTest {
    @Test
    public void test1() throws IOException {
        String jasonToDeserialize = "{\"firstName\":\"Jan\",\"lastName\":null,\"addresses\":{\"Work\":{\"street\":\"Mokotowska\",\"code\":\"00-321\",\"city\":\"Poznan\"},\"Home\":{\"street\":\"Mokotowska\",\"code\":\"00-321\",\"city\":\"Poznan\"}},\"childrenNames\":[\"Marek\",\"Zosia\"]}\n";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jasonToDeserialize, User.class);

        Assert.assertEquals("Jan", user.getFirstName());
        Assert.assertNotNull(user.getChildrenNames());
        Assert.assertTrue(user.getChildrenNames() != null);
        Assert.assertTrue(user.getChildrenNames().size() == 2);
        Assert.assertEquals(Arrays.asList("Marek", "Zosia"), user.getChildrenNames());
    }

    @Test
    public void test2() throws IOException {
        User user = new UserBuilder().withFirstName("Jan").withLastName("Kowalski")
                .withAddress("Work", new AddressBuilder().withCity("Poznan").withCode("60-111").withStreet("Baraniaka").build())
                .withAddress("Home", new AddressBuilder().withCity("Warszawa").withCode("00-321").withStreet("Mokotowska").build())
                .withChildrenNames(Arrays.asList("Marek", "Zosia"))
                .build();
        ObjectMapper mapper2 = new ObjectMapper();
        String jsonMapper = mapper2.writeValueAsString(user);

        Assert.assertEquals("Jan", user.getFirstName());
        Assert.assertTrue(user.getChildrenNames().size() == 2);
        Assert.assertTrue(jsonMapper.contains("\"firstName\":\"Jan\""));
        Assert.assertTrue(jsonMapper.contains("Kowalski"));
        Assert.assertTrue(jsonMapper.contains("Zosia"));
    }

    @Test //nie wyswietla dzieci
    public void test3() throws IOException {
        User user = new UserBuilder().withFirstName("Jan").withLastName("Kowalski")
                .withAddress("Work", new AddressBuilder().withCity("Poznan").withCode("60-111").withStreet("Baraniaka").build())
                .withAddress("Home", new AddressBuilder().withCity("Warszawa").withCode("00-321").withStreet("Mokotowska").build())
                .withChildrenNames(null)
                .build();

        ObjectMapper mapper2 = new ObjectMapper();
        String serializedObject = mapper2.writeValueAsString(user);
        System.out.println(serializedObject);
    }


}
