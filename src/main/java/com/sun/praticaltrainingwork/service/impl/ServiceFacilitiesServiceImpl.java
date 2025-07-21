package com.sun.praticaltrainingwork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.praticaltrainingwork.domain.DO.TServiceFacilities;
import com.sun.praticaltrainingwork.domain.DTO.ServiceFacilities.ServiceFacilitiesListRequest;
import com.sun.praticaltrainingwork.domain.Result;
import com.sun.praticaltrainingwork.domain.VO.QueryVO;
import com.sun.praticaltrainingwork.domain.VO.ServiceFacilities.TServiceFacilitiesVO;
import com.sun.praticaltrainingwork.mapper.ServiceFacilitiesMapper;
import com.sun.praticaltrainingwork.service.ServiceFacilitiesService;
import com.sun.praticaltrainingwork.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ServiceFacilitiesServiceImpl implements ServiceFacilitiesService {

    private final ServiceFacilitiesMapper mapper;

    @Override
    public Result<Void> add(TServiceFacilities entity) {
        mapper.insert(entity);
        return Result.success(null);
    }

    @Override
    public Result<Void> delete(Integer id) {
        mapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result<Void> update(TServiceFacilities entity) {
        mapper.updateById(entity);
        return Result.success(null);
    }

//    @Override
//    public Result<QueryVO<TServiceFacilitiesVO>> query(Integer pageNum, Integer pageSize, Map<String, Boolean> sorts, ServiceFacilitiesListRequest.Conditions c) {
//        Page<TServiceFacilities> page = new Page<>(pageNum, pageSize);
//        if (sorts != null) {
//            sorts.forEach((key, desc) -> {
//                OrderItem orderItem = new OrderItem();
//                orderItem.setColumn(key);
//                orderItem.setAsc(!desc);
//                page.addOrder(orderItem);
//            });
//        }
//
//        IPage<TServiceFacilities> resultPage = mapper.selectPage(page,
//                new LambdaQueryWrapper<TServiceFacilities>()
//                        .like(c.getSerId() != null, TServiceFacilities::getSerId, c.getSerId())
//                        .like(c.getSerName() != null, TServiceFacilities::getSerName, c.getSerName())
//                        .eq(c.getSerExpType() != null, TServiceFacilities::getSerExpType, c.getSerExpType())
//                        .eq(c.getSerValid() != null, TServiceFacilities::getSerValid, c.getSerValid())
//                        .between(c.getSerStarttime() != null && c.getSerEndtime() != null,
//                                TServiceFacilities::getSerStarttime, c.getSerStarttime(), c.getSerEndtime())
//        );
//
//        QueryVO<TServiceFacilitiesVO> vo = new QueryVO<>();
//        vo.setTotal(resultPage.getTotal());
//        vo.setPageNum((int) resultPage.getCurrent());
//        vo.setPageSize((int) resultPage.getSize());
//        vo.setData(resultPage.getRecords().stream().map(record -> {
//            TServiceFacilitiesVO itemVO = new TServiceFacilitiesVO();
//            BeanUtils.copyProperties(record, itemVO);
//            return itemVO;
//        }).toList());
//
//        return Result.success(vo);
//    }
@Override
public Result<QueryVO<TServiceFacilitiesVO>> query(
        Integer pageNum,
        Integer pageSize,
        Map<String, Boolean> sorts,
        ServiceFacilitiesListRequest.Conditions c) {

    Page<TServiceFacilities> page = new Page<>(pageNum, pageSize);

    // ✅ 处理排序字段：支持前端传驼峰风格字段名
    if (sorts != null) {
        sorts.forEach((key, desc) -> {
            String column = CommonUtils.camelToUnderline(key); // 驼峰转下划线
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(column);
            orderItem.setAsc(!desc); // true 表示升序
            page.addOrder(orderItem);
        });
    }

    // ✅ 查询条件
    LambdaQueryWrapper<TServiceFacilities> wrapper = new LambdaQueryWrapper<>();
    if (c != null) {
        wrapper.like(c.getSerId() != null, TServiceFacilities::getSerId, c.getSerId())
                .like(c.getSerName() != null, TServiceFacilities::getSerName, c.getSerName())
                .eq(c.getSerExpType() != null, TServiceFacilities::getSerExpType, c.getSerExpType())
                .eq(c.getSerValid() != null, TServiceFacilities::getSerValid, c.getSerValid())
                .between(
                        c.getSerStarttime() != null && c.getSerEndtime() != null,
                        TServiceFacilities::getSerStarttime,
                        c.getSerStarttime(), c.getSerEndtime()
                );
    }

    IPage<TServiceFacilities> resultPage = mapper.selectPage(page, wrapper);

    // ✅ VO 封装
    QueryVO<TServiceFacilitiesVO> vo = new QueryVO<>();
    vo.setTotal(resultPage.getTotal());
    vo.setPageNum((int) resultPage.getCurrent());
    vo.setPageSize((int) resultPage.getSize());
    vo.setData(resultPage.getRecords().stream().map(record -> {
        TServiceFacilitiesVO itemVO = new TServiceFacilitiesVO();
        BeanUtils.copyProperties(record, itemVO);
        return itemVO;
    }).toList());

    return Result.success(vo);
}

}
