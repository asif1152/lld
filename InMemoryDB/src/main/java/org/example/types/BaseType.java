package org.example.types;

import org.example.exceptions.InValidColumnType;

public interface BaseType {
    boolean validateType(Object val) throws InValidColumnType;
}
