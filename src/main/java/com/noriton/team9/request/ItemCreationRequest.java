package com.noriton.team9.request;

import lombok.Data;

@Data
public class ItemCreationRequest {
    private String name;
    private int price;
    private Long sampleId;
}
