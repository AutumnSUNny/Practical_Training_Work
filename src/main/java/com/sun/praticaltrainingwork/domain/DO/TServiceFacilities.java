package com.sun.praticaltrainingwork.domain.DO;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_service_facilities")
public class TServiceFacilities implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String serId;

    private String serName;

    /*
    * 收费类别	0	西药
收费类别	1	中成药
收费类别	2	中草药
收费类别	3	床位费
收费类别	4	化验费
收费类别	5	诊查费
收费类别	6	检查费
收费类别	7	护理费
收费类别	8	特检费
收费类别	9	输氧费
收费类别	10	治疗费
收费类别	11	输血费
收费类别	12	特治费
收费类别	13	医疗服务费
收费类别	14	手术费
收费类别	15	麻药费
收费类别	16	产前检查费
收费类别	17	材料费
收费类别	18	新生儿费
收费类别	19	内置材料
收费类别	20	其他费
收费类别	21	监护床位费
收费类别	22	非处方药
收费类别	23	处方药
收费类别	24	甲类
收费类别	25	乙类
收费类别	26	丙类
收费类别	27	化验费
收费类别	28	诊查费
收费类别	29	检查费
收费类别	30	护理费
收费类别	31	特检费
收费类别	32	输氧费
收费类别	33	治疗费
收费类别	34	输血费
收费类别	35	特治费
收费类别	36	医疗服务费
收费类别	37	手术费
收费类别	38	麻药费
收费类别	39	产前检查费
收费类别	40	材料费
收费类别	41	新生儿费
收费类别	42	内置材料
收费类别	43	其他费
收费类别	44	监护床位费

    * */
    private String serExpType;

    private Date serStarttime;

    private Date serEndtime;

    private String serValid;
}
