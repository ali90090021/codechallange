package ir.mhm.codechallange.api;

import ir.mhm.codechallange.service.CacheService;
import ir.mhm.codechallange.model.AppEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/")
@RestController
public class EventAPI {

    public EventAPI(){}

    @Autowired
    CacheService cacheService;

    @GetMapping("event/{id}")
    public ResponseEntity<AppEvent> getBeerById(@PathVariable("id") long id){

        return new ResponseEntity<AppEvent>(cacheService.readEvent(id), HttpStatus.OK);
    }


    @PostMapping(path = "event")
    public ResponseEntity saveNewBeer(@RequestBody @Validated AppEvent appEventDto){
        return new ResponseEntity<AppEvent>(cacheService.addEvent(appEventDto), HttpStatus.CREATED);
    }
}
