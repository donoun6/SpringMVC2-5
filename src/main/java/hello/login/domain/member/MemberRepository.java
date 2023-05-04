package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id); //map에 담긴 value는 Member타입, 리스트형태로 반환
    }

    //Optional은 빈 껍데기라고 생각하자.
    //요즘은 값이 NULL로 반환하는 대신에 Optional을 이용하여 찾을수 있도록 한다.
    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for (Member m : all) {
//            if (m.getLoginId().equals(loginId)) {
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();

        //위와 같다 람다식
        //list를 stream이란걸로 바꾸고 루프를 돌린다. 이후 필타로 만족하는 애만 다음단게로 넘어가도록
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst(); //먼저 나오는애들 반환
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
