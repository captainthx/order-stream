package com.yutsuki.order_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@ToString(exclude = {"password"})
@Entity(name = "users")
@Table
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {

    @Column(name="username" ,length = 30)
    private String username;
    private String password;
}
