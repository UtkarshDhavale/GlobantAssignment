package com.student.student.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.student.student.Model.AttendanceSchema;


public interface AttendanceRepository extends MongoRepository<AttendanceSchema,ObjectId>{

}
