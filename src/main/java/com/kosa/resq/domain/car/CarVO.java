package com.kosa.resq.domain.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarVO {
    private String car_code;
    private String car_name;
    private String fuel_type;
    private float fuel_effciency;
    private float accum_mileage;
    private String authority;
    private Date buy_at;
    private String memo;
}
