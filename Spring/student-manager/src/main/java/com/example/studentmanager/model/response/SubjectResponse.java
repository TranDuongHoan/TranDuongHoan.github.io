package com.example.studentmanager.model.response;

import com.example.studentmanager.statics.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SubjectResponse {

    private int id;
    private String name;
    private int credit;
    private SubjectType subjectType;
}
