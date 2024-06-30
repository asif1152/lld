package org.example.column;

import lombok.Getter;
import org.example.column.validations.ColumnValidation;
import org.example.exceptions.InValidColumnType;
import org.example.types.BaseType;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BaseColumn {

    private String name;
    private BaseType columnType;

    private List<ColumnValidation> columnValidationsList;

    public BaseColumn(String name, BaseType columnType){
        this.name = name;
        this.columnType = columnType;
        this.columnValidationsList = new ArrayList<>();
    }

    public void addColumnValidation(ColumnValidation columnValidation){
        this.columnValidationsList.add(columnValidation);
    }

    public boolean validateColumn(Object val) {
        try{
            this.columnType.validateType(val);
        }catch (InValidColumnType ex){
            return false;
        }

        for(ColumnValidation columnValidation: this.columnValidationsList){
            try{
                if(!columnValidation.validate(val))
                    return false;
            }catch (InValidColumnType ex){
                return false;
            }
        }
        return true;
    }
}
