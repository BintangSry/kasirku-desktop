package com.bintang.kasir.components;

import java.sql.ResultSet;
import java.util.Map;

public interface FormHandler {
    void setData(ResultSet rs);
    Map<String, Object> getFormData();
}
