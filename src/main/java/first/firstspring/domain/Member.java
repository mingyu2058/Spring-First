package first.firstspring.domain;

// 객체랑 orm(object relational mapping을 annotaion으로 함)

import javax.persistence.*;

// 이 annotation쓰면 Jpa가 관리하는 entity
@Entity

public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
