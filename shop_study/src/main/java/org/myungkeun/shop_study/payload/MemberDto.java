package org.myungkeun.shop_study.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
    private String email;
    private String name;
    private String password;
    private String phone;
}
