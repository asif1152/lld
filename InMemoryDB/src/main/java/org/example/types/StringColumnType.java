package org.example.types;

import org.example.exceptions.InValidColumnType;

public class StringColumnType implements BaseType {
    @Override
    public boolean validateType(Object val) throws InValidColumnType {
        if(!(val instanceof String)){
            throw new InValidColumnType();
        }
        return true;
    }
}
