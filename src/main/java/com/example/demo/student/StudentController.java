package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //@RestController is a convenience annotation for creating Restful controllers.
//@RestController annotation indicates that the class is controller where
// @RequestMapping Method assume @ResponseBody by Default(i.e REST APIs).
// . It's a convenient annotation that combines @Controller and @ResponseBody,
// which eliminates the need
// to annotate every request handling method of the controller class with the @ResponseBody annotation.
//The key difference is that you do not need to use @ResponseBody on each and every handler method
// once you annotate the class with @RestController.
@RequestMapping(path="api/v1/student") //Url use to call the current method



/* THIS IS THE API LAYER*/
public class StudentController {
    private final StudentService studentService;

    @Autowired //use @Autowired annotation on properties to get rid of the setter methods.
    // When you will pass values of autowired properties using <property> Spring will
    // automatically assign those properties with the passed values or references
    public StudentController(StudentService studentService) {
        /*
            iF YOU USE =new ClassName();
            //this.studentService = new StudentService();
            THis is hard coding, and it will be tightly coupled
            //We want it to be loosely coupled
            //So if we change it in future then it won't be a problem
          */
        //This will work, but it should be avoided,
        //Use dependency injection instead (@Autowired)
        this.studentService=studentService;
    }

    @GetMapping //When we just return a response when the request did not have any body
    public List<Student> getStudent()
    {
        return studentService.getStudents();
    }



    @PostMapping //When we return response when a request had a body
    public void registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }


//    http://localhost:8080/api/v1/student/1
    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId)
    {
        studentService.deleteStudent(studentId);
    }




//    http://localhost:8080/api/v1/student/1?name=shishir2&email=shishirchanged@gmail.com
//      http://localhost:8080/api/v1/student/1?name=shishir2
//
    @PutMapping(path="{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email)
    {
        studentService.updateStudent(studentId,name,email);
    }
}
