package com.sohu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
class Student {
    private String name;

    private Integer age;
}

public class TestJackson {
    @Test
    public void display1() throws IOException {
        // List反序列化不能这样写
        String strs = "[{\"name\":\"sohu\",\"age\":18}]";
        ObjectMapper objectMapper = new ObjectMapper();
        // List<Student> list=objectMapper.readValue(strs, List<Student>.class);
        // System.out.println(list);
    }

    @Test
    public void display2() throws IOException {
        // 反序列化
        String strs = "[{\"name\":\"sohu\",\"age\":18}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> list = objectMapper.readValue(strs, List.class);
        // debug发现 ObjectMapper并不能将成员反序列化为Student类型，而是LinkedHashMap类型
        System.out.println(list);
    }

    @Test
    public void display3() throws JsonProcessingException {
        // 序列化
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setAge(18);
        student.setName("sohu");
        students.add(student);

        ObjectMapper objectMapper = new ObjectMapper();
        String strs = objectMapper.writeValueAsString(students);
        System.out.println(strs);
    }

    @Test
    public void display4() throws IOException {
        // 反序列化
        // Jackson ObjectMapper 提供了TypeReference支持对泛型对象的反序列化；
        // 对于获取泛型类型信息的场景，TypeReference是一个可以参考的通用解决方案。
        String strs = "[{\"name\":\"sohu\",\"age\":18}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> list=objectMapper.readValue(strs, new TypeReference<List<Student>>(){});
        System.out.println(list);
    }
}
