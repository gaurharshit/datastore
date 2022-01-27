package com.gaur.datastore.repository.register;

import com.gaur.datastore.domain.internal.DataSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This supports the RegistrationService to do database related functions.
 */
@Repository
@Log4j2
public class RegistrationRepositoryImp implements RegistrationRepository {

    private static final String DB_TABLE = "data_store";
    private static final String COLUMN_KEY = "key_name";
    private static final String COLUMN_VALUE = "value";
    private static final String COLUMN_CREATION_TIME = "creation_time";

    private static final String INSERT_SQL = "INSERT INTO " + DB_TABLE
        + "( " + COLUMN_KEY + ","
        + COLUMN_CREATION_TIME + ","
        + COLUMN_VALUE + ")"
        + " VALUES ("
        + ":" + COLUMN_KEY + ","
        + ":" + COLUMN_CREATION_TIME + ","
        + ":" + COLUMN_VALUE + ")";
    private static final String DELETE_SQL = "DELETE FROM " + DB_TABLE +
        " WHERE " + COLUMN_KEY + "=:" + COLUMN_KEY;

    private static final String GET_SQL = "SELECT " + COLUMN_KEY + " FROM " + DB_TABLE +
        " WHERE " + COLUMN_KEY + "=:" + COLUMN_KEY;

    private static final String UPDATE_SQL = "UPDATE " + DB_TABLE +
        " SET " +
        COLUMN_VALUE + "=:" + COLUMN_VALUE +
        " WHERE " +
        COLUMN_KEY + "=:" + COLUMN_KEY;


    private NamedParameterJdbcTemplate jdbcTemplate;

    RegistrationRepositoryImp(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DataSet save(DataSet dataSet) {
        if (!isKeyRegisteredAlready(dataSet.getKey())) {
            log.debug("Creating new key entry in the database with key:{} and value:{}",
                dataSet.getKey(), dataSet.getValue());

            insertApplicationInfo(dataSet);

            log.debug("Creation for key:{} successful", dataSet.getKey());

            return dataSet;
        } else {
            log.debug("Updating key entry in the database with key:{} and value:{}",
                dataSet.getKey(), dataSet.getValue());
            updateApplicationInfo(dataSet);
            log.debug("Update for key:{} successful", dataSet.getKey());
        }
        return dataSet;
    }

    private void insertApplicationInfo(DataSet dataSet) {
        jdbcTemplate.update(INSERT_SQL, new MapSqlParameterSource()
            .addValue(COLUMN_KEY, dataSet.getKey(), Types.VARCHAR)
            .addValue(COLUMN_CREATION_TIME, Timestamp.from(Instant.now()), Types.TIMESTAMP)
            .addValue(COLUMN_VALUE, dataSet.getValue(), Types.VARCHAR));
    }

    private void updateApplicationInfo(DataSet databaseData) {
        final int rows = jdbcTemplate.update(UPDATE_SQL, new MapSqlParameterSource()
            .addValue(COLUMN_KEY, databaseData.getKey(), Types.VARCHAR)
            .addValue(COLUMN_VALUE, databaseData.getValue(), Types.VARCHAR));
        if (rows != 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        }
    }

    private boolean isKeyRegisteredAlready(String key) {
        try {
            jdbcTemplate.queryForObject(GET_SQL,
                new MapSqlParameterSource(COLUMN_KEY, key),
                this::getKeyFromResultSet);
            return true;
        } catch (IncorrectResultSizeDataAccessException e) {
            log.debug("No record found in database for key:{} ", key);
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        return jdbcTemplate.update(DELETE_SQL, new MapSqlParameterSource(COLUMN_KEY, key)) > 0;
    }

    private String getKeyFromResultSet(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString(COLUMN_KEY);
    }
}
