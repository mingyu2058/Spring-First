package first.firstspring.controller;

public class MemberForm {

    //createMemberForm에서 name = "name"과 매칭  - POST
    //so, getName으로 가져올 수 있음

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
