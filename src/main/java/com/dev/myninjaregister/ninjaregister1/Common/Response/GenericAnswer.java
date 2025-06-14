package com.dev.myninjaregister.ninjaregister1.Common.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class GenericAnswer<T> {
    private T data;
    private String mensage;
}
