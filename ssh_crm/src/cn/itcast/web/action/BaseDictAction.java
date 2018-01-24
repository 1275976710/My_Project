package cn.itcast.web.action;

import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import cn.itcast.domain.BaseDict;
import cn.itcast.service.BaseDictService;

@Controller("baseDictAction")
@Scope("prototype")
public class BaseDictAction extends ActionSupport {

	//spring注入baseDictService
	@Resource(name="baseDictService")
	private BaseDictService baseDictService;	
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	//使用struts2的属性驱动
	private String dict_type_code;
	
	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	@Override
	//根据dict_type_code获得数据字典对象
	public String execute() throws Exception {
		//1.调用Service根据type_code获得数据字典对象list
		List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);
		//2.将list转换为json格式
		Gson gson = new Gson();
		String json = gson.toJson(list);
		//3.将json发送给浏览器
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(json);
		//告诉struts2不需要进行结果处理
		return null;
	}
	
}
