package kz.samgau.webkafka.model;

import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true, nullable = false)
    private String name;

    @ ManyToMany (mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
