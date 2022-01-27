package com.gaur.datastore.repository.register;

import com.gaur.datastore.domain.internal.DataSet;
import com.gaur.datastore.domain.internal.DatabaseData;
import com.gaur.datastore.repository.common.AbstractRepositoryBuilder;
import com.gaur.datastore.repository.retrieve.RetrieverRepository;
import com.gaur.datastore.repository.retrieve.RetrieverRepositoryImp;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 */
class RegistrationRepositoryImpTest extends AbstractRepositoryBuilder {

    private RegistrationRepository registrationRepository;
    private RetrieverRepository retrieverRepository;

    RegistrationRepositoryImpTest() {
        registrationRepository = new RegistrationRepositoryImp(jdbcTemplate);
        retrieverRepository = new RetrieverRepositoryImp(jdbcTemplate);
    }

    @Test
    void shouldInsertNewKey() {
        String key = "test";
        String value = "testing";

        registrationRepository.save(DataSet.builder().key(key).value(value).build());

        final Optional<DatabaseData> databaseData = retrieverRepository.getValueFromKey(key);

        Assertions.assertEquals(key, databaseData.get().getKey());
        Assertions.assertEquals(value, databaseData.get().getValue());

    }

    @Test
    void delete() {
        String key = "harshit";

        registrationRepository.delete(key);

        final Optional<DatabaseData> databaseData = retrieverRepository.getValueFromKey(key);

        Assertions.assertTrue(databaseData.isEmpty());
    }
}