package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "member")
@NamedQuery(name = "Members.getAll", query = "SELECT m FROM Member m")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "student_id", length = 10, nullable = false)
    private String studentID;
    @Column(name = "biggest_secret", length = 100, nullable = false)
    private String biggestSecret;
    @Column(name = "fav_tv_show", length = 30, nullable = false)
    private String favTvShow;
    
    
    public Member() {
    }

    public Member(String name, String studentID, String biggestSecret, String favTvShow) {
        this.name = name;
        this.studentID = studentID;
        this.biggestSecret = biggestSecret;
        this.favTvShow = favTvShow;
    }
    
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getBiggestSecret() {
        return biggestSecret;
    }

    public void setBiggestSecret(String biggestSecret) {
        this.biggestSecret = biggestSecret;
    }

    public String getFavTvShow() {
        return favTvShow;
    }

    public void setFavTvShow(String favTvShow) {
        this.favTvShow = favTvShow;
    }



    
    
    

   
}
