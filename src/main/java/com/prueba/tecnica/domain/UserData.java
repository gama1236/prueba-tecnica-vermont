package com.prueba.tecnica.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
public class UserData {

    private Long id;
    private String fullName;
    private String phone;
    private String address;
}
