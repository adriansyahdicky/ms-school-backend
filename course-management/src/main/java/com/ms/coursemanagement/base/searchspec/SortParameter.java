package com.ms.coursemanagement.base.searchspec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortParameter implements Serializable {
    private String fieldName;
    private boolean isAscending;
}