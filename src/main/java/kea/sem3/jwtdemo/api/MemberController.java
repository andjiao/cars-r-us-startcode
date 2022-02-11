package kea.sem3.jwtdemo.api;


import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.CarService;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/members")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberResponse> getMembers(){
        return memberService.getMemebers();
    }

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable String username) throws Exception {

        return memberService.getMember(username,false);}


    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    @PutMapping("/{id}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username){return null;}

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String username){
        memberService.deleteMember(username);
    }

}
