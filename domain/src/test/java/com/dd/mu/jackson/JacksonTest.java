package com.dd.mu.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
public class JacksonTest {

    @Test
    public void json2BeanTest() {
        String jsonInput = "{\"address\":\"def addr\",\"age\":25,\"name\":\"HW\"}";
        ObjectMapper mapper = new ObjectMapper();
        Student student;
        try {
            student = mapper.readValue(jsonInput, Student.class);
            System.out.println(student.getAge());
            Assertions.assertEquals(25,student.getAge());
            System.out.println(student.getName());
            Assertions.assertEquals("HW",student.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listTeacherTest() throws JsonProcessingException {
        List<Teacher> list = new ArrayList<Teacher>();
        Teacher t = new Teacher();
        t.setId(2);
        t.setName("xx");
        list.add(t);

        t = new Teacher();
        t.setId(3);
        t.setName("yy");
        list.add(t);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(list);
        System.out.println(content);

        Teacher[] ts = mapper.readValue(content, Teacher[].class);
        Assertions.assertEquals(2, ts.length);
    }

    @Test
    public void testJavaType() {
        String jsonInput = "[{\"id\":2,\"name\":\"xx\"},{\"id\":3,\"name\":\"yy\"},{\"id\":4,\"name\":\"aa\"}]";
        ObjectMapper mapper = new ObjectMapper();
        List<Teacher> lst;
        try {
            // use getCollectionType Or T[].class
            lst = mapper.readValue(jsonInput, getCollectionType(mapper, List.class, Teacher.class));
            Assertions.assertEquals(3, lst.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Order(1)
    @Test
    public void complexObjectSerializedTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Clazz clazz = genClazz();
        try {
            // Java objects to JSON file
            mapper.writeValue(new File("src/test/resources/staff.json"), clazz);

            // Java objects to JSON string - compact-print
            String jsonString = mapper.writeValueAsString(clazz);

            System.out.println(jsonString);

            // Java objects to JSON string - pretty-print
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clazz);

            System.out.println(jsonInString2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Order(2)
    @Test
    public void complexObjectDeserializedTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON file to Java object
            Clazz clazz = mapper.readValue(new File("src/test/resources/staff.json"), Clazz.class);

            System.out.println(clazz);

            // pretty print
            String prettyStaff = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clazz);
            System.out.println(prettyStaff);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    private Clazz genClazz(){

        Clazz clazz = new Clazz();
        Teacher t = new Teacher();
        t.setId(2);
        t.setName("myTeacher");
        clazz.setTeacher(t);

        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setAge(20);
        student.setName("xx");
        student.setAddress("C2C");
        list.add(student);

        student = new Student();
        student.setAge(22);
        student.setName("yy");
        student.setAddress("M2C");
        list.add(student);

        clazz.setStudents(list);

        return clazz;
    }


    @Data
    static class Clazz {
        Teacher teacher;

        List<Student> students;
    }

    @Data
    static class Teacher {
        int id;
        String name;
    }

    @Data
    static class Student {
        private int age;
        private String name;
        private String address;
    }
}
