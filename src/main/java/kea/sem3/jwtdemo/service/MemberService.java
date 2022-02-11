package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    public List<MemberResponse> getMemebers(){
        List<Member> memebers = memberRepository.findAll();
        return  MemberResponse.getMemberEntities(memebers);
    }

    public MemberResponse getMember(String username, boolean all) throws Exception {
        Member m1 = memberRepository.findById(username).orElseThrow(()->new Client4xxException("not found"));
        return new MemberResponse(m1,false);
    }

    public MemberResponse addMember(MemberRequest body){
        Member memberNew = memberRepository.save(new Member(body));
        return new MemberResponse(memberNew,true);
    }


    public MemberResponse editMember(MemberRequest body, String username){

        Member m1 = memberRepository.findById(username).orElseThrow(()-> new Client4xxException("not found"));
        //From baseuser
        m1.setUsername(body.getUsername());
        m1.setEmail(body.getEmail());
        m1.setPassword(body.getPassword());

        //From user

        m1.setFirstName(body.getFirstName());
       m1.setLastName(body.getLastName());
       m1.setStreet(body.getStreet());
       m1.setCity(body.getCity());
       m1.setZip(body.getZip());
       m1.setApproved(body.isApproved());
       m1.setRanking(body.getRanking());

        return new MemberResponse(memberRepository.save(m1),true);

    }
    public void deleteMember(String username){
        memberRepository.deleteById(username);
    }
}
