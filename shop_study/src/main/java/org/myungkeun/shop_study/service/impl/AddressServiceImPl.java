package org.myungkeun.shop_study.service.impl;

import org.modelmapper.ModelMapper;
import org.myungkeun.shop_study.entity.Address;
import org.myungkeun.shop_study.entity.Member;
import org.myungkeun.shop_study.exception.ResourceNotFoundException;
import org.myungkeun.shop_study.payload.*;
import org.myungkeun.shop_study.repository.AddressRepository;
import org.myungkeun.shop_study.repository.MemberRepository;
import org.myungkeun.shop_study.service.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class AddressServiceImPl implements AddressService {
    private ModelMapper modelMapper;
    private AddressRepository addressRepository;

    private MemberRepository memberRepository;

    public AddressServiceImPl(ModelMapper modelMapper, AddressRepository addressRepository, MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
    }
    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Member member = memberRepository.findById(addressDto.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", addressDto.getMemberId()));
        Address address = mapToEntity(addressDto);
        address.setMember(member);
        Address newAddress = addressRepository.save(address);
        AddressDto responseAddress = mapToDto(newAddress);
        return responseAddress;
    }

    @Override
    public AddressesResponseDto getAllAddress(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Member> members = memberRepository.findAll(pageable);
        List<Member> listOfMembers = members.getContent();
        List<MemberDto> contents = listOfMembers.stream().map(member ->
                mapToDto(member)).collect(Collectors.toList());
        MembersResponseDto responseMembers = new MembersResponseDto();
        responseMembers.setContent(contents);
        responseMembers.setPageNo(members.getNumber() + 1);
        responseMembers.setPageSize(members.getSize());
        responseMembers.setTotalPages(members.getTotalPages());
        responseMembers.setTotalElements(members.getTotalElements());
        responseMembers.setLast(members.isLast());
        return responseMembers;
    }

    @Override
    public AddressDto getAddressById(int id) {
        return null;
    }

    @Override
    public AddressDto deleteAddressById(int id) {
        return null;
    }

    @Override
    public AddressDto updateAddressById(int id, AddressDto addressDto) {
        return null;
    }
    private AddressDto mapToDto(Address address) {
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);
        return addressDto;
    }

    private Address mapToEntity(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        return address;
    }
}
