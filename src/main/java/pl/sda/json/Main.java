package pl.sda.json;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RENT on 2017-03-02.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        User user1 = new User();
        user1.setFirstName("Jan");

        Address address1 = new Address();
        address1.setStreet("Baraniaka");
        address1.setCode("11-111");
        address1.setCity("Poznan");

        Address address2 = new Address();
        address1.setStreet("Mokotowska");
        address1.setCode("00-321");

        Map<String, Address> addresses = new HashMap<>();
        addresses.put("Home", address1);
        addresses.put("Work", address1);

        user1.setAddresses(addresses);

        List<String> childrenNames = Arrays.asList("Marek", "Zosia");
        user1.setChildrenNames(childrenNames);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user1);
        System.out.println(json);
    }
}
