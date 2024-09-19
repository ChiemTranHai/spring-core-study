package com.example.tracking;

import java.util.Map;

public interface UsageTracked {

    void incrementUseCount(String key);

    Map<String, Integer> getUseCount();
}
