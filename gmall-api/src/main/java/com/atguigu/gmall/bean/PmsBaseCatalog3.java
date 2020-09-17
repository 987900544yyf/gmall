package com.atguigu.gmall.bean;

import lombok.Data;

import javax.persistence.Id;

@Data
public class PmsBaseCatalog3 {

    @Id
    private String id;

    private String name;

    private String catalog2Id;
}
