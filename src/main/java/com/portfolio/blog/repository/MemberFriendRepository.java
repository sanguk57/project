package com.portfolio.blog.repository;

import com.portfolio.blog.constant.FriendShip;
import com.portfolio.blog.entity.MemberFriend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberFriendRepository extends JpaRepository<MemberFriend, Long> {

    //친구삭제1
    void deleteByLoginIdAndFriendId(String loginId, String friendId);
    //친구삭제2
    void deleteByFnum(Long fnum);
    //친구추가 중복검사
    int countByLoginIdAndFriendId(String loginId, String friendId);

    List<MemberFriend> findByFriendIdAndType(String friendId, FriendShip friendShip);
    List<MemberFriend> findByLoginId(String loginId);
    List<MemberFriend> findByFriendId(String friendId);
    MemberFriend findByLoginIdAndFriendId(String loginId, String friendId);
}
