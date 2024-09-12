package com.student.student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.student.Model.StudentSchema;
import com.student.student.Repository.StudentRepository;

@RestController
@RequestMapping("/api/v1/student")
public class RegisterStudentController {

    @Autowired
    private StudentRepository StudentRepository;

     @PostMapping("/")
    public ResponseEntity<StudentSchema> createReview(@RequestBody StudentSchema student){
        
        StudentRepository.save(student);
        
        return new ResponseEntity<StudentSchema>(student,HttpStatus.CREATED);    
    }

}
