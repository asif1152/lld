package org.example.column.validations;

import org.example.exceptions.InValidColumnType;

public interface ColumnValidation {

    boolean validate(Object val) throws InValidColumnType;
}
