package WebToyProject1.webService.repository;

import WebToyProject1.webService.member.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

}
