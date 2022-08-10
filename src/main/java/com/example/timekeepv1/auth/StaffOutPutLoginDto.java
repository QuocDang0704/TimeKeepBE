package com.example.timekeepv1.auth;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StaffOutPutLoginDto  implements Serializable {
    private static final long serialVersionUID = -7817224776021728682L;

    private Integer id;
    private String namePosition;
    private String name;
    private Boolean gender;
    private String email;
    private String adress;
    private Integer idPosition;
    private String image;

    private List<String> roles;
    private String type = "Bearer";
    private String token;
}
