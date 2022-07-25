package common;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import static org.assertj.core.api.Assertions.*;

public class Utils {


    public String getRandomUserName(){
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public String getRandomAge(){
        return RandomStringUtils.randomNumeric(2);
    }

    public String getRandomSalary(){
        return RandomStringUtils.randomNumeric(4);
    }

    public int getRandomId(){
        return ((Integer.parseInt(RandomStringUtils.randomNumeric(1,2))));
    }

    public void verifyStatusCode(Response response, int expectedCode){
        assertThat(response.getStatusCode()).isEqualTo(expectedCode);
    }
}
