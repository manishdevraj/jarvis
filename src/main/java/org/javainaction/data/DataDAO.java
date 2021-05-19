package org.javainaction.data;

import java.util.Iterator;
import java.util.List;

public interface DataDAO<T> {
    Iterator<T> searchData(Object something) throws Exception;
    List<T> fetchData() throws Exception;
}
