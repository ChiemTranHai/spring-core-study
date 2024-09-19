package com.example.tracking.impl;

import com.example.tracking.UsageTracked;

import java.util.HashMap;
import java.util.Map;

public class DefaultUsageTracked implements UsageTracked {

    private Map<String,Integer> map = new HashMap<>();

    @Override
    public void incrementUseCount(String key) {
        if (map.containsKey(key)){
            map.put(key, map.get(key) + 1);
        } else{
            map.put(key, 1);
        }
    }

    @Override
    public Map<String, Integer> getUseCount() {
        return map;
    }
}
