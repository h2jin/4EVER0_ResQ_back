package com.kosa.resq.mapper.car;

import com.kosa.resq.domain.dto.car.CarDetailDTO;
import com.kosa.resq.domain.dto.car.CarLocDTO;
import com.kosa.resq.domain.dto.car.CarRezDTO;
import com.kosa.resq.domain.vo.car.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CarUserMapper {
    //예약정보 저장
    public int carRezSave(CarRezRequestVO carRezRequestVO);
    public int carRezSeq();
    public int carLocSave(CarLocRequestVO CarLocRequestVO);
    public int carLocSeq();
    public List<CarDetailResponseVO> carGetAll();
    public List<AvailableCarResponseVO> carGetAll2(Date start_at, Date return_at);
    public CarDetailResponseVO carGetOne(String car_code);
    public CarRezResponseVO carRezGetOne(String car_rez_code);
    public List<CarLocResponseVO> carLocInfoGetAll(String car_rez_code);
    public List<CarRezInfoResponseVO> carRezGetAll(String mem_code);
    public List<CarRezInfoResponseVO> filterCarRezGetAll(String mem_code,String rez_status);
    public List<CarNameCodeResponseVO> searchCarGetAll ();
    public int carRezDelete(String car_rez_code);
    public int carRezUpdate(CarRezRequestVO carRezRequestVO);
    public int carLocUpdate(CarLocRequestVO carLocRequestVO);
    public int carLocDelete(String car_rez_code);
    public int operationSave(OperationRequestVO operationRequestVO);
    public int carRezCompleteUpdate(String car_rez_code);
    public int carDetailUpdate(CarDetailRequestVO carDetailRequestVO);
    public CarLocResponseVO carLocReturnGetOne(String car_rez_code);
    public int expenditureSave(ExpenditureRequestVO expenditureRequestVO);
}
