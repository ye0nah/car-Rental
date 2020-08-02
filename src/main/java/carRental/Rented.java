package carRental;

import java.util.Date;

public class Rented extends AbstractEvent {

    private Long id;
    private Long carId;
    private Integer fee;
    private Date rentalDate;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return carId;
    }

    public void setProductId(Long carId) {
        this.carId = carId;
    }
    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
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
}