package com.hexad.librarymanagment.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private Integer userId;
    private String name;
    @OneToMany
    @Cascade({CascadeType.ALL})
    @ApiModelProperty(hidden = true)
    private List<Book> borrowBookList;
}
