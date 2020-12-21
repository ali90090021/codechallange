package ir.mhm.codechallange.service;

import ir.mhm.codechallange.model.EventRepository;
import ir.mhm.codechallange.service.CacheService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduledTasks {

    AtomicInteger timeCounter = new AtomicInteger();
    EventRepository eventRepository;
    CacheService cacheService = new CacheService();

    @Scheduled(fixedRate = 1000)
    public void PeriodicBackup() {

        long count = cacheService.getCount();

        if (count >= 4) {
            step();
            return;

        }
        if (count > 0 && timeCounter.get() == 9) {
            step();
            return;
        }
        timeCounter.incrementAndGet();

    }

    private void step() {
        Map res = cacheService.getAll();
        timeCounter.set(0);
        eventRepository.add(res);
        cacheService.removeAll();

    }
}
