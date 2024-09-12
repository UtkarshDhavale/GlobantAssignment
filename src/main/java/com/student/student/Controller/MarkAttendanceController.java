package com.student.student.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.student.student.Model.AttendanceSchema;
import com.student.student.Model.StudentSchema;
import com.student.student.Repository.AttendanceRepository;
import com.student.student.Repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/attendance")
public class MarkAttendanceController {

    @Autowired
    AttendanceRepository AttendanceRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/getAttandance/{StudentObjId}")
    public ResponseEntity<Optional<List<AttendanceSchema>>> getAttendanceById(@PathVariable String StudentObjId){
        ObjectId StudentId = new ObjectId(StudentObjId);

        Optional<StudentSchema> student = studentRepository.findById(StudentId);

        List<ObjectId> attendanceObj = student.get().getAttendance();
    
        List<AttendanceSchema> result = new ArrayList<AttendanceSchema>();

        for(ObjectId obj: attendanceObj){
            result.add(AttendanceRepository.findById(obj).get());
        }
        return new ResponseEntity<Optional<List<AttendanceSchema>>>(Optional.of(result),HttpStatus.OK);
    }

    @PostMapping("/{StudentObjId}")
    public ResponseEntity<AttendanceSchema> markAttendance(@PathVariable String StudentObjId, @RequestBody AttendanceSchema attendance){
        
        AttendanceRepository.save(attendance);
        ObjectId StudentId = new ObjectId(StudentObjId);
        
        mongoTemplate.update(StudentSchema.class)
                    .matching(Criteria.where("id").is(StudentId))
                    .apply(new Update().push("attendance").value(attendance.get_id()))
                    .first();
        
        return new ResponseEntity<AttendanceSchema>(attendance,HttpStatus.CREATED);    
    }
}
