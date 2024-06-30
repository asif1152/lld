package org.example;

import org.example.Cache.ICache;
import org.example.exceptions.KeyNotFoundException;
import org.example.factory.CacheEnum;
import org.example.factory.CacheFactory;

public class Main {
    public static void main(String[] args) {

        CacheFactory<String, String> cacheFactory = new CacheFactory<>();
        ICache<String, String> cacheObj = cacheFactory.getCacheObj(CacheEnum.InMemoryMapCache);

        cacheObj.put("name", "Asif Khan");
        cacheObj.put("addr", "Bglr");
        cacheObj.put("age", "29");

        System.out.printf("name is: %s\n", cacheObj.get("name"));
        System.out.printf("addr is: %s\n", cacheObj.get("addr"));

        cacheObj.put("hobby", "coding");
        System.out.printf("age is: %s\n",cacheObj.get("age"));
        cacheObj.put("religion", "Islam");
        cacheObj.put("species", "human");

        System.out.printf("hobby is: %s\n", cacheObj.get("hobby"));
        System.out.printf("religion is: %s\n", cacheObj.get("religion"));
        System.out.printf("species is: %s\n", cacheObj.get("species"));



    }
}