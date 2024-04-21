package kz.currencycontrol.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountFrom;
    private Long accountTo;
    @ManyToOne(fetch = FetchType.EAGER)
    private Currency currency;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @CreationTimestamp
    private Timestamp dateTime;
    private double sumTransaction;
    private double currentLimit;
    private boolean limitExceeded;

}
