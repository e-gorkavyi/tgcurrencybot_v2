package org.eg.tgbot.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SPEND")
@Data
public class Spend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(name = "SPEND")
    private BigDecimal spend;

    @Column(name = "DATE")
    private Date date;
}
