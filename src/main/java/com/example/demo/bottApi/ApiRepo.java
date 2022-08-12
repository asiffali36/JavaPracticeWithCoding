package com.example.demo.bottApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ApiRepo {

    private final JdbcTemplate jdbcTemplate;

    RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setCell(rs.getDouble("cell"));
            return customer;
        }
    };

    RowMapper<ModelClass> delegationRowMaper = new RowMapper<ModelClass>() {
        @Override
        public ModelClass mapRow(ResultSet rs, int rowNum) throws SQLException {
            ModelClass modelClass = new ModelClass();
            modelClass.setId(rs.getInt("id"));
            modelClass.setName(rs.getString("name"));
            modelClass.setStatus(rs.getString("designation"));
            modelClass.setImageUrl(rs.getString("imageurl"));
            return modelClass;
        }
    };


    @Autowired
    public ApiRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public List<Customer> getAllCustomers() {
////        String query = "SELECT * FROM customer";
////        return jdbcTemplate.query(query, customerRowMapper);
////    }

    public List<ModelClass> getRecord() {
        String query = "SELECT * FROM delegation";
        return jdbcTemplate.query(query, delegationRowMaper);
    }

    public int insertRecord(ModelClass modelClass) {
        String query = "insert into delegation(name,designation,imageurl) values(?,?,?)";
        int update = this.jdbcTemplate.update(query, modelClass.getName(), modelClass.getStatus(), modelClass.getImageUrl());
        return update;
    }

    public int deleteRecord(int id) {
        String query = "Delete from delegation where id=? ";
        return this.jdbcTemplate.update(query, id);
    }

    public int updateRecord(ModelClass modelClass,int id) {
        String query = "UPDATE delegation\n" +
                "SET name = ?, designation= ?, imageurl =?\n" +
                "WHERE id = ?";
        return this.jdbcTemplate.update(query, modelClass.getName(), modelClass.getStatus(), modelClass.getImageUrl(),id);
    }


}
