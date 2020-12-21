package ir.mhm.codechallange.config;


import ir.mhm.codechallange.service.CacheService;
import ir.mhm.codechallange.model.AppEvent;
import org.cache2k.Cache;
import org.cache2k.CacheEntry;
import org.cache2k.addon.UniversalResiliencePolicy;
import org.cache2k.event.CacheEntryCreatedListener;
import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    public CacheConfig(){}

    @Autowired
    CacheService cacheService;

    @Bean
    public CacheManager cacheManager() {
        return new SpringCache2kCacheManager("EventCacheManager")
                .addCaches(
                        b -> b.name("event").expireAfterWrite(200, TimeUnit.SECONDS).entryCapacity(10000).
                                setupWith(UniversalResiliencePolicy::enable,f->f.retryInterval(1,TimeUnit.SECONDS))
                                .permitNullValues(true).addListener(
                                new CacheEntryCreatedListener() {
                                    @Override
                                    public void onEntryCreated(Cache cache, CacheEntry cacheEntry) throws Exception {
                                        cacheService.shadow((AppEvent) cacheEntry);
                                    }
                                }

                        )
                )
                 .addCaches(b -> b.name("temp").entryCapacity(10000).permitNullValues(true));
    }

}
