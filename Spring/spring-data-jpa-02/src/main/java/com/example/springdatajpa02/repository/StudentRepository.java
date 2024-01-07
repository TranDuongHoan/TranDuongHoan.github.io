package com.example.springdatajpa02.repository;

import com.example.springdatajpa02.model.request.SearchStudentRequest;
import com.example.springdatajpa02.model.response.StudentDataResponse;
import com.example.springdatajpa02.model.response.StudentResponse;
import com.example.springdatajpa02.util.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class StudentRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<StudentDataResponse> searchStudent(SearchStudentRequest request) {
        String sql = "select " +
                "id, " +
                "name, " +
                "address, " +
                "class_name className " +
                "from students s\n" +
                "where 1 = 1\n";

        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasText(request.getName())) {
            sql += "and s.name like :name\n";
            parameters.put("name", "%" + StringUtil.escapeWildCardCharacter(request.getName()) + "%");
        }

        if (StringUtils.hasText(request.getAddress())) {
            sql += "and s.address like :address\n";
            parameters.put("address", "%" + StringUtil.escapeWildCardCharacter(request.getAddress()) + "%");
        }

        sql += " limit " + request.getPageSize();
        if (request.getCurrentPage() != 0){
            sql += " offset " + request.getCurrentPage() * request.getPageSize();
        }

        return namedParameterJdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(StudentDataResponse.class));
    }

    public Long countStudent(SearchStudentRequest request) {
        String sql = "select count(*) total_record\n "+
                "from students s\n" +
                "where 1 = 1\n";

        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasText(request.getName())) {
            sql += "and s.name like :name\n";
            parameters.put("name", "%" + StringUtil.escapeWildCardCharacter(request.getName()) + "%");
        }

        if (StringUtils.hasText(request.getAddress())) {
            sql += "and s.address like :address\n";
            parameters.put("address", "%" + StringUtil.escapeWildCardCharacter(request.getAddress()) + "%");
        }

        sql += " limit " + request.getPageSize();
        if (request.getCurrentPage() != 0){
            sql += " offset " + (request.getCurrentPage() -1) * request.getPageSize();
        }

        return namedParameterJdbcTemplate.queryForObject(sql, parameters, Long.class);
    }

    public List<StudentDataResponse> searchStudentVer2(SearchStudentRequest request) {
        String sql = "select " +
                "id, " +
                "name, " +
                "address, " +
                "class_name className " +
                "from students s\n" +
                "where 1 = 1\n";

        String searchCondition = " where 1 = 1 ";

        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasText(request.getName())) {
            sql += "and s.name like :name\n";
            parameters.put("name", "%" + StringUtil.escapeWildCardCharacter(request.getName()) + "%");
        }

        if (StringUtils.hasText(request.getAddress())) {
            sql += "and s.address like :address\n";
            parameters.put("address", "%" + StringUtil.escapeWildCardCharacter(request.getAddress()) + "%");
        }

        sql += " limit " + request.getPageSize();
        if (request.getCurrentPage() != 0){
            sql += " offset " + request.getCurrentPage() * request.getPageSize();
        }

        sql =  sql.replace("{search_condition}", searchCondition);
        sql =  sql.replace("{limit_number}", Integer.valueOf(request.getPageSize()).toString());
        String offsetNumber = "0";
        if()

        return namedParameterJdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(StudentDataResponse.class));

}
