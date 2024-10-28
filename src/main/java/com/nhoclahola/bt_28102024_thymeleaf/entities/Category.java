package com.nhoclahola.bt_28102024_thymeleaf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryId;

    @Column(name = "CategoryName", length = 255)
    @NotEmpty(message = "Không được bỏ trống")
    private String categoryName;

    @Column(name = "Image", length = 500)
    private String image;

    @Column(name = "Status")
    private int status;
}
