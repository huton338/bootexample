package com.example.form;

import javax.validation.constraints.NotBlank;

/**
 *
 */
public class UserForm {

    private Integer id;

    @NotBlank
    private String displayName;

    @NotBlank
    private String name;

    @NotBlank
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
