package org.example;


import org.example.Cache.ICache;
import org.example.factory.CacheEnum;
import org.example.factory.CacheFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;


public class CacheTest {

    private ICache<String, String> cacheObj;

    @BeforeEach
    public void setup(){
        this.cacheObj = new CacheFactory<String, String>().getCacheObj(CacheEnum.InMemoryMapCache);
    }

    @Test
    public void fetchAndPutItemsInCache(){

        cacheObj.put("name", "Asif Khan");
        cacheObj.put("addr", "Bglr");
        cacheObj.put("age", "29");

        Assertions.assertEquals(cacheObj.get("name"), "Asif Khan");
        Assertions.assertNotEquals(cacheObj.get("addr"), "Bglr1");

        cacheObj.put("hobby", "coding");
        Assertions.assertNull(cacheObj.get("age"));
    }



}
