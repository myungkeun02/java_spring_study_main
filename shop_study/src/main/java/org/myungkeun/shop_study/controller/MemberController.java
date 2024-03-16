package org.myungkeun.shop_study.controller;

import org.myungkeun.shop_study.payload.MemberDto;
import org.myungkeun.shop_study.payload.MembersResponseDto;
import org.myungkeun.shop_study.payload.RegisterUserDto;
import org.myungkeun.shop_study.service.MemberService;
import org.myungkeun.shop_study.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto) {
        return null;
    }
//    @PostMapping("/create")
//    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
//        return new ResponseEntity<>(memberService.createMember(memberDto), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<MembersResponseDto> getAllMember(
//            @RequestParam(value = "pageNe", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNe,
//            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
//    ) {
//        return new ResponseEntity<>(memberService.getAllMember(pageNe, pageSize, sortBy, sortDir), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MemberDto> getMemberById(@PathVariable(name = "id") int id) {
//        return new ResponseEntity<>(memberService.getMemberById(id), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteMemberById(@PathVariable(name = "id") int id) {
//        return new ResponseEntity<>(memberService.deleteMemberById(id), HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<MemberDto> updatememberById(@PathVariable(name = "id") int id, @RequestBody MemberDto memberDto) {
//        return new ResponseEntity<>(memberService.updateMemberById(id, memberDto), HttpStatus.OK);
//    }


}
