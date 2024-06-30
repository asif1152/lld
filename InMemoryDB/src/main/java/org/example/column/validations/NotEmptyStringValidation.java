package org.example.column.validations;

import org.example.exceptions.InValidColumnType;
import org.example.types.StringColumnType;

import java.util.Objects;

public class NotEmptyStringValidation implements ColumnValidation{

    @Override
    public boolean validate(Object val) throws InValidColumnType {
        if(Objects.isNull(val)){
            return false;
        }
        if(val instanceof String){
            return ((String) val).length() != 0;
        }else{
            throw new InValidColumnType();
        }

    }
}
