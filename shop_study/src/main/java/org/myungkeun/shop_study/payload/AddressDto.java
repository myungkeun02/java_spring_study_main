package org.myungkeun.shop_study.payload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myungkeun.shop_study.entity.Member;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private int memberId;
    private String name;
    private int zipcode;
    private String addr;
    private String addrDetail;
    private String phone;
    private String request;
}
