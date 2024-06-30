package org.example.column.validations;

import org.example.exceptions.InValidColumnType;

import java.util.Objects;

public class IntegerRangeValidation implements ColumnValidation{

    private final Integer min_val;
    private final Integer max_val;
    public IntegerRangeValidation(){
        this.min_val = 0;
        this.max_val = 10000;
    }
    @Override
    public boolean validate(Object val) throws InValidColumnType {
        if(Objects.isNull(val))
            return false;
        if(val instanceof Integer){
            return ((Integer) val) >= min_val && ((Integer) val) <= max_val;
        }else{
            throw new InValidColumnType();
        }
    }
}
