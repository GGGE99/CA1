/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entities.Member;

/**
 *
 * @author madsa
 */
public class MemberDTO {

    private long id;
    private String name;
    private String studentID;
    private String favTvShow;

    public MemberDTO(Member members) {
        this.id = members.getId();
        this.name = members.getName();
        this.studentID = members.getStudentID();
        this.favTvShow = members.getFavTvShow();
    }

}
