package com.lmz.mike.auth.bean;

import com.amazonaws.partitions.PartitionRegionImpl;
import com.lmz.mike.data.annotation.LField;
import lombok.Data;

import java.util.List;


@Data
public class LmzRole extends BaseBean{

	private String code;
	private String name;
	private String extjson;
	private Integer active;

	@LField(useForDb = false)
	private List<LmzMenu> menus;

}
