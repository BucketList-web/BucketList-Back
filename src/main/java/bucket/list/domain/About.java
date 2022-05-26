package bucket.list.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aboutnumber")
    private Integer aboutnumber;

    private String about_subject;

    private String about_text;

    private String about_writer;

    private LocalDate about_date;

    private String about_file;


    @PrePersist
    public void localAboutDate(){
        this.about_date = LocalDate.now();
    }

}
