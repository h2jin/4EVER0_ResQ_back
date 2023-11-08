package com.kosa.resq.controller.car;

import com.kosa.resq.domain.dto.car.CarDTO;
import com.kosa.resq.domain.dto.car.CarMaintItemDTO;
import com.kosa.resq.domain.dto.car.SearchCar;
import com.kosa.resq.domain.dto.car.SearchOperation;
import com.kosa.resq.domain.dto.common.MemDTO;
import com.kosa.resq.domain.vo.car.*;
import com.kosa.resq.domain.vo.common.MemResponseVO;
import com.kosa.resq.service.car.CarAdminService;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Delete;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Log
@RestController
@RequestMapping("/manager")
public class CarAdminController {

    @Autowired
    private CarAdminService carAdminService;

    // 차량 등록
    @PostMapping("/car/carRegister")
    public void carSave(@RequestBody CarDTO carDTO) {
        carDTO.getCarDetail().setCar_code(carDTO.getCar_code());
        carDTO.getCarDetail().setCar_status("사용가능");

        log.info(carDTO.toString());
        log.info(carDTO.getCarDetail().toString());
        log.info(carDTO.getCarUser().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CarRequestVO carRequestVO = mapper.map(carDTO, CarRequestVO.class);

        CarDetailRequestVO carDetailRequestVO = mapper.map(carDTO.getCarDetail(), CarDetailRequestVO.class);
        CarUserRequestVO carUserRequestVO = mapper.map(carDTO.getCarUser(), CarUserRequestVO.class);

        log.info(carRequestVO.toString());
        log.info(carDetailRequestVO.toString());
        log.info(carUserRequestVO.toString());

        carAdminService.carSave(carRequestVO, carDetailRequestVO, carUserRequestVO);
    }

    // 차량 조회 - 개별
    @GetMapping("/car/carListGetOne")
    public CarUserResponseVO carListGetOne(@RequestParam String mem_code) {
        log.info(mem_code);
        return carAdminService.carListGetOne(mem_code);
    }

    // 사용자 리스트 조회
    @GetMapping("/memList")
    public List<MemResponseVO> memGetAll() {

        return carAdminService.memGetAll(
        );
    }

    // 차량 조회 - 리스트
    @PostMapping("/car/carList")
    public List<CarListResponseVO> carGetAll(@RequestBody SearchCar searchCar) {
        log.info(searchCar.toString());

        return carAdminService.carGetAll(searchCar);
    }


    // 차량 상세 조회
    @GetMapping("/car/carGetOne")
    public CarDetailResponseVO carGetOne(@RequestParam String car_code) {
//        log.info(carAdminService.carGetOne(car_code).toString());
        return carAdminService.carGetOne(car_code);
    }

    // 차량 수정
    @PutMapping("/car/carModify")
    public void carUpdate(@RequestBody CarDTO carDTO) {
//        log.info(carDTO.toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CarRequestVO carRequestVO = mapper.map(carDTO, CarRequestVO.class);
        CarDetailRequestVO carDetailRequestVO = mapper.map(carDTO.getCarDetail(), CarDetailRequestVO.class);
        carDetailRequestVO.setCar_code(carRequestVO.getCar_code());
        CarUserRequestVO carUserRequestVO = mapper.map(carDTO.getCarUser(), CarUserRequestVO.class);
        carUserRequestVO.setCar_code(carRequestVO.getCar_code());

        log.info(carRequestVO.toString());
        log.info(carDetailRequestVO.toString());
        log.info(carUserRequestVO.toString());
        carAdminService.carModify(carRequestVO, carDetailRequestVO, carUserRequestVO);
    }

    // 차량 삭제 (상태 변경)
    @PutMapping("/car/carDelete")
    public void carDelete(@RequestBody String car_code) {
        log.info(car_code);
        carAdminService.carDelete(car_code);

    }


    // 정비 내역, 정비 업체 조회
    @GetMapping("/car/getMaintItem")
    public CarMaintItemResponseVO getCarMaintItem() {
        return carAdminService.carMaintItemGetAll();
    }

    // 정비 등록
    @PostMapping("/car/maintRecordRegister")
    public MaintRecordResponseVO maintRecordSave(@RequestBody CarMaintItemDTO carMaintItemDTO) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MaintRecordRequestVO maintRecordRequestVO = mapper.map(carMaintItemDTO, MaintRecordRequestVO.class);

        return carAdminService.maintRecordSave(maintRecordRequestVO);
    }

    // 차량 하나의 정비 리스트 불러오기
    @GetMapping("/car/maintOneCarRecordList")
    public List<MaintRecordResponseVO> maintOneCarRecordGetAll(@RequestParam String car_code) {
        log.info(car_code);
        return carAdminService.maintOneCarRecordGetAll(car_code);
    }

    // 선택한 정비 완료 처리
    @PostMapping("/car/maintCarStatusModify")
    public void maintCarStatusUpdate(@RequestBody MaintModifyRequestVO maintModifyRequestVO) {
        carAdminService.maintEndAtUpdate(maintModifyRequestVO);

    }

    // 선택한 정비 삭제
    @PostMapping("/car/maintRecordDelete")
    public void maintRecordDelete(@RequestBody MaintModifyRequestVO maintModifyRequestVO) {
        log.info(maintModifyRequestVO.getCar_code());
        log.info(maintModifyRequestVO.getMaint_codes().toString());
        carAdminService.maintRecordDelete(maintModifyRequestVO);
    }

    //차량 운행 내역 조회
    @PostMapping("/car/operationList")
    public List<OperationResponseVO> operationGetAll(@RequestBody SearchOperation searchOperation) {
        log.info(searchOperation.toString());
        return carAdminService.operationGetAll(searchOperation);
    }

    @GetMapping("/car/currentMaint")
    public List<CurrentMaintResponseVO> currentMaintGet(@RequestParam String car_code) {
        return carAdminService.currentMaintGet(car_code);
    }

    @GetMapping("/car/operationListOne")
    public List<OperationResponseVO> operationGetOneCar(@RequestParam String car_code, @RequestParam Date sdate, @RequestParam Date edate) {
        return carAdminService.operationGetOne(car_code, sdate, edate);
    }

    // 차량 목록 가져오기
    @GetMapping("/car/carListGetAll")
    public List<CarVO> carListGetAll() {
        return carAdminService.carListGetAll();
    }

    // 예약 리스트 가져오기
    @GetMapping("/car/rezListGetAll")
    public List<CarRezInfoResponseVO> carRezListGetAll() {
        return carAdminService.carRezListGetAll();
    }


}
