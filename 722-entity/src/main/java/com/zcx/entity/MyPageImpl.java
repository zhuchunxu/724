package com.zcx.entity;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Data
public class MyPageImpl<T> {

    private  long totalElements=0;
    private  List<T> content=null;
    private  int totalPages=0;
    private  int number=0;
    private  int size=3;
    public MyPageImpl(Page<T> pil) {
        this.totalElements = pil.getTotalElements();
        this.content =pil.getContent();
        this.totalPages =pil.getTotalPages();
        this.number =pil.getNumber();
        this.size = pil.getSize();
    }

    public MyPageImpl() {

    }



}
