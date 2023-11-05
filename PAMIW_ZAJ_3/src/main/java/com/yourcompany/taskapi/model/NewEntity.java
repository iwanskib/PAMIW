package com.yourcompany.taskapi.model;

import javax.persistence.*;

@Entity
public class NewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getid(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

}
