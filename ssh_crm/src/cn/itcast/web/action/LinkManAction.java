package cn.itcast.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import cn.itcast.domain.LinkMan;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;

@Controller("linkManAction")
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan = new LinkMan();
	
	@Resource(name="linkManService")
	private LinkManService lms;

	private Integer currentPage;
	private Integer pageSize;
	
	//查询联系人列表
	public String list() throws Exception {
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		//判断并封装参数
		if(StringUtils.isNotBlank(linkMan.getLkm_name())){
			dc.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		//根据客户id查询联系人
		//不加后面的条件分页会出问题
		if(linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = lms.getPageBean(dc,currentPage,pageSize);
		//2 将PageBean放入request域,转发到列表页面显示
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}
	
	//保存联系人功能
	public String add() throws Exception {
		//1 调用Service
		lms.save(linkMan);
		System.out.println(linkMan);
		//2 重定向到联系人列表
		return "toList";
	}
	
	//查询联系人
	public String toEdit() throws Exception {
		//1 调用Service,查询LinkMan
		LinkMan lm = lms.getById(linkMan.getLkm_id());
		//2 将查询的Linkman对象放入request域,转发到添加页面
		ActionContext.getContext().put("linkMan", lm);
		return "add";
	}


	@Override
	public LinkMan getModel() {
		return linkMan;
	}


	public void setLms(LinkManService lms) {
		this.lms = lms;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
