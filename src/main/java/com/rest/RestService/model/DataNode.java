package com.rest.RestService.model;

import com.rest.RestService.Enum.Node;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

//@Builder
@Getter
@Setter
@ToString
@Entity
public class DataNode {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private Node type;
    @Column
    private List<DataNode> parent;
    @Column
    private Request request;
}


