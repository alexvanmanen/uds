package nl.qien.uren.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UrenRegistratie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long employeeId;
    private Long projectId;
    private Long aantalUren;
    private LocalDate datum;
}