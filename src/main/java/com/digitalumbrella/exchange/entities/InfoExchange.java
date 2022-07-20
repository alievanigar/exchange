package com.digitalumbrella.exchange.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="digumb_exchange")
public class InfoExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_digumb_exchange")
    @SequenceGenerator(name = "seq_digumb_exchange", sequenceName = "ibs.seq_digumb_exchange", allocationSize = 1, initialValue = 1)

    private Long id;

    private String code;
    private String name;
    private String  value;


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "GMT+4")
    private Date dat;
}
