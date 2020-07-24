package com.zcx.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private  String username;
    private  String password;
    private  String name;
    private  String email;
    private  String telephone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private  int sex;
    private  int state;
    private  String code;

    @OneToOne(targetEntity = Card.class,cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "cid",referencedColumnName = "id",insertable = true,updatable = false,nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Card card;

    @ManyToOne(targetEntity = Dept.class,cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name = "did",referencedColumnName = "id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @NotFound(action = NotFoundAction.IGNORE)
    private Dept dept;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "uid",referencedColumnName = "uid")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Room> rooms;

    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(name = "user_like",
            joinColumns = {@JoinColumn(name = "uid",referencedColumnName = "uid", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "lid",referencedColumnName = "id",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))}

        )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Like> likes;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", state=" + state +
                ", code='" + code + '\'' +
                ", card=" + card +
                ", dept=" + dept +
                ", rooms=" + rooms +
                ", likes=" + likes +
                '}';
    }
}
