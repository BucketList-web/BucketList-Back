package bucket.list.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity(name = "userdata")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_idx")
    private Integer useridx;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String user_id;


    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = "[가-힣]*",message = "이름 형식이 올바르지 않습니다")
    private String user_name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String user_pw;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String user_pw2;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
//    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String user_email;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
//    @Pattern(regexp = "(010)-\\d{3,4}-\\d{4}", message = "전화번호 형식이 올바르지 않습니다.")
    @Column(name = "user_phone")
    private String user_phone;

    @Column(name = "USEREXISTID")
    private boolean userExistId;

}
