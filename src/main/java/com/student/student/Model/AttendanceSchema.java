package com.student.student.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "attendnce")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceSchema {
    @Id
    private ObjectId _id;
    private String date;
    private String status;

    public AttendanceSchema(String date, String status){
        this.date = date;
        this.status = status;
    }
}
