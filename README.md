# spring data envers
- 간단한 히스토리 저장할 때 사용하면 좋을 듯함
- 조회
  - RevisionRepository를 추가해주면 됨.
  - 공식문서 참고
```
# 참고
interface PersonRepository extends CrudRepository<Person, Long>, RevisionRepository<User, Long, Long>{}

findLastChangeRevision(ID id);
findRevisions(ID id);
findRevisions(ID id, Pageable pageable);
findRevision(ID id, N revisionNumber);
```

# 참고
- https://docs.spring.io/spring-data/envers/docs/current/reference/html/#reference
- https://sehajyang.github.io/2020/04/15/springboot-envers-logging-for-revision/
