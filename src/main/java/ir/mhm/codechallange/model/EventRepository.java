package ir.mhm.codechallange.model;

import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface EventRepository  {

    void add(Object appEvent);

}
