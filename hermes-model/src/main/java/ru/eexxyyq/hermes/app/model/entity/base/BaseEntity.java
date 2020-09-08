package ru.eexxyyq.hermes.app.model.entity.base;

import lombok.Data;
import ru.eexxyyq.hermes.app.model.entity.person.Account;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class BaseEntity {
    private Long id;
    private LocalDateTime createdDT;
    private LocalDateTime modifiedDT;
    private Account createdBy;
    private Account modifiedBy;
}
