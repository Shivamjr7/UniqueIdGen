package com.design.uniqueidgen.controller;

import com.design.uniqueidgen.entity.IdModel;
import com.design.uniqueidgen.repository.IdRepo;
import com.design.uniqueidgen.snowflake.SnowFlakeIdgen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
public class UniqueIdController {

    @Autowired
    IdRepo idRepo;


    private long sequence = 0L;
    private long lastTimestamp = -1L;

    @Autowired
    private Environment environment;
    @GetMapping(path="/v1/id/random")
    public String  getUniqueRandomId(){
        return UUID.randomUUID().toString();
    }

    @GetMapping(path="/v1/id/sorted")
    public long getSortedId(){
        return Instant.now().toEpochMilli();
    }

    @GetMapping(path = "/v1/id/auto")
    public long getIdFromDB(){

        IdModel idModel = new IdModel();
        return idRepo.save(idModel).getId();

    }

    @GetMapping(path = "/v1/id/snowflakeid")
    public long getSnowFlakeId(){
        long workerId = Long.parseLong(environment.getProperty("workerId"));
        long dataCenterId = Long.parseLong(environment.getProperty("dataCenterId"));

        SnowFlakeIdgen idgen = new SnowFlakeIdgen(workerId,dataCenterId);
        return idgen.generateId();
    }
}
