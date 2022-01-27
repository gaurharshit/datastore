package com.gaur.datastore.service;

import com.gaur.datastore.api.model.Value;
import com.gaur.datastore.domain.internal.DatabaseData;
import com.gaur.datastore.repository.retrieve.RetrieverRepository;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This service handles the get requests for all the keys present in the database.
 */
@Service
@Log4j2
public class RetrieverServiceImp implements RetrieverService {

    private final RetrieverRepository retrieverRepository;

    RetrieverServiceImp(RetrieverRepository retrieverRepository) {
        this.retrieverRepository = retrieverRepository;
    }

    @Override
    public Optional<Value> getValueFromKey(String key) {
        final Optional<DatabaseData> databaseData = retrieverRepository.getValueFromKey(key);
        return databaseData.map(this::convertToModel);
    }

    Value convertToModel(DatabaseData databaseData) {
        return new Value(databaseData);
    }

}
