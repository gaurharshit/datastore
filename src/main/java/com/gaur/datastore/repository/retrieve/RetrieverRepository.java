package com.gaur.datastore.repository.retrieve;

import com.gaur.datastore.domain.internal.DatabaseData;
import java.util.Optional;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 */
public interface RetrieverRepository {

    Optional<DatabaseData> getValueFromKey(String key);
}
