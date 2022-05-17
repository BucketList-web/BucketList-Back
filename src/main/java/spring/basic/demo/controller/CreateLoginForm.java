package spring.basic.demo.controller;

import lombok.Data;

@Data
public class CreateLoginForm {

    private String User_id;
    private String User_pw;
    private String User_pwcorrect;
    private String User_name;
    private String User_email;
    private String User_phone;

}
