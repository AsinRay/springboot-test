package com.dd.mu.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JUnit Test Method Order demo
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
public class Jackson2Test {


    @Order(1)
    @Test
    public void json2complexObjectTest() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        Staff staff = createStaff();

        try {
            // Java objects to JSON file
            mapper.writeValue(new File("src/test/resources/staff.json"), staff);

            // Java objects to JSON string - compact-print
            String jsonString = mapper.writeValueAsString(staff);

            System.out.println(jsonString);

            // Java objects to JSON string - pretty-print
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);

            System.out.println(jsonInString2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Order(2)
    @Test
    public void complexObject2JsonTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // JSON file to Java object
            Staff staff = mapper.readValue(new File("src/test/resources/staff.json"), Staff.class);

            System.out.println(staff);

            // pretty print
            String prettyStaff = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(prettyStaff);

            System.out.println("=====================");

            // JSON string to Java object
            String jsonInString = "{\"name\":\"txinf.com\",\"age\":37,\"skills\":[\"java\",\"python\"]}";
            Staff staff2 = mapper.readValue(jsonInString, Staff.class);

            // compact print
            System.out.println(staff2);

            // pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff2);

            System.out.println(prettyStaff1);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Staff createStaff() {

        Staff staff = new Staff();

        staff.setName("txinf.com");
        staff.setAge(38);
        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
        Map<String, BigDecimal> salary = new HashMap() {{
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));
        }};
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));
        return staff;
    }


    @Data
    static class Staff{
        private String name;
        private int age;
        private String[] position;              //  Array
        private List<String> skills;            //  List
        private Map<String, BigDecimal> salary; //  Map
    }
}
