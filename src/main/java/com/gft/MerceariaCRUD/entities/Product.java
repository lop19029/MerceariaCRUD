package com.gft.MerceariaCRUD.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio.")
    private String name;

    @Digits(fraction = 2, integer = 10)
    private BigDecimal price;

    private Integer quantity;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dueDate;

    @URL
    private String image;

    @JsonManagedReference
    @ManyToOne
    private Supplier supplier;

}
