package org.example.row;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.example.column.BaseColumn;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Row {

    // columnName vs value of the column
    private Map<String, Object> rowVal;

    public Row(){
        this.rowVal = new HashMap<>();
    }
    public void addColumnVal(String column, Object val){
        this.rowVal.put(column, val);
    }

    public Object getColumnVal(String column){
        return this.rowVal.get(column);
    }
}
