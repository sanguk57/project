package com.portfolio.blog.service;

import com.portfolio.blog.dto.MemberDTO;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberService service;

    //회원가입 테스트
    @Test
    public void joinTest(String id, String password,
                         String name, String nickName){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setName(name);
        memberDTO.setPassword(password);
        memberDTO.setNickName(nickName);

        Member member = Member.createMember(memberDTO, passwordEncoder);

        Optional<Member> findMember = memberRepository.findById(member.getId());
        if (findMember.isPresent()){
            throw new IllegalStateException ("이미 존재하는 아이디입니다.");
        }else {
            memberRepository.save(member);
        }
    }
    
    //로그인 테스트
    /*@Test
    public void loginTest() throws Exception {
        String id = "id";
        String password = "password";
        String name = "name";
        String nickName = "nickName";

        this.joinTest(id, password,name,nickName);

        mockMvc.perform(formLogin().userParameter("id")
                        .loginProcessingUrl("/blog/login")
                        .user(id).password(password))
                        .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }*/

}
