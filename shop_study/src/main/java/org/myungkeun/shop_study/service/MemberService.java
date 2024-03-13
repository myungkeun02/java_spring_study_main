package org.myungkeun.shop_study.service;

import org.myungkeun.shop_study.entity.Member;
import org.myungkeun.shop_study.payload.MemberDto;
import org.myungkeun.shop_study.payload.MembersResponseDto;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);

    MembersResponseDto getAllMember(int pageNo, int pageSize, String sortBy, String sortDir);

    MemberDto getMemberById(int id);

    String deleteMemberById(int id);

    MemberDto updateMemberById(int id, MemberDto memberDto);

}
