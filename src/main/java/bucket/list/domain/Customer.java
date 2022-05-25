package bucket.list.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customeridx")
    private Integer customerIdx;

    @Column(name = "customersubject")
    private String customerSubject;

    @Column(name = "customertext")
    private String customerText;

    @Column(name = "customerwriter")
    private String customerWriter;

    @Column(name = "customerdate")
    private LocalDate customerDate;

    @Column(name = "customerfile")
    private String customerFile;

    @Column(name = "customerpassword")
    private String customerPassword;

    @Column(name = "customersecret")
    private String customerSecret;

    @PrePersist
    public void localCustomerDate(){
        this.customerDate = LocalDate.now();
    }


}


