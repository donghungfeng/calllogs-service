package com.example.gateservice.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginReponse {
    private String token;
    private Long staffId;
    private Long departmentId;
    private List<String> roleList;
}
