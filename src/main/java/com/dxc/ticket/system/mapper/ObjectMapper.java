package com.dxc.ticket.system.mapper;

import org.springframework.beans.BeanUtils;



public class ObjectMapper {

   public static Object copyObject(Object source,Object target) {
	   BeanUtils.copyProperties(source, target);
	   return target;
   }

}
