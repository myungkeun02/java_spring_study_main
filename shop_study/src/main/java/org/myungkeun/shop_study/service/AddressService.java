package org.myungkeun.shop_study.service;

import org.myungkeun.shop_study.payload.AddressDto;
import org.myungkeun.shop_study.payload.AddressesResponseDto;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto);

    AddressesResponseDto getAllAddress(int pageNo, int pageSize, String sortBy, String sortDir);

    AddressDto getAddressById(int id);

    AddressDto deleteAddressById(int id);

    AddressDto updateAddressById(int id, AddressDto addressDto);
}
