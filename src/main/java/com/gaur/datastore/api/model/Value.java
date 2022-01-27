package com.gaur.datastore.api.model;

import com.gaur.datastore.domain.internal.DatabaseData;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 */
@Data
@NoArgsConstructor
public class Value {

    private String value;

    public Value(DatabaseData data) {
        this.value = data.getValue();
    }
}
