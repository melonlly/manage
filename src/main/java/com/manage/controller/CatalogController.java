package com.manage.controller;

import com.manage.entity.Catalog;
import com.manage.entity.Result;
import com.manage.repository.CatalogRepository;
import com.manage.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

	@Resource
	CatalogRepository catalogRepository;

	@RequestMapping("/all")
	@ResponseBody
	public Result catalogs() {
		Result result = new Result();
		try {
			List<Catalog> all = catalogRepository.findAll();
			List<Map> catalogs = new ArrayList<Map>();
			for (Catalog catalog : all) {
				Integer parentid = catalog.getParentid();
				if (parentid == null) {
					Map map = new HashMap();
					map.put("id", catalog.getId());
					map.put("name", catalog.getName());
					catalogs.add(map);
				}
			}
			for (Catalog catalog : all) {
				Integer parentid = catalog.getParentid();
				if(parentid != null){
					for (Map map : catalogs){
						if(parentid == map.get("id")){
							List<Map> subs = (List<Map>)map.get("sub");
							Map sub = new HashMap();
							sub.put("id", catalog.getId());
							sub.put("name", catalog.getName());
							sub.put("url", catalog.getUrl());
							if(subs != null){
								subs.add(sub);
							}else {
								map.put("sub", new ArrayList<>());
								((List<Map>) map.get("sub")).add(sub);
							}
						}
					}
				}
			}
			result.setCode(Constants.SUCCESS);
			result.setData(catalogs);
		} catch (Exception e) {
			result.setCode(Constants.FAIL);
			result.setError(e.getMessage());
		}
		return result;
	}

}
