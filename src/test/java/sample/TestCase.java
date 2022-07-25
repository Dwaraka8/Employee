package sample;


import common.Constants;
import common.Utils;
import common.WebServiceMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.*;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase {
    Response response;

    public CreateUserRequest createUserRequest;
    public CreateUserResponse createUserResponse;
    Utils utils = new Utils();

    @BeforeAll
    public void setUp(){
        RestAssured.baseURI= Constants.HOST_NAME;
    }


    @Test
    @Order(1)
    @DisplayName("Verify user able to create employee")
    public void createEmployee(){
        createUserRequest=CreateUserRequest.builder().name(utils.getRandomUserName()).age(utils.getRandomAge()).salary(utils.getRandomSalary()).build();
        createUserResponse=WebServiceMethods.post(Constants.CREATE_EMPLOYEE,createUserRequest).as(CreateUserResponse.class);
        APIContext.setId1(createUserResponse.getData().getId());
    }

    @Test
    @Order(2)
    @DisplayName("Verify user able to delete particular employee data based on their id")
    public void deleteEmployee(){
        response = WebServiceMethods.delete(Constants.DELETE_EMPLOYEE+APIContext.getId1());
        utils.verifyStatusCode(response,200);
        assertThat(response.getBody().jsonPath().getString("message")).isEqualTo("Successfully! Record has been deleted");
    }

    @Test
    @Order(3)
    @DisplayName("Verify user able to view all employees data")
    public void getAllEmployees(){
        int id = utils.getRandomId();
        response=WebServiceMethods.get(Constants.GET_ALL_EMPLOYEES);
        utils.verifyStatusCode(response,200);
        int employeeId = Integer.parseInt(response.getBody().jsonPath().getList("data.id").get(id).toString());
        APIContext.setId(employeeId);
        APIContext.setName(response.getBody().jsonPath().getList("data.employee_name").get(id).toString());
    }

    @Test
    @Order(4)
    @DisplayName("Verify user able to view particular employee data based on their id")
    public void getEmployeeById(){
        response=WebServiceMethods.get(Constants.GET_EMPLOYEE_BY_ID+APIContext.getId());
        utils.verifyStatusCode(response,200);
        assertThat(response.getBody().jsonPath().getString("data.employee_name")).withFailMessage("Employee name not matched"+APIContext.getName()+ " Actual is : " + response.getBody().jsonPath().getString("data.employee_name")).isEqualTo(APIContext.getName());
    }

    @AfterEach
    public void waitForAPIToExecute() throws Exception{
        Thread.sleep(5000);
    }

}
