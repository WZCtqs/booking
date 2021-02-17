package com.zih.booking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Ceshi {

    public static void main(String[] args) {
        // 新计算计费体积
        List<BigDecimal> list = new ArrayList<>();
        // 实测总体积
        BigDecimal measuredVolume = new BigDecimal("10.80");
        list.add(measuredVolume);

        // 最终选用体积
        BigDecimal billableVolume = list.stream().max(BigDecimal::compareTo).get();

        String lcFreight = "5";//运费
        Long orderUnloadSiteBacost = 60L;//目的站收费
        BigDecimal bgLcF = new BigDecimal(lcFreight);
        BigDecimal bgOrdunload = new BigDecimal(orderUnloadSiteBacost);
        BigDecimal totalSHFee = new BigDecimal("0");
        totalSHFee = totalSHFee.add(bgLcF.add(bgOrdunload));
        BigDecimal totalRailFees = billableVolume.multiply(totalSHFee).setScale(1,BigDecimal.ROUND_HALF_UP);//总铁路运费
        System.out.println(totalRailFees);

    }
}
