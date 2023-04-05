package com.sohu.bo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
    /**
     * createPeople函数名字代表spring容器里面的bean id 与xml定义bean对应
     */
    @Bean
    public People createPeople(){
        People people = new People();
        people.setSex(true);
        people.setStudent(createStudent());
        return people;
    }

    @Bean
    public Student createStudent() {
        Student student = new Student();
        student.setAge(18);
        student.setName("iewi");
        student.setEmail(createEmail());
        return student;
    }

    @Bean
    public Email createEmail() {
        Email email = new Email();
        email.setAddress("fgwgqg@qq.com");
        return email;
    }
}
