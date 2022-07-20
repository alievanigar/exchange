package com.digitalumbrella.exchange.reporitories;

import com.digitalumbrella.exchange.entities.InfoExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InfoExchangeRepository extends JpaRepository<InfoExchange,Long>, JpaSpecificationExecutor<InfoExchange> {

    @Query(value = "from InfoExchange where  dat= :date")
    List<InfoExchange> findByDat( @Param("date") Date date);

    @Query(value = "from InfoExchange where " +
            " (:date is null or dat=:date) " +
            "and (:valyuta is null or code = :valyuta) ")
    List<InfoExchange> findWithParam(@Param("date") Date date, @Param("valyuta") String valyuta );

    List<InfoExchange> findByName(String name);
}
