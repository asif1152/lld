package org.example.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.column.BaseColumn;
import org.example.row.Row;

import java.util.*;

@Getter
@AllArgsConstructor
public class BaseSchema {

    List<BaseColumn> columnList;
    Map<String, BaseColumn> columnMap;

    public BaseSchema(){
        this.columnList = new ArrayList<>();
        this.columnMap = new HashMap<>();
    }

    public void addColumn(BaseColumn column){
        this.columnList.add(column);
        this.columnMap.put(column.getName(), column);
    }

    public BaseColumn getColumn(String nm){
        return this.columnMap.get(nm);
    }

    // TODO: we can default value logic for column
    public boolean validate_row(Row row){
        for(String columnName: row.getRowVal().keySet()){
            BaseColumn columnTemp = this.columnMap.get(columnName);
            if(Objects.isNull(columnTemp))
                return false;
            if(!columnTemp.validateColumn(row.getColumnVal(columnName)))
                return false;
        }
        return row.getRowVal().keySet().size() == this.columnList.size();
    }



}
