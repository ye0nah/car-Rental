package carRental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="CarRental_table")
public class CarRental {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long carId;
    private Date rentalDate;
    private String status;
    private Integer fee;

    @PostPersist
    public void onPostPersist(){

        Rented rented = new Rented();
        BeanUtils.copyProperties(this, rented);
        rented.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        carRental.external.Payment payment = new carRental.external.Payment();
        // mappings goes here
        RentalApplication.applicationContext.getBean(carRental.external.PaymentService.class)
            .pay(payment);


    }

    @PreRemove
    public void onPreRemove(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.


        RentalCanceled rentalCanceled = new RentalCanceled();
        BeanUtils.copyProperties(this, rentalCanceled);
        rentalCanceled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        carRental.external.Payment payment = new carRental.external.Payment();
        // mappings goes here
        RentalApplication.applicationContext.getBean(carRental.external.PaymentService.class)
            .refund(payment);


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }




}
