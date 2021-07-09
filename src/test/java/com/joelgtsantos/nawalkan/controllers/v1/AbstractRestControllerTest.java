package com.joelgtsantos.nawalkan.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Project: nawal-kan
 * Package: com.joelgtsantos.nawalkan.controllers.v1
 *
 * @author: Joel Ajucum
 * @since: 7/9/2021
 * @version: 0.1
 */
public abstract class AbstractRestControllerTest {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
