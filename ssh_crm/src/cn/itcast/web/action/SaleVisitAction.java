package cn.itcast.web.action;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import cn.itcast.domain.SaleVisit;
import cn.itcast.domain.User;
import cn.itcast.service.SaleVisitService;
import cn.itcast.utils.PageBean;

@Controller("saleVisitAction")
@Scope("prototype")
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	@Resource(name="saleVisitService")
	private SaleVisitService svs;
	
	public void setSvs(SaleVisitService svs) {
		this.svs = svs;
	}

	private Integer currentPage;
	private Integer pageSize;
	
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

	//准备栈顶对象
	private SaleVisit sv = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return sv;
	}
	
	//添加客户拜访记录
	public String add() throws Exception {
		//取出登录用户，放入SaleVisit实体表达关系
		User user = (User) ActionContext.getContext().getSession().get("user");
		sv.setUser(user);
		//调用service保存客户拜访记录
		svs.save(sv);
		//重定向到拜访记录列表Action
		return "toList";
	}
	
	//查询用户拜访客户列表，返回一个pageBean
		public String list() throws Exception {
			//封装离线查询对象
			DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
			//判断并封装参数
			if(sv.getCustomer()!=null&&sv.getCustomer().getCust_id()!=null){
				dc.add(Restrictions.eq("customer.cust_id", sv.getCustomer().getCust_id()));
			}
			//1 调用Service查询分页数据(PageBean)
			PageBean pb = svs.getPageBean(dc,currentPage,pageSize);
			//2 将PageBean放入request域,转发到列表页面显示
			ActionContext.getContext().put("pageBean", pb);
			return "list";
		}

	//修改拜访记录
		public String toEdit() throws Exception {
			
			//调用service，根据ID查询客户拜访对象
			SaleVisit saleVisit = svs.getById(sv.getVisit_id());
			//将对象放入request域
			ActionContext.getContext().put("Salevisit", saleVisit);
			return "add";
		}
}
