package com.noriton.team9.controller;

import com.noriton.team9.request.MemberCreationAndLoginRequest;
import com.noriton.team9.request.OrderCreationRequest;
import com.noriton.team9.service.MemberService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인
     * */
    @GetMapping("/member/login")
    public ResponseEntity login(@RequestBody MemberCreationAndLoginRequest request){
        return ResponseEntity.ok(memberService.login(request.getLoginId(), request.getPassword()));
    }

    /**
     * 전체 client 조회
     * */
    @GetMapping("/member")
    public ResponseEntity readMember(){
        return ResponseEntity.ok(memberService.readMembers());
    }

    /**
     * 회원가입
     * */
    @PostMapping("/member")
    public ResponseEntity createMember(@RequestBody MemberCreationAndLoginRequest request){
        return ResponseEntity.ok(memberService.createMember(request.getLoginId(), request.getPassword()));
    }

    /**
     * 회원탈퇴
     * */
    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }



}
