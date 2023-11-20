package com.example.springmvc01.statics;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum BookCategory {

    SCIENCE("Khoa học"),
    SCI_FI("Viễn tưởng"),
    NOVEL("Tiểu thuyết"),
    SHORT_STORY("Truyện ngắn"),
    KID("Thiếu nhi"),
    @JsonEnumDefaultValue OTHER("Khác")
    ;

    public String value;

    BookCategory(String value) {
        this.value = value;
    }

}
