package kz.currencycontrol.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
@Entity
@Table(name = "limits")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double currentLimit;
    private double sum;
    private Long accountNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @UpdateTimestamp
    private Timestamp limitDate;
}
