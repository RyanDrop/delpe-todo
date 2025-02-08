package com.delpe.todo.domain.task;

import com.delpe.todo.domain.auditable.Auditable;
import com.delpe.todo.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
public class Task extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

}
