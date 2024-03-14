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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
        Page<Address> addresses = addressRepository.findAll(pageable);
        List<Address> listOfAddresses = addresses.getContent();
        List<AddressDto> contents = listOfAddresses.stream().map(address ->
                mapToDto(address)).collect(Collectors.toList());
        AddressesResponseDto addressesResponseDto = new AddressesResponseDto();
        addressesResponseDto.setContent(contents);
        addressesResponseDto.setPageNo(addresses.getNumber() + 1);
        addressesResponseDto.setPageSize(addresses.getSize());
        addressesResponseDto.setTotalPages(addresses.getTotalPages());
        addressesResponseDto.setTotalElements(addresses.getTotalElements());
        addressesResponseDto.setLast(addresses.isLast());
        return addressesResponseDto;
    }

    @Override
    public AddressDto getAddressById(int id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", id));
        AddressDto responseAddress = mapToDto(address);
        return responseAddress;
    }

    @Override
    public String deleteAddressById(int id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", id));
        addressRepository.delete(address);
        return "deleted";
    }

    @Override
    public AddressDto updateAddressById(int id, AddressDto addressDto) {
        Address oldAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", id));
        Member member = memberRepository.findById(addressDto.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        oldAddress.setMember(member);
        oldAddress.setAddr(addressDto.getAddr());
        oldAddress.setName(addressDto.getName());
        oldAddress.setPhone(addressDto.getPhone());
        oldAddress.setAddrDetail(addressDto.getAddrDetail());
        oldAddress.setRequest(addressDto.getRequest());
        oldAddress.setZipcode(addressDto.getZipcode());
        Address updateAddress = addressRepository.save(oldAddress);
        AddressDto responseAddress = mapToDto(updateAddress);
        return responseAddress;
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
