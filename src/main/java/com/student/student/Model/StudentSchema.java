package com.student.student.Model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSchema {
    @Id
    private ObjectId id;
    private String name;
    private String classname;
    private List<ObjectId> attendance;

    public StudentSchema(String name, String classname){
        this.name = name;
        this.classname = classname;
        //this.attendance = new ArrayList<ObjectId>();
    }
}
