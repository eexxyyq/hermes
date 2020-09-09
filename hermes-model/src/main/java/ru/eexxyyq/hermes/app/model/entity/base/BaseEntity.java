package ru.eexxyyq.hermes.app.model.entity.base;

import lombok.Data;
import ru.eexxyyq.hermes.app.model.entity.person.Account;

import javax.persistence.*;
import java.time.LocalDateTime;


@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATED_DT", nullable = false, updatable = false)
    private LocalDateTime createdDT;

    @Column(name = "MODIFIED_DT", insertable = false)
    private LocalDateTime modifiedDT;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY", updatable = false)
    private Account createdBy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODIFIED_BY", insertable = false)
    private Account modifiedBy;

    @PrePersist
    public void prePersist() {
        if (getId() == null || getId() == 0) {
            setCreatedDT(LocalDateTime.now());
        }
    }
}
