package org.myungkeun.shop_study.service.impl;

import org.modelmapper.ModelMapper;
import org.myungkeun.shop_study.entity.Member;
import org.myungkeun.shop_study.exception.ResourceNotFoundException;
import org.myungkeun.shop_study.payload.MemberDto;
import org.myungkeun.shop_study.payload.MembersResponseDto;
import org.myungkeun.shop_study.repository.MemberRepository;
import org.myungkeun.shop_study.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    private ModelMapper modelMapper;

    public MemberServiceImpl(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = mapToEntity(memberDto);
        Member newMember = memberRepository.save(member);
        MemberDto responseMember = mapToDto(newMember);
        return responseMember;
    }

    @Override
    public MembersResponseDto getAllMember(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Member> members = memberRepository.findAll(pageable);
        List<Member> listOfMembers = members.getContent();
        List<MemberDto> contents = listOfMembers.stream().map(member ->
                mapToDto(member)).collect(Collectors.toList());
        MembersResponseDto membersResponseDto = new MembersResponseDto();
        membersResponseDto.setContent(contents);
        membersResponseDto.setPageNo(members.getNumber());
        membersResponseDto.setPageSize(members.getSize());
        membersResponseDto.setTotalPages(members.getTotalPages());
        membersResponseDto.setTotalElements(members.getTotalElements());
        membersResponseDto.setLast(members.isLast());
        return membersResponseDto;
    }

    @Override
    public MemberDto getMemberById(int id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        MemberDto responseMember = mapToDto(member);
        return responseMember;
    }

    @Override
    public String deleteMemberById(int id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        memberRepository.delete(member);
        return "deleted";
    }

    @Override
    public MemberDto updateMemberById(int id, MemberDto memberDto) {
        Member oldMember = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        oldMember.setName(memberDto.getName());
        oldMember.setEmail(memberDto.getEmail());
        oldMember.setPassword(memberDto.getPassword());
        oldMember.setPhone(memberDto.getPhone());
        Member updateMember = memberRepository.save(oldMember);
        MemberDto responseMember = mapToDto(updateMember);
        return responseMember;
    }

    private MemberDto mapToDto(Member member) {
        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        return memberDto;
    }

    private Member mapToEntity(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        return member;
    }
}
