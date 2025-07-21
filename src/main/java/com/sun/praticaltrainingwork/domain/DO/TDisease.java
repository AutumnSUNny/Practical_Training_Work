package com.sun.praticaltrainingwork.domain.DO;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_disease")
public class TDisease implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String diseaseId;

    private String diseaseName;

    /*
    疾病种类	0	心血管系统疾病
	疾病种类	1	消化系统疾病
	疾病种类	2	代谢内分泌疾病
	疾病种类	3	造血系统疾病
	疾病种类	4	肾脏疾病
	疾病种类	5	神经系统
	疾病种类	6	免疫系统疾病
	疾病种类	7	传染病
	疾病种类	8	呼吸系统疾病
	疾病种类	9	恶性肿瘤
	疾病种类	10	精神病
	疾病种类	11	残疾
	疾病种类	12	泌尿系统
	疾病种类	13	其他
*/
    private String diseaseType;

    private String diseaseReimbursementStandards;

    private String notes;
}