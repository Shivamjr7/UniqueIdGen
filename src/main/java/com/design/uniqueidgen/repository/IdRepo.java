package com.design.uniqueidgen.repository;

import com.design.uniqueidgen.entity.IdModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IdRepo extends CrudRepository<IdModel, Long> {


}
