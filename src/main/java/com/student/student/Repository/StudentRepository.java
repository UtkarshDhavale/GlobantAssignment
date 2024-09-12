package com.student.student.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.student.student.Model.StudentSchema;


public interface StudentRepository extends MongoRepository<StudentSchema, ObjectId>{  

}
