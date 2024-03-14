package org.myungkeun.shop_study.service;

import org.myungkeun.shop_study.payload.MemberDto;
import org.myungkeun.shop_study.payload.MembersResponseDto;
import org.springframework.stereotype.Service;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);

    MembersResponseDto getAllMember(int pageNo, int pageSize, String sortBy, String sortDir);

    MemberDto getMemberById(int id);

    String deleteMemberById(int id);

    MemberDto updateMemberById(int id, MemberDto memberDto);

}
