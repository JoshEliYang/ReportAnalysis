package cn.springmvc.listener;

import javax.servlet.http.HttpServlet;

/**
 * 已废弃！
 * 每隔固定时间刷新redis数据
 * 
 * @author johsnon
 *
 */
public class InitListener extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6221844797355470783L;

//	public void init() {
//		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
//		Timer timer1=new Timer();
//		timer1.schedule(new ReportRefresh(context), 1000, 1000*3600);
//	}

}
