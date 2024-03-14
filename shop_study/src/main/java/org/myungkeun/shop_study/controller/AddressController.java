package org.myungkeun.shop_study.controller;

import org.myungkeun.shop_study.payload.AddressDto;
import org.myungkeun.shop_study.payload.AddressesResponseDto;
import org.myungkeun.shop_study.service.AddressService;
import org.myungkeun.shop_study.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto){
        return new ResponseEntity<>(addressService.createAddress(addressDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<AddressesResponseDto> getAllAddress(
            @RequestParam(value = "pageNe", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNe,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return new ResponseEntity<>(addressService.getAllAddress(pageNe, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(addressService.deleteAddressById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddressById(@PathVariable(name = "id") int id, AddressDto addressDto) {
        return new ResponseEntity<>(addressService.updateAddressById(id, addressDto), HttpStatus.OK);
    }
}
