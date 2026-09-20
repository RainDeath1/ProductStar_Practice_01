package com.product.star.homework;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class ContactDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private final RowMapper<Contact> contactRowMapper = (rs, rowNum) ->
            new Contact(
                    rs.getLong("ID"),
                    rs.getString("NAME"),
                    rs.getString("SURNAME"),
                    rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER")
            );

    public ContactDao(NamedParameterJdbcTemplate namedJdbcTemplate){
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Contact> getAllContacts(){
        String sql = "SELECT ID, NAME, SURNAME, EMAIL, PHONE_NUMBER\n" +
                "                                FROM CONTACT";
        return namedJdbcTemplate.query(sql,contactRowMapper);
    }
    public Contact getContact(long contactId) {
        String sql = "SELECT ID, NAME, SURNAME, EMAIL, PHONE_NUMBER\n" +
                "                FROM CONTACT\n" +
                "                WHERE ID = :contactId";

        var params = new MapSqlParameterSource()
                .addValue("contactId", contactId);

        return namedJdbcTemplate.queryForObject(
                sql,
                params,
                contactRowMapper
        );
    }

    public long addContact(Contact contact) {
        String sql = "INSERT INTO CONTACT (NAME, SURNAME, EMAIL, PHONE_NUMBER) " +
                "VALUES (:name, :surname, :email, :phone) " +
                "RETURNING ID";

        var params = new MapSqlParameterSource()
                .addValue("name", contact.getName())
                .addValue("surname", contact.getSurname())
                .addValue("email", contact.getEmail())
                .addValue("phone", contact.getPhone());

        Long id = namedJdbcTemplate.queryForObject(
                sql,
                params,
                Long.class
        );

        return id;
    }
    public void updatePhoneNumber(long contactId, String phoneNumber) {
        String sql = "                UPDATE CONTACT\n" +
                "                SET PHONE_NUMBER = :phoneNumber\n" +
                "                WHERE ID = :contactId";

        var params = new MapSqlParameterSource()
                .addValue("contactId", contactId)
                .addValue("phoneNumber", phoneNumber);

        namedJdbcTemplate.update(sql, params);
    }

    public void updateEmail(long contactId, String email) {
        String sql = "UPDATE CONTACT\n" +
                "                SET EMAIL = :email\n" +
                "                WHERE ID = :contactId";

        var params = new MapSqlParameterSource()
                .addValue("contactId", contactId)
                .addValue("email", email);

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteContact(long contactId) {
        String sql = "DELETE FROM CONTACT\n" +
                "                WHERE ID = :contactId";

        var params = new MapSqlParameterSource()
                .addValue("contactId", contactId);

        namedJdbcTemplate.update(sql, params);
    }
}


