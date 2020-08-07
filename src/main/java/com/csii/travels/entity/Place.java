package com.csii.travels.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Place {

    public Integer id;
    public String name;
    public String picpath;
    @JsonFormat(pattern = "yyyy/MM/dd")
    public Date hottime;
    public Double hotticket;
    public Double dimticket;
    public String placedes;
    public Integer provinceid;
}
