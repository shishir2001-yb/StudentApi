package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {



     @Bean
//             @Bean annotation tells that a method produces a bean to be managed by the Spring container.
//             It is a method-level annotation. During Java configuration (@Configuration), the method is
//             executed and its return value is registered as a bean within a BeanFactory.



    /*
    CommandLineRunner is a simple Spring Boot interface with a run method.
    Spring Boot will automatically call the run method of all beans implementing this
    interface after the application context has been loaded.
    */
     CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args -> {
            Student Shishir=new Student(
                    "Shishir",
                    "shds2001@gmail.com",
                    LocalDate.of(2001, Month.JULY,1)

            );
            Student Nisha=new Student(
                    "Nisha",
                    "nisha2001@gmail.com",
                    LocalDate.of(2005, Month.JULY,1)

            );
             repository.saveAll(List.of(Shishir,Nisha));

        };
    }
}
