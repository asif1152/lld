package org.example.types;

import org.example.exceptions.InValidColumnType;

public class IntegerColumnType implements BaseType{
    @Override
    public boolean validateType(Object val) throws InValidColumnType {
        if(!(val instanceof Integer))
            throw new InValidColumnType();
        return true;
    }
}
