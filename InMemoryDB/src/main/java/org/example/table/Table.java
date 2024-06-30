package org.example.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.column.BaseColumn;
import org.example.exceptions.InValidRowException;
import org.example.exceptions.IndexCreateException;
import org.example.index.BaseIndex;
import org.example.index.SortedIndex;
import org.example.row.Row;
import org.example.schema.BaseSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Table {

    private String name;
    private  String pmKeyColName;
    private BaseColumn pmCol;
    private BaseSchema schema;
    private List<Row> rowList;
    private Map<Object, Row> objectRowMap;
    private List<BaseIndex> indexList;

    public Table(String nm, String pmKeyColName, BaseSchema schema){
        this.name = nm;
        this.pmKeyColName = pmKeyColName;
        this.schema = schema;
        this.pmCol = this.schema.getColumn(pmKeyColName);
        this.rowList = new ArrayList<>();
        this.objectRowMap = new HashMap<>();
        this.indexList = new ArrayList<>();
        // Default primary key index
        this.indexList.add(new SortedIndex("primary_key_index", this.pmCol, this.pmCol));
    }

    // insert row
    public void addRow(Row row) throws InValidRowException{
        if(!this.schema.validate_row(row)){
            throw new InValidRowException();
        }
        for(BaseIndex index: this.indexList){
            try{
                index.index(row);
            }catch (IndexCreateException ex){
                throw new InValidRowException();
            }
        }
        this.rowList.add(row);
        this.objectRowMap.put(row.getColumnVal(this.pmKeyColName), row);
    }

    public List<Row> queryRows(Map<String, Object> filterColVal){
        List<Row> resultRows = new ArrayList<>();
        if (filterColVal.containsKey(this.pmKeyColName)){
            resultRows.add(this.objectRowMap.get(filterColVal.get(this.pmKeyColName)));
            return resultRows;
        }

        for(BaseIndex index: this.indexList){
            if (!filterColVal.containsKey(index.getIndexColumn().getName()))
                continue;
            List<Object> tmpFilterPmKeys = index.getIndexRows(filterColVal.get(index.getIndexColumn().getName()));

        }

        for(String colName: filterColVal.keySet()){
            Object colVal = filterColVal.get(colName);

        }
        return null;
    }


}
