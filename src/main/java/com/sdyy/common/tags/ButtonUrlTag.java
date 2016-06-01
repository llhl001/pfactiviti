package com.sdyy.common.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/*import com.sdyy.base.ac.ac_permission.model.AcPermission;*/

public class ButtonUrlTag extends BodyTagSupport {
	private static final long serialVersionUID = -7811902545513255473L; 


	//标签属性用户名
	private String user = null;
	//标签属性操作url
	private String url = null;
	//标签属性 js方法
	private String jsmethod = null;
	//标签属性image 按钮图片
	private String image = null;
	//标签属性 alt 提示
	private String alt = null;
	//标签属性操作value 按钮文本
	private String value  = null;




	/* 标签初始方法 */
	public int doStartTag() throws JspTagException{
		return super.EVAL_BODY_INCLUDE;
	}


	/* 标签结束方法 */
	public int doEndTag() throws JspTagException{
		pageContext.getSession();
		Boolean b = false;
		List list = new ArrayList();
		/*AcPermission p = new AcPermission();*/
		/*JDBCHibernate jdbca = new JDBCHibernate();*/
		try {
			/*list = jdbca.getHaveURLByUsernameList(user);*/
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 0;i < list.size(); i++){
			/*p = (AcPermission) list.get(i);*/
			if(1==1) {//p.getUrl().trim().equals(url.trim())){
				b = true;
				//如果jsmethod属性不为null 则把超链接href改为调用js
				if(jsmethod!=null){
					url = jsmethod;
				}
			}
		}
		JspWriter out = pageContext.getOut();
		if(b){
			try {
				//有权限 显示操作按钮
				out.println("<a href='" +url+ "' class='regular'><img src='" + image + "' alt='" + alt +"' />" + value + "</a>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return super.SKIP_BODY;
	}

	/* 释放资源 */
	public void release(){
		super.release();
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getAlt() {
		return alt;
	}


	public void setAlt(String alt) {
		this.alt = alt;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}

	public String getJsmethod() {
		return jsmethod;
	}


	public void setJsmethod(String jsmethod) {
		this.jsmethod = jsmethod;
	}
}
