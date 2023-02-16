package com.portfolio.blog.api;

import com.portfolio.blog.dto.MemberSearchDTO;
import com.portfolio.blog.entity.Member;
import com.portfolio.blog.repository.MemberRepositoryCostom;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog-information-api")
public class BolgApi {
    private static final Logger logger = LoggerFactory.getLogger(BolgApi.class);

    final private MemberRepositoryCostom memberRepositoryCostom;

    @PostMapping("/memberSearch")
    public ResponseEntity<Page<Member>> memberSearch(@RequestBody HashMap<String, String> map, Pageable pageable){
        MemberSearchDTO memberSearchDTO = new MemberSearchDTO();

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        memberSearchDTO.setSearchBy(map.get("searchBy"));
        memberSearchDTO.setSearchQuery(map.get("searchQuery"));
        Page<Member> member = memberRepositoryCostom.getMemberList(memberSearchDTO, pageable);

        return new ResponseEntity<Page<Member>> (member, resHeaders, HttpStatus.OK);
    }

}
