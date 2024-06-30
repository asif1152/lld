package org.example.index;

import lombok.Getter;
import org.example.column.BaseColumn;
import org.example.exceptions.IndexCreateException;
import org.example.row.Row;

import java.util.List;
import java.util.Map;

@Getter
public abstract class BaseIndex {

    protected String name;
    protected BaseColumn indexColumn;
    protected BaseColumn pmKey;
    protected Map<Object, List<Object> > indexValVsPmKeyValMap;

    abstract public void index(Row row) throws IndexCreateException;

    abstract public List<Object> getIndexRows(Object val);

}
