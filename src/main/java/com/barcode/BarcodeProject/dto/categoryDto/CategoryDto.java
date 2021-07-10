package com.barcode.BarcodeProject.dto.categoryDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@Builder
public class CategoryDto {
    private int id;
    private String name;
}
