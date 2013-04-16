package com.gulf.web.controller.api.v1;

import java.util.HashMap;
import java.util.Map;

import com.gulf.constants.ApiConstants;

public class ApiTemplate {
    public static Map<String, Object> fillCommonData() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ApiConstants.RESPONSE, new HashMap<String, Object>());
        result.put(ApiConstants.ERR_CODE, "");
        result.put(ApiConstants.ERR_MSG, "");
        return result;
    }
}
