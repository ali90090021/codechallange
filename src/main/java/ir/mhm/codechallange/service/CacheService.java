package ir.mhm.codechallange.service;

import ir.mhm.codechallange.model.AppEvent;
import ir.mhm.codechallange.model.EventRepository;
import org.cache2k.Cache;
import org.cache2k.CacheManager;
import org.cache2k.operation.CacheControl;
import org.cache2k.operation.CacheStatistics;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheService {

    public CacheService(){}

    Cache tempCache = CacheManager.getInstance("EventCacheManager").getCache("temp");
    Cache eventCache = CacheManager.getInstance("EventCacheManager").getCache("event");
    CacheStatistics cacheStatistics = CacheControl.of(tempCache).sampleStatistics();


    public long getCount() {
        return cacheStatistics.getGetCount();
    }

    public Map getAll() {
        return tempCache.peekAll(tempCache.keys());
    }

    public void removeAll() {
        tempCache.removeAll();
    }

    public AppEvent shadow(AppEvent appEvent) {
        tempCache.putIfAbsent(appEvent.getTimestamp(), appEvent.getEvent());
        return appEvent;
    }

    public AppEvent addEvent(AppEvent appEvent) {
        eventCache.putIfAbsent(appEvent.getTimestamp(), appEvent.getEvent());
        return appEvent;
    }

    public AppEvent readEvent(long id) {
        return (AppEvent) eventCache.get(id);
    }

}
