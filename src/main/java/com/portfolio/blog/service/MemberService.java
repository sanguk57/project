package com.portfolio.blog.service;

import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MemberService implements UserDetailsService  {
    // 자동 주입
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){

        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){

        Optional<Member> findMember = memberRepository.findById(member.getId());
        if (findMember.isPresent()){
            throw new IllegalStateException ("이미 존재하는 아이디입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername-------------------------------");
        Optional<Member> member = memberRepository.findById(id);
        if (member == null){
            throw new UsernameNotFoundException(id);
        }
        return User.builder()
                .username(member.get().getId())
                .password(member.get().getPassword())
                .roles(member.get().getRole().toString())
                .build();
    }

    /*
    UserDetailsService인터페이스는 db에서 회원정보를 가져오는 역할
    loadUserByUsername()
    : 회원정보를 조회하여
      사용자의 정보와 권한을 갖는 UserDetails인터페이스를 반환

      UserDetail : 직접구현,  시큐리티에서 제공하는 User클래스를 사용
     */



}
