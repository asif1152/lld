package org.example.index;

import lombok.Getter;
import org.example.column.BaseColumn;
import org.example.exceptions.ColumnValNotIndexed;
import org.example.exceptions.IndexCreateException;
import org.example.row.Row;

import java.util.*;

@Getter
public class SortedIndex extends BaseIndex{

//    private String name;
//    private BaseColumn indexColumn;
//    private BaseColumn pmKey;
//
//    private Map<Object, List<Object> > indexValVsPmKeyValMap;

    public SortedIndex(String nm, BaseColumn pmKey, BaseColumn indexColumn){
        this.name = nm;
        this.pmKey = pmKey;
        this.indexColumn = indexColumn;
        this.indexValVsPmKeyValMap = new HashMap<>();
    }

    @Override
    public void index(Row row) {
        if(row.getRowVal().get(pmKey.getName()) == null)
            throw new IndexCreateException();

        Object columnVal = row.getColumnVal(this.indexColumn.getName());
        if(!this.indexValVsPmKeyValMap.containsKey(columnVal)){
            this.indexValVsPmKeyValMap.put(columnVal, new ArrayList<>());
        }
        this.indexValVsPmKeyValMap.get(columnVal).add(row.getColumnVal(this.pmKey.getName()));
    }
    @Override
    public List<Object> getIndexRows(Object val) {
        if(!this.indexValVsPmKeyValMap.containsKey(val))
            throw new ColumnValNotIndexed();
        return this.indexValVsPmKeyValMap.get(val);
    }
}
