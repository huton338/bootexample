package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "name")
    private String name;

    @Column(name = "mailaddress")
    private String mailaddress;

    /**
     * id Getter.
     *
     * @return id
     */
    public Integer getId() {

        return id;
    }

    /**
     * id Setter.
     *
     * @param id id
     */
    public void setId(Integer id) {

        this.id = id;
    }

    /**
     * displayName Getter.
     *
     * @return displayName
     */
    public String getDisplayName() {

        return displayName;
    }

    /**
     * displayName Setter.
     *
     * @param displayName displayName
     */
    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    /**
     * name Getter.
     *
     * @return name
     */
    public String getName() {

        return name;
    }

    /**
     * name Setter.
     *
     * @param name name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * mailaddress Getter.
     *
     * @return mailaddress
     */
    public String getMailaddress() {

        return mailaddress;
    }

    /**
     * mailaddress Setter.
     *
     * @param mailaddress mailaddress
     */
    public void setMailaddress(String mailaddress) {

        this.mailaddress = mailaddress;
    }

}
