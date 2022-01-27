package com.gaur.datastore.repository.retrieve;

import com.gaur.datastore.domain.internal.DatabaseData;
import com.gaur.datastore.repository.common.AbstractRepositoryBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 */
class RetrieveRepositoryImpTest extends AbstractRepositoryBuilder {

    private final RetrieverRepository retrieverRepository;

    RetrieveRepositoryImpTest() {
        retrieverRepository = new RetrieverRepositoryImp(jdbcTemplate);
    }


    @Test
    void shouldGetValueByKey() throws ParseException {
        final DatabaseData expectedDatabaseData = DatabaseData.builder().id(2).key("foobar").value("barfoo")
            .creationTime(getFormattedTime("2022-01-20 07:23:48.112")).build();

        final Optional<DatabaseData> databaseData = retrieverRepository.getValueFromKey(
            "foobar");

        Assertions.assertEquals(expectedDatabaseData, databaseData.get());
    }

    private Instant getFormattedTime(String time) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(time).toInstant();
    }

    @Test
    void shouldGetEmptyValueIfKeyNotPresent() {
        final Optional<DatabaseData> databaseData = retrieverRepository.getValueFromKey(
            "N/A");

        Assertions.assertTrue(databaseData.isEmpty());

    }


}