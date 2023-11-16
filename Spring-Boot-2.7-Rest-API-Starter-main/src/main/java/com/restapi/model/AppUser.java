package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users") // don't use User
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    @NotEmpty
    @Size(min = 3, message = "Username should have at least 3 characters")
    private String username;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(min = 3, message = "Password should have at least 3 characters")
    private String password;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String name;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @CreationTimestamp
    private LocalDateTime createdAt;

//    private Boolean deleteFlag = false;

}
