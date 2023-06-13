package Test;

import Shared.UserRequest;
import User.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class Test_1 {
    public static void main(String[] args) throws JsonProcessingException {
        User user = new User("111", "2222", "3333", "5555");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(user);
        System.out.println(jsonData);
    }
}
