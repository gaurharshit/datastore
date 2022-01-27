package com.gaur.datastore.domain.internal;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 */
@Data
@Builder
public class DatabaseData {

    private int id;
    private String key;
    private String value;
    private Instant creationTime;
}
