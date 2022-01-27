package com.gaur.datastore.repository.retrieve;

import com.gaur.datastore.domain.internal.DatabaseData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This supports the Retriver Service to do database related functions.
 */
@Repository
@Log4j2
public class RetrieverRepositoryImp implements RetrieverRepository {

    private static final String DB_TABLE = "data_store";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_KEY = "key_name";
    private static final String COLUMN_VALUE = "value";
    private static final String COLUMN_CREATION_TIME = "creation_time";

    private static final String SELECT_ALL_USING_KEY_SQL = "SELECT " + COLUMN_ID + "," +
        COLUMN_KEY + "," +
        COLUMN_VALUE + "," +
        COLUMN_CREATION_TIME +
        " FROM " + DB_TABLE +
        " WHERE " + COLUMN_KEY + "=:" + COLUMN_KEY;


    private NamedParameterJdbcTemplate jdbcTemplate;

    public RetrieverRepositoryImp(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<DatabaseData> getValueFromKey(String key) {
        return optionalResult(
            () -> jdbcTemplate.queryForObject(SELECT_ALL_USING_KEY_SQL,
                new MapSqlParameterSource(COLUMN_KEY, key),
                this::fromResultSetToDatabaseData));
    }

    private DatabaseData fromResultSetToDatabaseData(ResultSet rs, int rowNum) throws SQLException {
        return DatabaseData.builder().id(rs.getInt(COLUMN_ID))
            .key(rs.getString(COLUMN_KEY))
            .value(rs.getString(COLUMN_VALUE))
            .creationTime(Instant.ofEpochMilli(rs.getTimestamp(COLUMN_CREATION_TIME).getTime())).build();
    }

    private Optional<DatabaseData> optionalResult(Supplier<DatabaseData> query) {
        try {
            DatabaseData databaseData = query.get();
            return Optional.of(databaseData);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
